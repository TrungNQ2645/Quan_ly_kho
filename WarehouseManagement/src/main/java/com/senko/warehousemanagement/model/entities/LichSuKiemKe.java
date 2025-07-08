
package com.senko.warehousemanagement.model.entities;

import java.time.LocalDate;


public class LichSuKiemKe {
    private int maLichSuKiemKe;
    private String nhanVien;
    private LocalDate thoiGian;
    private String vatTu;
    private int soLuongConLai;
    private String tinhTrang;

    public LichSuKiemKe(int maLichSuKiemKe, String nhanVien, LocalDate thoiGian, String vatTu, int soLuongConLai, String tinhTrang) {
        this.maLichSuKiemKe = maLichSuKiemKe;
        this.nhanVien = nhanVien;
        this.thoiGian = thoiGian;
        this.vatTu = vatTu;
        this.soLuongConLai = soLuongConLai;
        this.tinhTrang = tinhTrang;
    }

    public int getMaLichSuKiemKe() {
        return maLichSuKiemKe;
    }

    public void setMaLichSuKiemKe(int maLichSuKiemKe) {
        this.maLichSuKiemKe = maLichSuKiemKe;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDate getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalDate thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getVatTu() {
        return vatTu;
    }

    public void setVatTu(String vatTu) {
        this.vatTu = vatTu;
    }

    public int getSoLuongConLai() {
        return soLuongConLai;
    }

    public void setSoLuongConLai(int soLuongConLai) {
        this.soLuongConLai = soLuongConLai;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    
}
