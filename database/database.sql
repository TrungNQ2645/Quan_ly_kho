-- Tạo bảng LoaiVT
CREATE TABLE LoaiVT (
    MaLVT NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TenLVT VARCHAR2(100) NOT NULL
);

-- Tạo bảng NhaCungCap
CREATE TABLE NhaCungCap (
    MaNCC NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TenNCC VARCHAR2(100) NOT NULL,
    SDT VARCHAR2(15)
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
    TenDangNhap VARCHAR2(50) NOT NULL,
    MatKhau VARCHAR2(50) NOT NULL,
    Email VARCHAR2(100) NOT NULL,
    CONSTRAINT CK_ChucVu CHECK (ChucVu IN ('KeToan', 'NhapXuat', 'NghiemThu','Admin'))
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
CREATE INDEX IDX_GiaoDich_MaNV ON GiaoDich(MaNV);

-- LichSuCapNhat
CREATE INDEX IDX_LichSuCapNhat_MaVT ON LichSuCapNhat(MaVT);
CREATE INDEX IDX_LichSuCapNhat_NgayCapNhat ON LichSuCapNhat(NgayCapNhat);

-- CT_Nhap
CREATE INDEX IDX_CT_Nhap_MaGD ON CT_Nhap(MaGD);
CREATE INDEX IDX_CT_Nhap_MaVT ON CT_Nhap(MaVT);
CREATE INDEX IDX_CT_Nhap_MaNCC ON CT_Nhap(MaNCC);

-- CT_Xuat
CREATE INDEX IDX_CT_Xuat_MaGD ON CT_Xuat(MaGD);
CREATE INDEX IDX_CT_Xuat_MaVT ON CT_Xuat(MaVT);
CREATE INDEX IDX_CT_Xuat_MaKH ON CT_Xuat(MaKH);

-- LichSuKiemKe
CREATE INDEX IDX_LichSuKiemKe_MaNV ON LichSuKiemKe(MaNV);
CREATE INDEX IDX_LichSuKiemKe_MaVT ON LichSuKiemKe(MaVT);
CREATE INDEX IDX_LichSuKiemKe_ThoiGian ON LichSuKiemKe(ThoiGian);


-- Xóa các bảng có phụ thuộc khóa ngoại trước
--DROP TABLE CT_Xuat CASCADE CONSTRAINTS;
--DROP TABLE CT_Nhap CASCADE CONSTRAINTS;
--DROP TABLE LichSuCapNhat CASCADE CONSTRAINTS;
--DROP TABLE LichSuKiemKe CASCADE CONSTRAINTS;
--DROP TABLE GiaoDich CASCADE CONSTRAINTS;
--DROP TABLE VatTu CASCADE CONSTRAINTS;
--DROP TABLE NhanVien CASCADE CONSTRAINTS;
--DROP TABLE KhachHang CASCADE CONSTRAINTS;
--DROP TABLE NhaCungCap CASCADE CONSTRAINTS;
--DROP TABLE NhaVanChuyen CASCADE CONSTRAINTS;
--DROP TABLE LoaiVT CASCADE CONSTRAINTS;

-- Bảng LoaiVT
INSERT INTO LoaiVT (TenLVT) VALUES ('Vật liệu xây dựng');
INSERT INTO LoaiVT (TenLVT) VALUES ('Gỗ, ván ép');
INSERT INTO LoaiVT (TenLVT) VALUES ('Nhựa, cao su');
INSERT INTO LoaiVT (TenLVT) VALUES ('Nội thất');
INSERT INTO LoaiVT (TenLVT) VALUES ('Linh kiện cơ khí');
INSERT INTO LoaiVT (TenLVT) VALUES ('Linh kiện điện tử');
INSERT INTO LoaiVT (TenLVT) VALUES ('Thiết bị điện tử');
INSERT INTO LoaiVT (TenLVT) VALUES ('Thiết bị gia dụng');
INSERT INTO LoaiVT (TenLVT) VALUES ('Máy móc công nghiệp');
INSERT INTO LoaiVT (TenLVT) VALUES ('Nhiên liệu');
INSERT INTO LoaiVT (TenLVT) VALUES ('Hóa chất');
INSERT INTO LoaiVT (TenLVT) VALUES ('Nông nghiệp, sinh học');
INSERT INTO LoaiVT (TenLVT) VALUES ('Vật tư đóng gói');
INSERT INTO LoaiVT (TenLVT) VALUES ('Đồ bảo hộ');
INSERT INTO LoaiVT (TenLVT) VALUES ('Đồ may mặc');
INSERT INTO LoaiVT (TenLVT) VALUES ('Thuốc men');
INSERT INTO LoaiVT (TenLVT) VALUES ('Dụng cụ y tế');
INSERT INTO LoaiVT (TenLVT) VALUES ('Thực phẩm đông lạnh, đóng hộp');
INSERT INTO LoaiVT (TenLVT) VALUES ('Hàng tiêu dùng');
INSERT INTO LoaiVT (TenLVT) VALUES ('Văn phòng phẩm');
COMMIT;

-- Bảng NhaCungCap
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH ALLYBUILD Minh Mẫn', '0901000001');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty Cổ Phần VLXD Và Khoáng Sản Bình Thuận', '0901000002');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Kho Gỗ TDK FURNI', '0901000003');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty Cổ phần Thiết Bị Và Dịch Vụ Đồng Lợi', '0901000004');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty Cổ phần Kiến trúc Kisato', '0901000005');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Nội Thất Hưng Phát Sài Gòn', '0901000006');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Công Nghiệp Phụ Trợ Tiến Thành', '0901000007');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH MISUMI Việt Nam', '0901000008');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty CP Công Nghiệp G7 Vina', '0901000009');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty CP Linh Kiện Việt Nam', '0901000010');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Một Thành Viên CNTT Thế Giới Di Động', '0901000011');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty CP Thương Mại - Dịch Vụ Phong Vũ', '0901000012');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Cao Phong ', '0901000013');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Một Thành Viên Thương Mại Kết Nối Tiêu Dùng', '0901000014');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Thương Mại Và Dịch Vụ Bếp Gas Bình Minh', '0901000015');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Sơn Tavaco', '0901000016');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Hạt giống HFSeeds', '0901000017');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Công Nghệ Bao Bì Giấy Tiến Phát', '0901000018');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH Sản Xuất Thương Mại Và Xuất Nhập Khẩu An Việt', '0901000019');
INSERT INTO NhaCungCap(TenNCC, SDT) VALUES ('Công ty TNHH UNIQLO Việt Nam', '0901000020');
COMMIT;

-- Bảng KhachHang
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Nguyễn Văn An', 0905123456);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Trần Thị Bích', 0916789123);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Lê Văn Cường', 0923456789);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Phạm Thị Dung', 0934567890);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Hoàng Văn Em', 0945678901);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Đặng Thị Phượng', 0956789012);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Ngô Văn Dũng', 0967890123);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Bùi Thị Hạnh', 0978901234);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Trịnh Văn Lộc', 0989012345);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Vũ Thị Minh', 0990123456);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Đoàn Văn Khải', 0906234567);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Lương Thị Lan', 0917345678);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Tạ Văn Minh', 0928456789);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Trương Thị Ngọc', 0939567890);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Phan Văn Đức', 0940678901);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Huỳnh Thị Phúc', 0951789012);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Cao Văn Hiếu', 0962890123);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Đỗ Thị Hồng', 0973901234);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Trần Văn Sang', 0984012345);
INSERT INTO KhachHang(TenKH, SDT) VALUES ('Lý Thị Trang', 0995123456);
COMMIT;

INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Huỳnh Nguyên Ðan', TO_DATE('2021-05-18', 'YYYY-MM-DD'), 9000000, 'KeToan', 'huynhnguyendan', '123', 'huynhnguyendan@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Lê Văn Hùng', TO_DATE('2020-09-20', 'YYYY-MM-DD'), 13200000, 'KeToan', 'levanhung', '123', 'levanhung@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Phạm Minh Tú', TO_DATE('2023-02-05', 'YYYY-MM-DD'), 11700000, 'NhapXuat', 'phamminhtu', '123', 'phamminhtu@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Trần Minh Hoài Tâm', TO_DATE('2020-06-10', 'YYYY-MM-DD'), 25000000, 'NhapXuat', 'tranminhhoaitam', '123', 'tranminhhoaitam@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Đặng Thị Lan', TO_DATE('2019-11-30', 'YYYY-MM-DD'), 19200000, 'NhapXuat', 'dangthilan', '123', 'dangthilan@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Lê Phước Cấp', TO_DATE('2018-06-02', 'YYYY-MM-DD'), 7100000, 'NghiemThu', 'lephuoccap', '123', 'lephuoccap@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Đỗ Quang Liêm', TO_DATE('2017-04-17', 'YYYY-MM-DD'), 10600000, 'NhapXuat', 'doquangliem', '123', 'doquangliem@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Nguyễn Quốc Trung', TO_DATE('2020-10-29', 'YYYY-MM-DD'), 25000000, 'NhapXuat', 'nguyenquoctrung', '123', 'nguyenquoctrung@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Hoàng Thị Ánh Vân', TO_DATE('2023-09-10', 'YYYY-MM-DD'), 15200000, 'NhapXuat', 'hoangthianhvan', '123', 'hoangthianhvan@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Lương Sĩ Diệt', TO_DATE('2022-12-23', 'YYYY-MM-DD'), 84000000, 'NhapXuat', 'luongsidiet', '123', 'luongsidiet@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Hoàng Xuân Độ', TO_DATE('2019-07-03', 'YYYY-MM-DD'), 69000000, 'NghiemThu', 'hoangxuando', '123', 'hoangxuando@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Nguyễn Minh Tuấn', TO_DATE('2021-01-15', 'YYYY-MM-DD'), 25000000, 'NghiemThu', 'nguyenminhtuan', '123', 'nguyenminhtuan@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Đỗ Tâm Nhuận', TO_DATE('2023-08-12', 'YYYY-MM-DD'), 6500000, 'NhapXuat', 'dotamnhuan', '123', 'dotamnhuan@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Trương Quang Hậu', TO_DATE('2027-05-09', 'YYYY-MM-DD'), 15300000, 'KeToan', 'truongquanghau', '123', 'truongquanghau@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Đinh Thị Thanh Uyên', TO_DATE('2020-01-05', 'YYYY-MM-DD'), 11700000, 'NghiemThu', 'dinhthithanhuyen', '123', 'dinhthithanhuyen@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Đào Thúy Quyên', TO_DATE('2018-08-18', 'YYYY-MM-DD'), 8100000, 'KeToan', 'daothuyquyen', '123', 'daothuyquyen@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Đặng Đình Nhâm', TO_DATE('2016-03-02', 'YYYY-MM-DD'), 9500000, 'NhapXuat', 'dangdinhnham', '123', 'dangdinhnham@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Luong Đức Huân', TO_DATE('2022-11-22', 'YYYY-MM-DD'), 17600000, 'NghiemThu', 'luongduchuan', '123', 'luongduchuan@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Lê Kiều Ý', TO_DATE('2019-10-23', 'YYYY-MM-DD'), 7500000, 'NhapXuat', 'lekieuy', '123', 'lekieuy@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Phan Vĩnh Hùng', TO_DATE('2017-05-07', 'YYYY-MM-DD'), 7300000, 'NghiemThu', 'phanvinhhung', '123', 'phanvinhhung@email.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Hồ Minh Đạt', TO_DATE('2021-04-10', 'YYYY-MM-DD'), 80000000, 'Admin', 'datg', '123', 'senkosannanoja@gmail.com');
INSERT INTO NhanVien(TenNV, NgayVaoLam, Luong, ChucVu, TenDangNhap, MatKhau, Email) VALUES ('Vũ Nhật Hà', TO_DATE('2021-04-11', 'YYYY-MM-DD'), 9000000, 'NhapXuat', 'havn', '123', 'senkosanokaeri@gmail.com');
COMMIT;

-- Bảng NhaVanChuyen
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Công ty Vận tải Hoàng Long', '0905123456');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Vận chuyển Siêu Tốc', '0916789123');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Giao hàng 24h', '0923456789');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Chuyển phát nhanh Việt Nam', '0934567890');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Dịch vụ vận chuyển Á Châu', '0945678901');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('VietExpress Logistics', '0956789012');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Tân Cảng Shipping', '0967890123');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('EMS Quốc Tế', '0978901234');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Giao hàng tiết kiệm', '0989012345');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Vận chuyển Bắc Nam', '0990123456');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Công ty Vận tải An Phát', '0906234567');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Dịch vụ ship hàng ABC', '0917345678');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Vận tải nhanh Đông Nam', '0928456789');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Chuyển hàng Toàn Quốc', '0939567890');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Nhà xe Minh Phát', '0940678901');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Vận tải Thành Đạt', '0951789012');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Công ty vận tải Việt Phát', '0962890123');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Dịch vụ vận tải Hưng Thịnh', '0973901234');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Nhà xe Hoàng Hà', '0984012345');
INSERT INTO NhaVanChuyen(TenNVC, SDT_DaiDien) VALUES ('Giao hàng Công Nghệ', '0995123456');
COMMIT;

-- Bảng VatTu
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Xi măng PCB40 ALLYBUILD bao 50kg', 1, 90000, 1000, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Gạch 4 lỗ Tuynel BCMC 1 viên', 1, 3500, 7500, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Ván ép sợi MDF TDK FURNI 12mm', 2, 200000, 200, 1);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Nhựa PVC Đồng Lợi 1220x2440mm 3mm', 3, 900000, 2000, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Bồn cầu Clara CT-118SS 1 chiếc', 4, 8000000, 100, 1);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Sofa Hưng Phát H-8276B 1 chiếc', 4, 12500000, 150, 1);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Bulong inox Tiến Thành M20x45 1 chiếc', 5, 22767, 5000, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Lò xo Torsion MISUMI 1 chiếc', 5, 2677, 10000, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Dây điện đơn cứng bọc PVC - CV 1x10 1 mét', 6, 47143, 600, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Công tắc nguồn KCD4-201N-B 1 chiếc', 6, 23000, 860, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Điện thoại iPhone 16e 128GB 1 chiếc', 7, 16590000, 500, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Máy in Epson L3250 1 chiếc', 7, 4090000, 300, 1);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Quạt Điều Hòa Paveden PAC-158U 1 chiếc', 8, 3000000, 1000, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Máy khoan động lực Bosch GSB 16RE 1 chiếc', 9, 1579000, 230, 1);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Gas Sài Gòn Petro 12kg', 10, 455000, 100, 1);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Sơn Nippon Spot Less 5 lít', 11, 670000, 420, 1);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Hạt giống ớt đỏ Hàn Quốc 10 hạt', 12, 30000, 190, 1);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Thùng carton FSC 40x30x30cm M90', 13, 9800, 30000, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Quần áo bảo hộ lao động AV02 1 bộ', 14, 190000, 660, 2);
INSERT INTO VatTu (TenVT, MaLVT, DonGiaNhap, SoLuong, TrangThai) VALUES ('Áo Parka Chống Tia UV Bỏ Túi NANO Design 1 chiếc', 15, 784000, 310, 1);
COMMIT;

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

--TRANSACTION NHẬP HÀNG-------------------------------------------------------
CREATE OR REPLACE PROCEDURE TRANSACTION_NHAP_HANG (
    MA_VAT_TU IN VARCHAR2,
    SO_LUONG_NHAP IN NUMBER,
    MA_NHAN_VIEN IN VARCHAR2,
    MA_NHA_CUNG_CAP IN VARCHAR2,
    MA_GIAO_DICH IN VARCHAR2
)
IS
    SO_LUONG_HIEN_CO NUMBER;
BEGIN
    -- Lấy số lượng hiện có và khóa dòng
    SELECT SOLUONG INTO SO_LUONG_HIEN_CO
    FROM VATTU
    WHERE MAVT = MA_VAT_TU
    FOR UPDATE WAIT 5;

    -- Cập nhật số lượng vật tư
    UPDATE VATTU
    SET SOLUONG = SO_LUONG_HIEN_CO + SO_LUONG_NHAP
    WHERE MAVT = MA_VAT_TU;

    -- Thêm giao dịch nhập hàng (nếu cần)
    INSERT INTO GIAODICH(MAGD, LOAIGD) VALUES(MA_GIAO_DICH, 'NHAP');

    -- Thêm chi tiết nhập hàng
    INSERT INTO CT_NHAP VALUES(MA_GIAO_DICH, MA_VAT_TU, SO_LUONG_NHAP, MA_NHAN_VIEN, MA_NHA_CUNG_CAP);

EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE = -60 THEN -- Deadlock
            RAISE_APPLICATION_ERROR(-20002, 'Deadlock');
        ELSE
            RAISE_APPLICATION_ERROR(-20001, SQLERRM);
        END IF;
END TRANSACTION_NHAP_HANG;
/

--TRANSACTION XUẤT HÀNG-------------------------------------------------------
CREATE OR REPLACE PROCEDURE TRANSACTION_XUAT_HANG (
    MA_VAT_TU IN VARCHAR2,
    SO_LUONG_XUAT IN NUMBER,
    MA_NHAN_VIEN IN VARCHAR2,
    MA_KHACH_HANG IN VARCHAR2,
    MA_GIAO_DICH IN VARCHAR2
)
IS
    SO_LUONG_HIEN_CO NUMBER;
    DON_GIA_XUAT NUMBER;
    THANH_TIEN NUMBER;
BEGIN
    -- Lấy đơn giá xuất và số lượng hiện có, khóa dòng
    SELECT DONGIAXUAT, SOLUONG INTO DON_GIA_XUAT, SO_LUONG_HIEN_CO
    FROM VATTU
    WHERE MAVT = MA_VAT_TU
    FOR UPDATE WAIT 5;

    -- Kiểm tra đủ số lượng xuất
    IF SO_LUONG_HIEN_CO < SO_LUONG_XUAT THEN
        RAISE_APPLICATION_ERROR(-20001, 'Không đủ hàng để xuất');
    END IF;

    -- Tính thành tiền
    THANH_TIEN := DON_GIA_XUAT * SO_LUONG_XUAT;

    -- Cập nhật số lượng vật tư
    UPDATE VATTU
    SET SOLUONG = SO_LUONG_HIEN_CO - SO_LUONG_XUAT
    WHERE MAVT = MA_VAT_TU;

    -- Thêm giao dịch xuất hàng (nếu cần)
    INSERT INTO GIAODICH(MAGD, LOAIGD) VALUES(MA_GIAO_DICH, 'XUAT');

    -- Thêm chi tiết xuất hàng
    INSERT INTO CT_XUAT VALUES(MA_GIAO_DICH, MA_VAT_TU, SO_LUONG_XUAT, MA_NHAN_VIEN, THANH_TIEN, MA_KHACH_HANG);

EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE = -60 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deadlock');
        ELSE
            RAISE_APPLICATION_ERROR(-20003, SQLERRM);
        END IF;
END TRANSACTION_XUAT_HANG;
/

--TRANSACTION THAY ĐỔI GIÁ----------------------------------------------
CREATE OR REPLACE PROCEDURE TRANSACTION_THAY_DOI_GIA (
    MA_LICH_SU_CAP_NHAT_GIA IN VARCHAR2,
    MA_VAT_TU IN VARCHAR2,
    GIA_MOI IN NUMBER
)
IS
    GIA_CU NUMBER;
BEGIN
    -- Lấy giá cũ và khóa dòng
    SELECT DONGIAXUAT INTO GIA_CU
    FROM VATTU
    WHERE MAVT = MA_VAT_TU
    FOR UPDATE WAIT 5;

    IF GIA_CU != GIA_MOI THEN
        UPDATE VATTU SET DONGIAXUAT = GIA_MOI WHERE MAVT = MA_VAT_TU;
        INSERT INTO LICHSUCAPNHAT(MALSCN, MAVT, GIACU, GIAMOI)
        VALUES(MA_LICH_SU_CAP_NHAT_GIA, MA_VAT_TU, GIA_CU, GIA_MOI);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE = -60 THEN
            RAISE_APPLICATION_ERROR(-20004, 'Deadlock');
        ELSE
            RAISE_APPLICATION_ERROR(-20005, SQLERRM);
        END IF;
END TRANSACTION_THAY_DOI_GIA;
/

--TRANSACTION KIỂM KÊ SỐ LƯỢNG--------------------------------------------------------
CREATE OR REPLACE PROCEDURE TRANSACTION_KIEM_KE (
    MA_LICH_SU_KIEM_KE IN VARCHAR2,
    MA_NHAN_VIEN IN NUMBER,
    MA_VAT_TU IN NUMBER,
    SO_LUONG_KIEM_KE IN NUMBER,
    TINH_TRANG IN VARCHAR2
)
IS
    SO_LUONG_CON_LAI NUMBER;
BEGIN
    -- Lấy số lượng hiện có và khóa dòng
    SELECT SoLuong INTO SO_LUONG_CON_LAI
    FROM VATTU
    WHERE MaVT = MA_VAT_TU
    FOR UPDATE WAIT 5;

    -- Ghi nhận lịch sử kiểm kê
    INSERT INTO LichSuKiemKe (MaNV, MaVT, SoLuongConLai, TinhTrang)
    VALUES (MA_NHAN_VIEN, MA_VAT_TU, SO_LUONG_KIEM_KE, TINH_TRANG);

    -- Nếu có chênh lệch thì cập nhật lại số lượng
    IF SO_LUONG_CON_LAI != SO_LUONG_KIEM_KE THEN
        UPDATE VATTU
        SET SoLuong = SO_LUONG_KIEM_KE
        WHERE MaVT = MA_VAT_TU;
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE = -60 THEN
            RAISE_APPLICATION_ERROR(-20006, 'Deadlock');
        ELSE
            RAISE_APPLICATION_ERROR(-20007, SQLERRM);
        END IF;
END TRANSACTION_KIEM_KE;
/