
package com.senko.warehousemanagement.model.entities;

import java.time.LocalDate;


public class LichSuCapNhatGia {
    private int maLichSuCapNhatGia;
    private String vatTu;
    private long giaCu;
    private long giaMoi;
    private LocalDate ngayCapNhat;

    public LichSuCapNhatGia(int maLichSuCapNhatGia, String vatTu, long giaCu, long giaMoi, LocalDate ngayCapNhat) {
        this.maLichSuCapNhatGia = maLichSuCapNhatGia;
        this.vatTu = vatTu;
        this.giaCu = giaCu;
        this.giaMoi = giaMoi;
        this.ngayCapNhat = ngayCapNhat;
    }

    public int getMaLichSuCapNhatGia() {
        return maLichSuCapNhatGia;
    }

    public void setMaLichSuCapNhatGia(int maLichSuCapNhatGia) {
        this.maLichSuCapNhatGia = maLichSuCapNhatGia;
    }

    public String getVatTu() {
        return vatTu;
    }

    public void setVatTu(String vatTu) {
        this.vatTu = vatTu;
    }

    public long getGiaCu() {
        return giaCu;
    }

    public void setGiaCu(long giaCu) {
        this.giaCu = giaCu;
    }

    public long getGiaMoi() {
        return giaMoi;
    }

    public void setGiaMoi(long giaMoi) {
        this.giaMoi = giaMoi;
    }

    public LocalDate getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    
}
