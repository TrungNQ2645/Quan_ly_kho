-- TABLES
-- Tạo bảng LoaiVT
CREATE TABLE LoaiVT (
    MaLVT NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TenLVT VARCHAR2(100) NOT NULL
);

-- Tạo bảng NhaCungCap
CREATE TABLE NhaCungCap (
    MaNCC NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TenNCC VARCHAR2(100) NOT NULL
);

-- Tạo bảng KhachHang
CREATE TABLE KhachHang (
    MaKH NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TenKH VARCHAR2(100) NOT NULL,
    SDT VARCHAR2(15)
);

-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
    MaNV NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TenNV VARCHAR2(100) NOT NULL,
    NgayVaoLam DATE,
    Luong NUMBER,
    ChucVu VARCHAR2(50) NOT NULL,
    CONSTRAINT CK_ChucVu CHECK (ChucVu IN ('KeToan', 'NhapXuat', 'NghiemThu'))
);

-- Tạo bảng NhaVanChuyen
CREATE TABLE NhaVanChuyen (
    MaNVC NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TenNVC VARCHAR2(100) NOT NULL,
    SDT_DaiDien VARCHAR2(15)
);

-- Tạo bảng VatTu
CREATE TABLE VatTu (
    MaVT NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TenVT VARCHAR2(100) NOT NULL,
    MaLVT NUMBER NOT NULL,
    DonGiaNhap NUMBER NOT NULL,
    DonGiaXuat NUMBER,
    SoLuong NUMBER DEFAULT 0,
    TrangThai NUMBER CONSTRAINT CK_TrangThai CHECK(TrangThai Between 0 and 2),
    CONSTRAINT FK_VatTu_LoaiVT FOREIGN KEY (MaLVT) REFERENCES LoaiVT(MaLVT)
);

-- Tạo bảng GiaoDich
CREATE TABLE GiaoDich (
    MaGD NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    LoaiGD VARCHAR2(10) NOT NULL,
    ThoiGian DATE DEFAULT SYSDATE,
    ThanhTien NUMBER DEFAULT 0,
    MaNVC NUMBER NOT NULL,
    MaNV NUMBER NOT NULL, --> Xóa ở CT_Nhap và CT_Xuat thêm ở đây
    CONSTRAINT CK_GiaoDich_LoaiGD CHECK (LoaiGD IN ('Nhap', 'Xuat')),
    CONSTRAINT FK_GiaoDich_NhaVanChuyen FOREIGN KEY (MaNVC) REFERENCES NhaVanChuyen(MaNVC)
);

-- Tạo bảng LichSuCapNhat
CREATE TABLE LichSuCapNhat (
    MaLSCN NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    MaVT NUMBER NOT NULL,
    GiaCu NUMBER,
    GiaMoi NUMBER NOT NULL,
    NgayCapNhat DATE DEFAULT SYSDATE,
    CONSTRAINT FK_LichSuCapNhat_VatTu FOREIGN KEY (MaVT) REFERENCES VatTu(MaVT)
);

-- Tạo bảng CT_Nhap
CREATE TABLE CT_Nhap (
    MaGD NUMBER NOT NULL,
    MaVT NUMBER NOT NULL,
    SL NUMBER NOT NULL,
    ThanhTien NUMBER,
    MaNCC NUMBER NOT NULL,
    CONSTRAINT PK_CT_Nhap PRIMARY KEY (MaGD, MaVT),
    CONSTRAINT FK_CT_Nhap_GiaoDich FOREIGN KEY (MaGD) REFERENCES GiaoDich(MaGD),
    CONSTRAINT FK_CT_Nhap_VatTu FOREIGN KEY (MaVT) REFERENCES VatTu(MaVT),
    CONSTRAINT FK_CT_Nhap_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    CONSTRAINT FK_CT_Nhap_NhaCungCap FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(MaNCC)
);

-- Tạo bảng CT_Xuat
CREATE TABLE CT_Xuat (
    MaGD NUMBER NOT NULL,
    MaVT NUMBER NOT NULL,
    SL NUMBER NOT NULL,
    ThanhTien NUMBER,
    MaKH NUMBER NOT NULL,
    CONSTRAINT PK_CT_Xuat PRIMARY KEY (MaGD, MaVT),
    CONSTRAINT FK_CT_Xuat_GiaoDich FOREIGN KEY (MaGD) REFERENCES GiaoDich(MaGD),
    CONSTRAINT FK_CT_Xuat_VatTu FOREIGN KEY (MaVT) REFERENCES VatTu(MaVT),
    CONSTRAINT FK_CT_Xuat_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    CONSTRAINT FK_CT_Xuat_KhachHang FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);

-- Tạo bảng LichSuKiemKe
CREATE TABLE LichSuKiemKe (
    MaLSKK NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    MaNV NUMBER NOT NULL,
    ThoiGian DATE DEFAULT SYSDATE,
    MaVT NUMBER NOT NULL,
    SoLuongConLai NUMBER,
    TinhTrang VARCHAR2(100),
    CONSTRAINT FK_LichSuKiemKe_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    CONSTRAINT FK_LichSuKiemKe_VatTu FOREIGN KEY (MaVT) REFERENCES VatTu(MaVT)
);


-- Index cho các table

-- KhachHang
CREATE INDEX IDX_KhachHang_SDT ON KhachHang(SDT);

-- NhanVien
CREATE INDEX IDX_NhanVien_ChucVu ON NhanVien(ChucVu);
CREATE INDEX IDX_NhanVien_NgayVaoLam ON NhanVien(NgayVaoLam);

-- NhaVanChuyen
CREATE INDEX IDX_NhaVanChuyen_SDT_DaiDien ON NhaVanChuyen(SDT_DaiDien);

-- VatTu
CREATE INDEX IDX_VatTu_MaLVT ON VatTu(MaLVT);
CREATE INDEX IDX_VatTu_TenVT ON VatTu(TenVT);
CREATE INDEX IDX_VatTu_TrangThai ON VatTu(TrangThai);

-- GiaoDich
CREATE INDEX IDX_GiaoDich_MaNVC ON GiaoDich(MaNVC);
CREATE INDEX IDX_GiaoDich_LoaiGD ON GiaoDich(LoaiGD);
CREATE INDEX IDX_GiaoDich_ThoiGian ON GiaoDich(ThoiGian);

-- LichSuCapNhat
CREATE INDEX IDX_LichSuCapNhat_MaVT ON LichSuCapNhat(MaVT);
CREATE INDEX IDX_LichSuCapNhat_NgayCapNhat ON LichSuCapNhat(NgayCapNhat);

-- CT_Nhap
CREATE INDEX IDX_CT_Nhap_MaGD ON CT_Nhap(MaGD);
CREATE INDEX IDX_CT_Nhap_MaVT ON CT_Nhap(MaVT);
CREATE INDEX IDX_CT_Nhap_MaNV ON CT_Nhap(MaNV);
CREATE INDEX IDX_CT_Nhap_MaNCC ON CT_Nhap(MaNCC);

-- CT_Xuat
CREATE INDEX IDX_CT_Xuat_MaGD ON CT_Xuat(MaGD);
CREATE INDEX IDX_CT_Xuat_MaVT ON CT_Xuat(MaVT);
CREATE INDEX IDX_CT_Xuat_MaNV ON CT_Xuat(MaNV);
CREATE INDEX IDX_CT_Xuat_MaKH ON CT_Xuat(MaKH);

-- LichSuKiemKe
CREATE INDEX IDX_LichSuKiemKe_MaNV ON LichSuKiemKe(MaNV);
CREATE INDEX IDX_LichSuKiemKe_MaVT ON LichSuKiemKe(MaVT);
CREATE INDEX IDX_LichSuKiemKe_ThoiGian ON LichSuKiemKe(ThoiGian);




-- TRIGGER
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



-- PROCEDURE
SET SERVEROUTPUT ON;
--Kiểm tra hàng tồn kho.
CREATE OR REPLACE PROCEDURE proc_Check_Inventory (
    p_MaVT IN NUMBER,
    p_SoLuong OUT NUMBER
)
AS
BEGIN
    SELECT SoLuong
    INTO p_SoLuong
    FROM VatTu
    WHERE MaVT = p_MaVT;

EXCEPTION
    WHEN NO_DATA_FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('Không tìm thấy mặt hàng với ID: ' || p_MaVT);
        p_SoLuong := 0;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Lỗi xảy ra: ' || SQLERRM);
        p_SoLuong := -1;

END proc_Check_Inventory;
/

-- DECLARE
--     v_soluong NUMBER;
-- BEGIN
--     CHECK_INVENTORY(1, v_soluong);
--     DBMS_OUTPUT.PUT_LINE('Số lượng tồn kho: ' || v_soluong);
-- END;
-- /

-- select * from VatTu;


--Cập nhật thông tin hàng hóa (giá, mô tả, số lượng).
CREATE OR REPLACE PROCEDURE proc_CapNhatHangHoa (
    p_MaVT IN VARCHAR2,
    p_TenVT IN VARCHAR2,
    p_DonGiaNhap IN NUMBER,
    p_SoLuong IN NUMBER
)
AS
BEGIN
    -- Cập nhật thông tin hàng hóa (UPDATE tự động khoá hàng cập nhật.)
    UPDATE VATTU
    SET DonGiaNhap = p_DonGiaNhap,
        TenVT = p_TenVT,
        DonGiaXuat = p_DonGiaNhap * 1.2,
        SoLuong = p_SoLuong
    WHERE MaVT = p_MaVT;
    
    -- Hiển thị thông báo cập nhật thành công
    DBMS_OUTPUT.PUT_LINE('Cập nhật thành công vật tư ID: ' || p_MaVT);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Không tìm thấy vật tư với ID: ' || p_MaVT);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Lỗi xảy ra: ' || SQLERRM);
END proc_CapNhatHangHoa;
/

--Xuất báo cáo tồn kho theo từng loại vật tư.
CREATE OR REPLACE PROCEDURE proc_BaoCaoTonKho_LoaiVT
AS
BEGIN
    FOR LVT IN (
        SELECT LoaiVT.MaLVT, LoaiVT.TenLVT, NVL(SUM(VatTu.SoLuong), 0) AS TongSoLuong
        FROM LoaiVT 
        LEFT JOIN VatTu ON LoaiVT.MaLVT = VatTu.MaLVT
        GROUP BY LoaiVT.MaLVT, LoaiVT.TenLVT
    ) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE('Loại vật tư: ' || LVT.MaLVT || ' - ' || LVT.TenLVT ||
                             ' - Tổng tồn kho: ' || LVT.TongSoLuong);
    END LOOP;
END proc_BaoCaoTonKho_LoaiVT;
/


-- BEGIN
--    BAOCAO_TONKHO_LoaiVT;
-- END;
-- /

-- Tính toán và trả về giá trị tổng kho của mỗi loại vật tư.
CREATE OR REPLACE PROCEDURE proc_ThongKeGiaTriKho
AS
    v_GiaTri NUMBER;
BEGIN
    FOR r IN (SELECT MaLVT, SUM(SoLuong * DonGiaNhap) AS GiaTri
              FROM VatTu
              GROUP BY MaLVT)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Loại vật tư: ' || r.MaLVT || ' - Giá trị kho: ' || r.GiaTri);
    END LOOP;
END proc_ThongKeGiaTriKho;
/


-- Lấy ra giá trị số lượng tồn kho theo MaVT
CREATE OR REPLACE PROCEDURE proc_Get_SLTonKho(
    P_MaVT IN NUMBER,
    P_SoLuong OUT NUMBER    
)
AS
BEGIN
    SELECT SoLuong INTO P_SoLuong FROM VatTu WHERE MaVT = P_MaVT;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Không tìm thấy số lượng vật tư với ID: ' || p_MaVT);
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Lỗi xảy ra: ' || SQLERRM);
END proc_Get_SLTonKho;
/

-- Báo cáo Nhập/Xuất Kho theo ngày.
CREATE OR REPLACE PROCEDURE proc_BaoCaoNhapXuatTheoNgay(
    P_Ngay IN DATE
)
AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Báo cáo nhập xuất ngày: ' || TO_CHAR(P_Ngay, 'DD/MM/YYYY'));

    -- Báo cáo nhập kho
    FOR r IN (SELECT MaGD, SUM(SL) AS SL_Nhap, SUM(ThanhTien) AS ThanhTien_Nhap
              FROM CT_Nhap
              WHERE ThoiGian = P_Ngay
              GROUP BY MaGD)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Giao dịch nhập: ' || r.MaGD || ' - Số lượng: ' || r.SL_Nhap || ' - Thành tiền: ' || r.ThanhTien_Nhap);
    END LOOP;

    -- Báo cáo xuất kho
    FOR r IN (SELECT MaGD, SUM(SL) AS SL_Xuat, SUM(ThanhTien) AS ThanhTien_Xuat
              FROM CT_Xuat
              WHERE ThoiGian = P_Ngay
              GROUP BY MaGD)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Giao dịch xuất: ' || r.MaGD || ' - Số lượng: ' || r.SL_Xuat || ' - Thành tiền: ' || r.ThanhTien_Xuat);
    END LOOP;
END proc_BaoCaoNhapXuatTheoNgay;
/



--Truy vấn lịch sử nhập/xuất vật tư.
CREATE OR REPLACE PROCEDURE proc_LichSuGiaoDich(
    p_MaGD IN VARCHAR2
)
AS
     v_LoaiGD VARCHAR2(10);
BEGIN
    SELECT LoaiGD INTO v_LoaiGD FROM GiaoDich WHERE MaGD = p_MaGD;
    IF v_LoaiGD = 'Nhap' THEN
        FOR GD_Nhap IN (
            SELECT GiaoDich.MaGD, LoaiGD, ThoiGian, MaVT, SL, CT_Nhap.ThanhTien, MaNV, MaNCC
            FROM GiaoDich JOIN CT_Nhap 
            ON GiaoDich.MaGD = CT_Nhap.MaGD
            WHERE GiaoDich.MaGD = p_MaGD
        )
        LOOP
            DBMS_OUTPUT.PUT_LINE('Giao dịch: ' || GD_Nhap.MaGD || ' - Loại giao dịch: ' || GD_Nhap.LoaiGD  || ' - Ngày giao dịch: ' || GD_Nhap.ThoiGian ||
            ' - Thành tiền: ' || GD_Nhap.ThanhTien || ' | Vật tư: ' || GD_Nhap.MaVT || ' | Số lượng: ' || GD_Nhap.SL || ' | Nhân viên: ' || GD_Nhap.MaNV ||
            ' | Nhà Cung Cấp: ' || GD_Nhap.MaNCC);
        END LOOP;
    ELSIF v_LoaiGD = 'Xuat' THEN
        FOR GD_Xuat IN (
        SELECT GiaoDich.MaGD, LoaiGD, ThoiGian, MaVT, CT_Xuat.ThanhTien, SL, MaNV, MaKH
        FROM GiaoDich JOIN CT_Xuat 
        ON GiaoDich.MaGD = CT_Xuat.MaGD
        WHERE GiaoDich.MaGD = p_MaGD
        )
        LOOP
            DBMS_OUTPUT.PUT_LINE('Giao dịch: ' || GD_Xuat.MaGD || ' - Loại giao dịch: ' || GD_Xuat.LoaiGD  || ' - Ngày giao dịch: ' || GD_Xuat.ThoiGian ||
                                 ' | Vật tư: ' || GD_Xuat.MaVT || ' | Số lượng: ' || GD_Xuat.SL || ' | Nhân viên: ' || GD_Xuat.MaNV || ' | khách Hàng: ' || GD_Xuat.MaKH);
        END LOOP;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Không tìm thấy giao dịch có mã: ' || p_MaGD);
    END IF;
    
END proc_LichSuGiaoDich;
/

-- Xoá Giao Dịch và các Chi tiết liên quan.
CREATE OR REPLACE PROCEDURE proc_Delete_GiaoDich(
    P_MaGD IN NUMBER
)
AS
    v_LoaiGD GIAODICH.LOAIGD%TYPE;
BEGIN
    -- Kiểm tra xem giao dịch có tồn tại không và lấy loại giao dịch
    SELECT LoaiGD INTO v_LoaiGD
    FROM GIAODICH
    WHERE MaGD = P_MaGD
    FOR UPDATE;

    -- Xoá các chi tiết giao dịch tương ứng
    IF v_LoaiGD = 'Nhap' THEN
        DELETE FROM CT_Nhap WHERE MaGD = P_MaGD;
    ELSIF v_LoaiGD = 'Xuat' THEN
        DELETE FROM CT_Xuat WHERE MaGD = P_MaGD;
    ELSE
        RAISE_APPLICATION_ERROR(-20001, 'Loại giao dịch không hợp lệ: ' || v_LoaiGD);
    END IF;

    -- Xoá bản ghi trong GiaoDich
    DELETE FROM GIAODICH WHERE MaGD = P_MaGD;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Đã xoá giao dịch và chi tiết liên quan với MaGD = ' || P_MaGD);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Không tìm thấy giao dịch có MaGD = ' || P_MaGD);
        ROLLBACK;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Lỗi: ' || SQLERRM);
        ROLLBACK;
END proc_Delete_GiaoDich;
/

-- BEGIN
--     XoaGiaoDich(1);
-- END;
-- /

-- Tìm kiếm vật tư theo MaVT hoặc tên VT
CREATE OR REPLACE PROCEDURE proc_Search_VatTu(
    p_TenVT IN VARCHAR2
)
AS
BEGIN
    SELECT *
    FROM VATTU
    WHERE MaVT LIKE '%' || p_TenVT || '%'
    OR TenVT LIKE '%' || p_TenVT || '%';

    IF OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Không tồn tại vật tư đó trong kho: ' || SQLERRM);
    END IF;
END proc_Search_VatTu;


--Tạo tài khoản người dùng mới trong hệ thống.
CREATE OR REPLACE PROCEDURE proc_Create_User(
    p_TenNguoiDung IN VARCHAR2,
    p_MatKhau IN VARCHAR2,
    p_VaiTro IN VARCHAR2
)
AS
    v_UserCount NUMBER;
BEGIN
    -- Kiểm tra xem user đã tồn tại chưa
    SELECT COUNT(*)
    INTO v_UserCount
    FROM ALL_USERS
    WHERE USERNAME = UPPER(p_TenNguoiDung);
    
    IF v_UserCount > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Người dùng đã tồn tại: ' || p_TenNguoiDung);
    ELSE
        EXECUTE IMMEDIATE 'CREATE USER ' || p_TenNguoiDung || ' IDENTIFIED BY ' || p_MatKhau;    
    
    IF p_VaiTro = 'Admin' THEN
        EXECUTE IMMEDIATE 'GRANT DBA TO ' || p_TenNguoiDung;
    ELSIF p_VaiTro = 'KhachHang' THEN
        EXECUTE IMMEDIATE 'GRANT CONNECT, RESOURCE TO ' || p_TenNguoiDung;
        EXECUTE IMMEDIATE 'GRANT SELECT ON VatTu ' || p_TenNguoiDung;
        EXECUTE IMMEDIATE 'GRANT SELECT ON LoaiVT ' || p_TenNguoiDung;
        EXECUTE IMMEDIATE 'GRANT SELECT ON GiaoDich ' || p_TenNguoiDung;
        EXECUTE IMMEDIATE 'GRANT SELECT ON CT_Nhap ' || p_TenNguoiDung;
        EXECUTE IMMEDIATE 'GRANT SELECT ON CT_Xuat ' || p_TenNguoiDung;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Vai trò không hợp lệ: ' || p_VaiTro);
        RETURN;
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('Đã tạo tài khoản thành công: ' || p_TenNguoiDung);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Lỗi khi tạo tài khoản: ' || SQLERRM);
END proc_Create_User;
/

--BEGIN
--    CREATE_USER('SinhVien01', 'abc123', 'NhanVien');
--END;
--/

-- Thêm quyền cho các nhân viên:
CREATE OR REPLACE PROCEDURE proc_ThemQuyenNV(
    p_MaNV IN VARCHAR2,
    p_TenNguoiDung IN VARCHAR2,
    p_MatKhau IN VARCHAR2
)
AS
    p_ChucVu VARCHAR2(50);
    v_UserCount NUMBER;
BEGIN
    SELECT ChucVu INTO p_ChucVu FROM NhanVien Where MaNV = p_MaNV;
    
    -- Kiểm tra xem user đã tồn tại chưa
    SELECT COUNT(*)
    INTO v_UserCount
    FROM ALL_USERS
    WHERE USERNAME = UPPER(p_TenNguoiDung);
    
    IF v_UserCount > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Người dùng đã tồn tại: ' || p_TenNguoiDung);
    ELSE
        EXECUTE IMMEDIATE 'CREATE USER ' || p_TenNguoiDung || ' IDENTIFIED BY ' || p_MatKhau; 
        EXECUTE IMMEDIATE 'GRANT CONNECT, RESOURCE TO ' || p_TenNguoiDung;
        
        IF p_ChucVu = 'KeToan'  THEN
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON GiaoDich TO ' || p_TenNguoiDung;
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON CT_Nhap TO ' || p_TenNguoiDung;
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON CT_Xuat TO ' || p_TenNguoiDung;    
        ELSIF p_ChucVu = 'NhapXuat' THEN
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON LoaiVT TO ' || p_TenNguoiDung;
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON VatTu TO ' || p_TenNguoiDung;
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON NhaCungCap TO ' || p_TenNguoiDung;
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON KhachHang TO ' || p_TenNguoiDung;
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON NhaVanChuyen TO ' || p_TenNguoiDung;
        ELSIF p_ChucVu = 'NghiemThu' THEN
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON LichSuKiemKe TO ' || p_TenNguoiDung;
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON LoaiVT TO ' || p_TenNguoiDung;
            EXECUTE IMMEDIATE 'GRANT SELECT, INSERT, UPDATE ON VatTu TO ' || p_TenNguoiDung;
        END IF;
    END IF;
END proc_ThemQuyenNV;
/



-- TRANSACTION

--TRANSACTION NHẬP HÀNG-------------------------------------------------------
CREATE OR REPLACE PROCEDURE TRANSACTION_NHAP_HANG
    (MA_VAT_TU IN VARCHAR2,
    SO_LUONG_NHAP IN NUMBER,
    MA_NHAN_VIEN IN VARCHAR2,
    MA_NHA_CUNG_CAP IN VARCHAR2,
    MA_GIAO_DICH IN VARCHAR2,
    MA_NHA_VAN_CHUYEN IN VARCHAR2)
IS
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
BEGIN
    COMMIT;

    --CHỜ 5S
    SELECT SOLUONG INTO SO_LUONG_HIEN_CO
    FROM VATTU
    WHERE MAVT = MA_VAT_TU
    FOR UPDATE WAIT 5;

    SAVEPOINT BEFORE_INSERT;

    --THÊM GIAO DỊCH NHẬP HÀNG
    UPDATE VATTU
    SET SOLUONG = SO_LUONG_HIEN_CO + SO_LUONG
    WHERE MAVT = MA_VAT_TU;
    INSERT INTO GIAODICH(MAGD,LOAIGD, MaNVC, MaNV) VALUES(MA_GIAO_DICH,'NHAP', MA_NHA_VAN_CHUYEN, MA_NHAN_VIEN);
    INSERT INTO CT_NHAP(MaGD, MaVT, SL, MaNCC) VALUES(MA_GIAO_DICH, MA_VAT_TU, SO_LUONG, MA_NHA_CUNG_CAP);

    COMMIT;

    EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE  = -60 THEN --DEADLOCK XẢY RA
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20002, "Deadlock");
            
        ELSE
            ROLLBACK TO BEFORE_INSERT;
        END IF;
END TRANSACTION_NHAP_HANG;

--TRANSACTION XUẤT HÀNG------------------------------------------------------------
CREATE PROCEDURE TRANSACTION_XUAT_HANG
    (MA_VAT_TU IN VARCHAR2,
    SO_LUONG_XUAT IN NUMBER,
    MA_NHAN_VIEN IN VARCHAR2,
    MA_KHACH_HANG IN VARCHAR2,
    MA_GIAO_DICH IN VARCHAR2,
    MA_NHA_VAN_CHUYEN IN VARCHAR2)
IS
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SO_LUONG_HIEN_CO NUMBER;
DON_GIA_XUAT NUMBER;
THANH_TIEN NUMBER:=0;

BEGIN
    COMMIT; 
    --LẤY ĐƠN GIÁ XUẤT VÀ SỐ LƯỢNG HIỆN CÓ

    --CHỜ 5S
    SELECT DONGIAXUAT, SOLUONG INTO DON_GIA_XUAT, SO_LUONG_HIEN_CO
    FROM VATTU
    WHERE MAVT = MA_VAT_TU
    FOR UPDATE WAIT 5;

    --KẾT THÚC NẾU KHÔNG ĐỦ SỐ LƯỢNG
    IF SO_LUONG_HIEN_CO < SO_LUONG_XUAT THEN
    RAISE_APPLICATION_ERROR(-20001, 'Không đủ hàng để xuất');
    END IF;

    SAVEPOINT BEFORE_INSERT;

    --THÊM GIAO DỊCH XUẤT HÀNG
    THANH_TIEN:=DON_GIA_XUAT*SO_LUONG_XUAT;
    UPDATE VATTU
    SET SOLUONG = SO_LUONG_HIEN_CO - SO_LUONG_XUAT
    WHERE MAVT = MA_VAT_TU;
    INSERT INTO GIAODICH(MAGD,LOAIGD, MaNVC, MaNV) VALUES(MA_GIAO_DICH,'XUAT', MA_NHA_VAN_CHUYEN, MA_NHAN_VIEN);
    INSERT INTO CT_XUAT VALUES(MA_GIAO_DICH,MA_VAT_TU, SO_LUONG, THANH_TIEN, MA_KHACH_HANG);

    COMMIT;

    EXCEPTION
        WHEN OTHERS THEN
            IF SQLCODE  = -60 THEN --DEADLOCK XẢY RA
                ROLLBACK;
                RAISE_APPLICATION_ERROR(-20002, "Deadlock");
                
            ELSE
                ROLLBACK TO BEFORE_INSERT;
            END IF;
END TRANSACTION_XUAT_HANG;



--TRANSACTION HỦY GIAO DỊCH----------------------------------------------
CREATE PROCEDURE TRANSACTION_HUY_GIAO_DICH
    (MA_GIAO_DICH IN VARCHAR2,
    MA_VAT_TU IN VARCHAR2
    )
IS
--SET TRANSACTION NAME "HUY_GIAO_DICH";
LOAI_GIAO_DICH VARCHAR2(10);
BEGIN
    COMMIT;

    SELECT LOAIGD INTO LOAI_GIAO_DICH 
    FROM GIAODICH 
    WHERE MAGD = MA_GIAO_DICH
    FOR UPDATE WAIT 5;

    SAVEPOINT BEFORE_DELETE;

    --NẾU LÀ GIAO DỊCH NHẬP
    IF LOAI_GIAO_DICH = 'Nhap' THEN
    UPDATE VATTU
    SET SOLUONG = SOLUONG - (SELECT SL FROM CT_NHAP WHERE MA_GIAO_DICH = MAGD)
    WHERE MA_VAT_TU = MAVT;

    DELETE FROM CT_NHAP WHERE MAGD = MA_GIAO_DICH;
    END IF;

    --NẾU LÀ GIAO DỊCH XUẤT
    IF LOAI_GIAO_DICH = 'Xuat' THEN
    UPDATE VATTU
    SET SOLUONG = SOLUONG + (SELECT SL FROM CT_XUAT WHERE MA_GIAO_DICH = MAGD)
    WHERE MA_VAT_TU = MAVT;

    DELETE FROM CT_XUAT WHERE MAGD = MA_GIAO_DICH;
    END IF;

    --XÓA GIAO DỊCH KHỎI BẢNG GIAO DỊCH;
    DELETE FROM GIAODICH WHERE MAGD = MA_GIAO_DICH;
    COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            IF SQLCODE = -60 THEN
                ROLLBACK;
                RAISE_APPLICATION_ERROR(-20003, 'Deadlock');
            ELSE
                ROLLBACK TO BEFORE_DELETE;
                RAISE;
            END IF;
END TRANSACTION_HUY_GIAO_DICH;


--TRANSACTION THAY DOI GIA----------------------------------------------

CREATE PROCEDURE TRANSACTION_THAY_DOI_GIA
(MA_LICH_SU_CAP_NHAT_GIA IN VARCHAR2,
MA_VAT_TU IN VARCHAR2,
GIA_MOI IN NUMBER)
IS
GIA_CU NUMBER;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

BEGIN
    COMMIT;

    SELECT DONGIAXUAT INTO GIA_CU
    FROM VATTU
    WHERE MAVT = MA_VAT_TU
    FOR UPDATE WAIT 5;

    SAVEPOINT BEFORE_UPDATE;
    IF GIA_CU != GIA_MOI THEN
    UPDATE VATTU
    SET DONGIAXUAT = GIA_MOI;
    WHERE MAVT = MA_VAT_TU

    INSERT INTO LICHSUCAPNHAT(MALSCN, MAVT, GIACU, GIAMOI)
    VALUES(MA_LICH_SU_CAP_NHAT_GIA, MA_VAT_TU, GIA_CU, GIA_CU, GIA_MOI);
    COMMIT;

    EXCEPTION
        WHEN OTHERS THEN
            IF SQLCODE = -60 THEN
                ROLLBACK;
                RAISE_APPLICATION_ERROR(-20004, 'Deadlock');
            ELSE
                ROLLBACK TO BEFORE_UPDATE;
                RAISE;
            END IF;
END TRANSACTION_THAY_DOI_GIA;

--TRANSACTION KIỂM KÊ SỐ LƯỢNG--------------------------------------------------------
CREATE PROCEDURE TRANSACTION_KIEM_KE
(MA_LICH_SU_KIEM_KE IN VARCHAR2,
MA_NHAN_VIEN IN NUMBER,
MA_VAT_TU IN NUMBER
SO_LUONG_KIEM_KE IN NUMBER
TINH_TRANG IN VARCHAR2)
IS
SO_LUONG_CON_LAI NUMBER;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

BEGIN
    COMMIT;

    SELECT SoLuong INTO SO_LUONG_CON_LAI
    FROM VATTU
    WHERE MaVT = MA_VAT_TU
    FOR UPDATE WAIT 5;

    SAVEPOINT BEFORE_KIEMKE;

    INSERT INTO LichSuKiemKe (MaNV, MaVT, SoLuongConLai, TinhTrang)
    VALUES (MA_NV, MA_VT, SO_LUONG_KIEM_KE, TINH_TRANG);

    --NẾU CÓ CHÊNH LỆCH CẬP NHẬT LẠI SỐ LƯỢNG
    IF SO_LUONG_CON_LAI != SO_LUONG_KIEM_KE THEN
        UPDATE VATTU
        SET SoLuong = SO_LUONG_KIEM_KE
        WHERE MaVT = MA_VAT_TU;
    END IF;

    COMMIT;

    EXCEPTION
        WHEN OTHERS THEN
            IF SQLCODE = -60 THEN
                ROLLBACK;
                RAISE_APPLICATION_ERROR(-20004, 'Deadlock');
            ELSE
                ROLLBACK TO BEFORE_KIEMKE;
                RAISE;
            END IF;
END TRANSACTION_KIEM_KE;

--TRANSACTION CẬP NHẬT VỊ TRÍ VÀ LƯƠNG NHÂN VIÊN---------------------------------------------------------------
CREATE OR REPLACE PROCEDURE TRANSACTION_CAP_NHAT_NHAN_VIEN (
    MA_NHAN_VIEN IN NUMBER,
    CHUC_VU_MOI IN VARCHAR2,
    LUONG_MOI IN NUMBER
)
IS
BEGIN
    SAVEPOINT BEFORE_UPDATE;

    -- Cập nhật thông tin nhân viên
    UPDATE NHANVIEN
    SET ChucVu = CHUC_VU_MOI,
        Luong = LUONG_MOI,
    WHERE MANV = MA_NHAN_VIEN;

    -- Kiểm tra có ai bị ảnh hưởng không
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20010, 'Không tìm thấy nhân viên để cập nhật');
    END IF;

    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK TO BEFORE_UPDATE;
        RAISE_APPLICATION_ERROR(SQLERRM);
END TRANSACTION_CAP_NHAT_NHAN_VIEN;