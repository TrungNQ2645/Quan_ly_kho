
package com.senko.warehousemanagement.model.entities;


public class LoaiVatTu {
    private int maLoaiVT;
    private String tenLoaiVT;

    public LoaiVatTu(int maLoaiVT, String tenLoaiVT) {
        this.maLoaiVT = maLoaiVT;
        this.tenLoaiVT = tenLoaiVT;
    }

    public int getMaLoaiVT() {
        return maLoaiVT;
    }

    public void setMaLoaiVT(int maLoaiVT) {
        this.maLoaiVT = maLoaiVT;
    }

    public String getTenLoaiVT() {
        return tenLoaiVT;
    }

    public void setTenLoaiVT(String tenLoaiVT) {
        this.tenLoaiVT = tenLoaiVT;
    }
}
