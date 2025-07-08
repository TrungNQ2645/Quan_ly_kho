--
--***1. Trigger trên bảng VatTu
--Tự động cập nhật số lượng vật tư khi có giao dịch nhập hoặc xuất.
--TREN BANG CT_Nhap
CREATE OR REPLACE TRIGGER TRG_UPDATE_INVENTORY_CT_Nhap
AFTER INSERT OR UPDATE OR DELETE
ON CT_Nhap
FOR EACH ROW
DECLARE v_current_quantity NUMBER;
BEGIN
    -- Khi INSERT: Cộng số lượng vào kho
    IF INSERTING OR UPDATING THEN
        BEGIN 
            -- Khóa hàng trong VatTu
            SELECT SoLuong 
            INTO v_current_quantity 
            FROM VatTu 
            WHERE MaVT = :NEW.MaVT
            FOR UPDATE;
        END;
    ELSIF DELETING THEN
        BEGIN
            -- Khóa hàng trong VatTu
            SELECT SoLuong 
            INTO v_current_quantity 
            FROM VatTu 
            WHERE MaVT = :OLD.MaVT
            FOR UPDATE;
        END;
    END IF;
    
    IF INSERTING THEN
        BEGIN
            -- Cập nhật số lượng
            UPDATE VatTu
            SET SoLuong = v_current_quantity + :NEW.SL
            WHERE MaVT = :NEW.MaVT;
        END;
        
        -- Khi UPDATE: Trừ số lượng cũ, sau đó cộng số lượng mới
    ELSIF UPDATING THEN
        BEGIN
            -- Cập nhật số lượng
            UPDATE VatTu
            SET SoLuong = v_current_quantity + :NEW.SL - :OLD.SL
            WHERE MaVT = :NEW.MaVT;
            
        END;
        
        -- Khi DELETE: Trừ số lượng khỏi kho
    ELSIF DELETING THEN
        BEGIN
            -- Cập nhật số lượng
            UPDATE VatTu
            SET SoLuong = v_current_quantity - :OLD.SL
            WHERE MaVT = :OLD.MaVT;
        
        END;  
    END IF;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20004, 'Không tìm thấy vật tư với MaVT tương ứng');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Lỗi khi cập nhật số lượng vật tư: ' || SQLERRM);
END;
/


--TREN BANG CT_Xuat
CREATE OR REPLACE TRIGGER TRG_UPDATE_INVENTORY 
AFTER INSERT OR UPDATE OR DELETE
ON CT_Xuat
FOR EACH ROW
DECLARE v_current_quantity NUMBER;
BEGIN
    IF INSERTING OR UPDATING THEN
        BEGIN
            SELECT SoLuong 
            INTO v_current_quantity
            FROM VatTu
            WHERE MaVT = :NEW.MaVT
            FOR UPDATE;
        END;
    ELSIF DELETING THEN
        BEGIN
            SELECT SoLuong 
            INTO v_current_quantity
            FROM VatTu
            WHERE MaVT = :OLD.MaVT
            FOR UPDATE;
        END;
    END IF;

    -- Khi INSERT: Trừ số lượng khỏi kho
    IF INSERTING THEN
        BEGIN
            UPDATE VatTu 
            SET SoLuong = SoLuong - :NEW.SL
            WHERE MaVT = :NEW.MaVT;
        END;
    
    -- Khi UPDATE: Trừ số lượng mới, sau đó cộng số lượng cũ
    ELSIF UPDATING THEN
        BEGIN
            UPDATE VatTu 
            SET SoLuong = SoLuong - :NEW.SL + :OLD.SL
            WHERE MaVT = :NEW.MaVT;
        
        END;
    
    -- Khi DELETE: Cộng số lượng vào kho 
    ELSIF DELETING THEN
        BEGIN
            UPDATE VatTu 
            SET SoLuong = SoLuong + :OLD.SL
            WHERE MaVT = :OLD.MaVT;  

        END;
    END IF;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20004, 'Không tìm thấy vật tư với MaVT tương ứng');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Lỗi khi cập nhật số lượng vật tư: ' || SQLERRM);
END;
/


--Cập nhật đơn giá xuất = đơn giá nhập * 1.2 nếu đơn giá xuất chưa được nhập.
CREATE OR REPLACE TRIGGER trg_update_export_price
BEFORE INSERT OR UPDATE
ON VatTu    
FOR EACH ROW
BEGIN
    -- Kiểm tra và cập nhật DonGiaXuat
    IF :NEW.DonGiaNhap IS NOT NULL THEN
        IF :NEW.DonGiaXuat IS NULL OR :NEW.DonGiaXuat <> :NEW.DonGiaNhap * 1.2 THEN
            :NEW.DonGiaXuat := :NEW.DonGiaNhap * 1.2;
        END IF;
    ELSE
        RAISE_APPLICATION_ERROR(-20001, 'Đơn giá nhập không được NULL');
    END IF;

    -- Ghi lịch sử cập nhật khi thay đổi DonGiaNhap
    IF UPDATING AND :OLD.DonGiaNhap != :NEW.DonGiaNhap THEN
        INSERT INTO LichSuCapNhat (MaVT, GiaCu, GiaMoi)
        VALUES (:NEW.MaVT, :OLD.DonGiaNhap, :NEW.DonGiaNhap);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Lỗi trong trigger: ' || SQLERRM);
END;
/



--***2. Trigger trên bảng GiaoDich
--Không cho phép thêm hay sửa giao dịch xuất khi số lượng vật tư trong kho không đủ.
--CT_Nhap
CREATE OR REPLACE TRIGGER trg_prevent_insufficient_stock_CT_Nhap
BEFORE DELETE OR UPDATE
ON CT_Nhap
FOR EACH ROW
DECLARE
    v_available_quantity NUMBER;
BEGIN
    -- Lấy số lượng hiện có trong kho của mặt hàng cần xuất 
    IF UPDATING THEN
        BEGIN
            SELECT SoLuong 
            INTO v_available_quantity
            FROM VatTu
            WHERE MaVT = :NEW.MaVT
            FOR UPDATE;
        END;
    ELSIF DELETING THEN
        BEGIN
            SELECT SoLuong 
            INTO v_available_quantity
            FROM VatTu
            WHERE MaVT = :OLD.MaVT
            FOR UPDATE;
        END;
    END IF;
    
    IF UPDATING THEN
        IF v_available_quantity + :NEW.SL - :OLD.SL < 0 THEN
            RAISE_APPLICATION_ERROR(-20005, 'Số lượng vật tư trong kho không đủ để xuất!');
        END IF;
    ELSIF DELETING THEN
        IF v_available_quantity - :OLD.SL < 0 THEN
            RAISE_APPLICATION_ERROR(-20005, 'Số lượng vật tư trong kho không đủ để xuất!');
        END IF;
    END IF;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20004, 'Không tìm thấy vật tư với MaVT tương ứng');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Lỗi khi cập nhật số lượng vật tư: ' || SQLERRM);
END;
/

-- CT_Xuat
CREATE OR REPLACE TRIGGER trg_prevent_insufficient_stock_CT_Xuat 
BEFORE INSERT OR UPDATE
ON CT_Xuat
FOR EACH ROW
DECLARE
    v_available_quantity NUMBER;
BEGIN
    -- Lấy số lượng hiện có trong kho của mặt hàng cần xuất 
    SELECT SoLuong 
    INTO v_available_quantity
    FROM VatTu
    WHERE MaVT = :NEW.MaVT
    FOR UPDATE;
    
    IF :NEW.SL - NVL(:OLD.SL, 0) > v_available_quantity THEN
        RAISE_APPLICATION_ERROR(-20005, 'Số lượng vật tư trong kho không đủ để xuất!');
    END IF;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20004, 'Không tìm thấy vật tư với MaVT tương ứng');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Lỗi khi cập nhật số lượng vật tư: ' || SQLERRM);
END;
/

--Không cho phép thay đổi LoaiGD.
CREATE TRIGGER Khong_Update_LoaiGD
BEFORE UPDATE
ON GiaoDich
FOR EACH ROW
BEGIN
    IF :NEW.LoaiGD <> :OLD.LoaiGD THEN
        RAISE_APPLICATION_ERROR(-20002, 'Không cho phép thay đổi LoaiGD!');
    END IF;
END;
/

--Tự động tính tổng tiền giao dịch dựa trên chi tiết nhập/xuất.
CREATE OR REPLACE TRIGGER trg_UpdateThanhTien_Xuat
FOR INSERT OR UPDATE OR DELETE
ON CT_Xuat
COMPOUND TRIGGER

    -- Tạo ra mảng lưu trữ giá trị thành tiền.
    TYPE t_CT_Xuat_ThanhTien IS TABLE OF CT_Xuat.THANHTIEN%TYPE INDEX BY PLS_INTEGER;
    TYPE t_CT_Xuat_MaGD IS TABLE OF CT_Xuat.MAGD%TYPE INDEX BY PLS_INTEGER;

    v_CT_Xuat_ThanhTien t_CT_Xuat_ThanhTien;
    v_CT_Xuat_MaGD t_CT_Xuat_MaGD;

    AFTER EACH ROW IS
    BEGIN
        IF INSERTING OR UPDATING THEN
        BEGIN
            v_CT_Xuat_ThanhTien(v_CT_Xuat_ThanhTien.COUNT + 1) := :NEW.ThanhTien - NVL(:OLD.ThanhTien, 0);
            v_CT_Xuat_MaGD(v_CT_Xuat_MaGD.COUNT + 1) := :NEW.MaGD;
        END;
        ELSIF DELETING THEN
        BEGIN
            v_CT_Xuat_ThanhTien(v_CT_Xuat_ThanhTien.COUNT + 1) := :OLD.ThanhTien;
            v_CT_Xuat_MaGD(v_CT_Xuat_MaGD.COUNT + 1) := :OLD.MaGD;
        END;
        END IF;
    END AFTER EACH ROW;

    AFTER STATEMENT IS
    BEGIN
        FOR i IN 1 .. v_CT_Xuat_MaGD.COUNT  
        LOOP
            -- Tạo biến tạm.
            DECLARE v_temp NUMBER;
            BEGIN
                -- Lock các row theo MaGD và MaVT
                SELECT 1 INTO v_temp
                FROM GIAODICH
                WHERE MaGD = v_CT_Xuat_MaGD(i) 
                FOR UPDATE;

                -- Tính Thành Tiền vào Giao Dịch.
                IF INSERTING OR UPDATING THEN
                    UPDATE GIAODICH
                    SET THANHTIEN = THANHTIEN + v_CT_Xuat_ThanhTien(i)
                    WHERE MaGD = v_CT_Xuat_MaGD(i);
                ELSIF DELETING THEN
                    UPDATE GIAODICH
                    SET THANHTIEN = THANHTIEN - v_CT_Xuat_ThanhTien(i)
                    WHERE MAGD = v_CT_Xuat_MaGD(i);
                END IF;

                EXCEPTION 
                    WHEN NO_DATA_FOUND THEN
                        DBMS_OUTPUT.PUT_LINE('Không tìm thấy dữ liệu cho MaGD: ' || v_CT_Xuat_MaGD(i));
                    WHEN TOO_MANY_ROWS THEN
                        DBMS_OUTPUT.PUT_LINE('Nhiều dòng dữ liệu trùng MaGD: ' || v_CT_Xuat_MaGD(i));
                    WHEN OTHERS THEN
                        DBMS_OUTPUT.PUT_LINE('Lỗi khác xảy ra: ' || SQLERRM);
            END;
        END LOOP;
    END AFTER STATEMENT;
END trg_UpdateThanhTien_Xuat;
/

CREATE OR REPLACE TRIGGER trg_UpdateThanhTien_Nhap
FOR INSERT OR UPDATE OR DELETE
ON CT_Nhap
COMPOUND TRIGGER

    -- Tạo ra mảng lưu trữ giá trị thành tiền.
    TYPE t_CT_Nhap_ThanhTien IS TABLE OF CT_Nhap.THANHTIEN%TYPE INDEX BY PLS_INTEGER;
    TYPE t_CT_Nhap_MaGD IS TABLE OF CT_Nhap.MAGD%TYPE INDEX BY PLS_INTEGER;

    v_CT_Nhap_ThanhTien t_CT_Nhap_ThanhTien;
    v_CT_Nhap_MaGD t_CT_Nhap_MaGD;

    AFTER EACH ROW IS
    BEGIN
        IF INSERTING OR UPDATING THEN
        BEGIN
            v_CT_Nhap_ThanhTien(v_CT_Nhap_ThanhTien.COUNT + 1) := :NEW.ThanhTien - NVL(:OLD.ThanhTien, 0);
            v_CT_Nhap_MaGD(v_CT_Nhap_MaGD.COUNT + 1) := :NEW.MaGD;
        END;
        ELSIF DELETING THEN
        BEGIN
            v_CT_Nhap_ThanhTien(v_CT_Nhap_ThanhTien.COUNT + 1) := :OLD.ThanhTien;
            v_CT_Nhap_MaGD(v_CT_Nhap_MaGD.COUNT + 1) := :OLD.MaGD;
        END;
        END IF;
    END AFTER EACH ROW;

    AFTER STATEMENT IS
    BEGIN
        FOR i IN 1 .. v_CT_Nhap_MaGD.COUNT  
        LOOP
            -- Tạo biến tạm.
            DECLARE v_temp NUMBER;
            BEGIN
                -- Lock các row theo MaGD và MaVT
                SELECT 1 INTO v_temp
                FROM GIAODICH
                WHERE MaGD = v_CT_Nhap_MaGD(i) 
                FOR UPDATE;

                -- Tính Thành Tiền vào Giao Dịch.
                IF INSERTING OR UPDATING THEN
                    UPDATE GIAODICH
                    SET THANHTIEN = THANHTIEN + v_CT_Nhap_ThanhTien(i)
                    WHERE MaGD = v_CT_Nhap_MaGD(i);
                ELSIF DELETING THEN
                    UPDATE GIAODICH
                    SET THANHTIEN = THANHTIEN - v_CT_Nhap_ThanhTien(i)
                    WHERE MAGD = v_CT_Nhap_MaGD(i);
                END IF;

                EXCEPTION 
                    WHEN NO_DATA_FOUND THEN
                        DBMS_OUTPUT.PUT_LINE('Không tìm thấy dữ liệu cho MaGD: ' || v_CT_Nhap_MaGD(i));
                    WHEN TOO_MANY_ROWS THEN
                        DBMS_OUTPUT.PUT_LINE('Nhiều dòng dữ liệu trùng MaGD: ' || v_CT_Nhap_MaGD(i));
                    WHEN OTHERS THEN
                        DBMS_OUTPUT.PUT_LINE('Lỗi khác xảy ra: ' || SQLERRM);
            END;
        END LOOP;
    END AFTER STATEMENT;
END trg_UpdateThanhTien_Nhap;
/

--***3. Trigger trên bảng CT_Nhap
--Tự động tính tổng tiền cho CT_Nhap
CREATE OR REPLACE TRIGGER TONGTIEN_CT_NHAP
BEFORE INSERT OR UPDATE
ON CT_Nhap
FOR EACH ROW
DECLARE 
    v_DonGiaNhap NUMBER;
BEGIN
    SELECT DonGiaNhap 
    INTO v_DonGiaNhap 
    FROM VatTu 
    WHERE MaVT = :NEW.MaVT
    FOR UPDATE;

    -- Tính ThanhTien trước khi dữ liệu được insert/update vào bảng CT_Nhap
    :NEW.ThanhTien := :NEW.SL * v_DonGiaNhap;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20003, 'Không tìm thấy đơn giá nhập cho vật tư ' || :NEW.MaVT);
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Lỗi khi tính tổng tiền CT_Nhap: ' || SQLERRM);
END;
/

--Kiểm tra xem mã NCC có tồn tại không trước khi thêm chi tiết nhập.
CREATE OR REPLACE TRIGGER trg_check_NCC_exist_CT_Nhap
BEFORE INSERT OR UPDATE
ON CT_Nhap
FOR EACH ROW
DECLARE v_count NUMBER;
BEGIN
    -- Kiểm tra mã khách hàng có tồn tại không
    SELECT COUNT(*) INTO v_count FROM NhaCungCap WHERE MaNCC = :NEW.MaNCC;
    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Không thể nhập hàng vì mã nhà cung cấp không tồn tại!');
    END IF;
END;
/

--***4. Trigger trên bảng CT_Xuat
--Tự động tính tổng tiền cho CT_Xuat
CREATE OR REPLACE TRIGGER TONGTIEN_CT_XUAT
BEFORE INSERT OR UPDATE
ON CT_Xuat
FOR EACH ROW
DECLARE
    v_DonGiaXuat NUMBER;
BEGIN
    -- Lấy đơn giá xuất từ bảng VatTu
    SELECT DonGiaXuat
    INTO v_DonGiaXuat
    FROM VatTu
    WHERE MaVT = :NEW.MaVT
    FOR UPDATE;

    -- Gán giá trị ThanhTien trực tiếp
    :NEW.ThanhTien := :NEW.SL * v_DonGiaXuat;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20003, 'Không tìm thấy đơn giá xuất cho vật tư ' || :NEW.MaVT);
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Lỗi trong trigger TONGTIEN_CT_XUAT: ' || SQLERRM);
END TONGTIEN_CT_XUAT;
/


--Không cho phép xuất hàng nếu mã khách hàng không tồn tại.
CREATE OR REPLACE TRIGGER trg_check_customer_exist_CT_Xuat
BEFORE INSERT OR UPDATE
ON CT_Xuat
FOR EACH ROW
DECLARE v_count NUMBER;
BEGIN
    -- Kiểm tra mã khách hàng có tồn tại không
    SELECT COUNT(*) INTO v_count FROM KhachHang WHERE MaKH = :NEW.MaKH;
    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Không thể xuất hàng vì mã khách hàng không tồn tại!');
    END IF;
END;
/

--***5. Trigger trên bảng LichSuKiemKe
--Ghi nhận lịch sử kiểm kê nếu có thay đổi số lượng vật tư.

--Không cho phép kiểm kê vật tư không tồn tại trong bảng VatTu.
CREATE OR REPLACE TRIGGER trg_Check_KiemKeVatTu_KhongTonTai
BEFORE INSERT OR UPDATE
ON LichSuKiemKe
FOR EACH ROW
DECLARE v_MaVT NUMBER;
BEGIN
    SELECT MaVT INTO v_MaVT FROM VatTu WHERE MaVT = :NEW.MaVT;
    IF v_MaVT IS NULL THEN
        RAISE_APPLICATION_ERROR(-20006, 'Không cho phép kiểm kê vật tư không tồn tại trong bảng VatTu!');
    END IF;
END;
/


--Không cho phép tạo giao dịch với ngày trong tương lai (Thời gian phải nhỏ hơn hoặc bằng SYSDATE).
CREATE OR REPLACE TRIGGER trg_check_ngaygiaodich_GiaoDich
BEFORE INSERT OR UPDATE
ON GiaoDich
FOR EACH ROW
BEGIN
    -- Kiểm tra ngày giao dịch không được lớn hơn SYSDATE
    IF :NEW.ThoiGian > SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20007, 'Không thể tạo giao dịch với ngày trong tương lai!');
    END IF;
END;
/

--Không cho phép chỉnh sửa ngày giao dịch sau khi đã tạo.
CREATE OR REPLACE TRIGGER trg_prevent_update_ngaygiaodich_GiaoDich
BEFORE UPDATE ON GiaoDich
FOR EACH ROW
BEGIN
    -- Kiểm tra nếu giá trị cũ và mới của NgayGiaoDich khác nhau
    IF :OLD.ThoiGian <> :NEW.ThoiGian THEN
        RAISE_APPLICATION_ERROR(-20008, 'Không thể chỉnh sửa ngày giao dịch sau khi đã tạo!');
    END IF;
END;
/

SELECT l.session_id, l.locked_mode, s.serial#, s.username, s.status
FROM dba_objects o
JOIN v$locked_object l ON o.object_id = l.object_id
JOIN v$session s ON s.sid = l.session_id;
ALTER SYSTEM KILL SESSION '515,1289' IMMEDIATE;
SELECT sid, serial#, username, status, osuser, machine, program 
FROM v$session
WHERE status = 'ACTIVE';
commit;