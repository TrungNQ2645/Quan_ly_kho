
package com.senko.warehousemanagement.model.entities;

import java.sql.Date;
import java.time.LocalDate;


public class NhanVien {
    private int maNhanVien;
    private String tenNhanVien;
    private LocalDate ngayVaoLam;
    private long luong;
    private String chucVu;
    private String tenDangNhap;
    private String matKhau;
    private String email;


    public NhanVien(int maNhanVien, String tenNhanVien, LocalDate ngayVaoLam, long luong, String chucVu, String tenDangNhap, String matKhau, String email) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngayVaoLam = ngayVaoLam;
        this.luong = luong;
        this.chucVu = chucVu;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public long getLuong() {
        return luong;
    }

    public void setLuong(long luong) {
        this.luong = luong;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    public String getTenDangNhap(){
        return tenDangNhap;
    }
    
    public void setTenDangNhap(String tenDangNhap){
        this.tenDangNhap = tenDangNhap;
    }
    
    public String getMatKhau(){
        return matKhau;
    }
    
    public void setMatKhau(String matKhau){
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
