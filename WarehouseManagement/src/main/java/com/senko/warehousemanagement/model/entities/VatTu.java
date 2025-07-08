
package com.senko.warehousemanagement.model.entities;

public class VatTu {
    private int maVatTu;
    private String tenVatTu;
    private String loaiVT;
    private long donGiaNhap;
    private long donGiaXuat;
    private int soLuong;
    private int trangThai;
    
    public VatTu(int maVatTu, String tenVatTu, String loaiVT, long donGiaNhap, long donGiaXuat, int soLuong, int trangThai) {
        this.maVatTu = maVatTu;
        this.tenVatTu = tenVatTu;
        this.loaiVT = loaiVT;
        this.donGiaNhap = donGiaNhap;
        this.donGiaXuat = donGiaXuat;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }
    
    public VatTu(int maVatTu, String tenVatTu, String loaiVT, long donGiaNhap, long donGiaXuat) {
        this.maVatTu = maVatTu;
        this.tenVatTu = tenVatTu;
        this.loaiVT = loaiVT;
        this.donGiaNhap = donGiaNhap;
        this.donGiaXuat = donGiaXuat;
    }
    
    public int getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(int maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public String getLoaiVT() {
        return loaiVT;
    }

    public void setMaLoaiVT(String loaiVT) {
        this.loaiVT = loaiVT;
    }

    public long getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(long donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }
    
    public long getDonGiaXuat() {
        return donGiaXuat;
    }

    public void setDonGiaXuat(long donGiaXuat) {
        this.donGiaXuat = donGiaXuat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
      
    public int getTrangThai(){
        return trangThai;
    }
    
    public void setTrangThai(int trangThai){
        this.trangThai = trangThai;
    }
}
