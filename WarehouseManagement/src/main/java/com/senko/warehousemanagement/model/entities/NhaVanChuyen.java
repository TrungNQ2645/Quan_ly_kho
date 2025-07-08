
package com.senko.warehousemanagement.model.entities;


public class NhaVanChuyen {
    private int maNhaVanChuyen;
    private String tenNhaVanChuyen;
    private String sdt;

    public NhaVanChuyen(int maNhaVanChuyen, String tenNhaVanChuyen, String sdt) {
        this.maNhaVanChuyen = maNhaVanChuyen;
        this.tenNhaVanChuyen = tenNhaVanChuyen;
        this.sdt = sdt;
    }

    public int getMaNhaVanChuyen() {
        return maNhaVanChuyen;
    }

    public void setMaNhaVanChuyen(int maNhaVanChuyen) {
        this.maNhaVanChuyen = maNhaVanChuyen;
    }

    public String getTenNhaVanChuyen() {
        return tenNhaVanChuyen;
    }

    public void setTenNhaVanChuyen(String tenNhaVanChuyen) {
        this.tenNhaVanChuyen = tenNhaVanChuyen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    
}
