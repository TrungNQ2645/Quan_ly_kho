
package com.senko.warehousemanagement.model.entities;


public class ChiTietXuat {
    private int maGiaoDich;
    private String vatTu;
    private int soLuong;
    private long thanhTien;
    private String khachHang;

    public ChiTietXuat(int maGiaoDich, String vatTu, int soLuong, long thanhTien, String khachHang) {
        this.maGiaoDich = maGiaoDich;
        this.vatTu = vatTu;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.khachHang = khachHang;
    }

    public int getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(int maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getVatTu() {
        return vatTu;
    }

    public void setVatTu(String vatTu) {
        this.vatTu = vatTu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }
    
}
