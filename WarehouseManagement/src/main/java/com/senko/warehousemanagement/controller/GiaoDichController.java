package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.GiaoDichDAO;
import com.senko.warehousemanagement.model.dao.NhaVanChuyenDAO;
import com.senko.warehousemanagement.model.dao.NhanVienDAO;
import com.senko.warehousemanagement.model.entities.GiaoDich;
import java.sql.Date;
import java.util.ArrayList;

public class GiaoDichController {
    private GiaoDichDAO model;
    private NhaVanChuyenDAO modelNVC;
    private NhanVienDAO modelNV;
    
    public GiaoDichController(){
        model = new GiaoDichDAO();
        modelNVC = new NhaVanChuyenDAO();
        modelNV = new NhanVienDAO();
    }
    
    public Object[][] getGiaoDichFromModel(){
        ArrayList<GiaoDich> giaoDichList = model.getAllGiaoDich();
        
        int colNum = 6;
        int rowNum = giaoDichList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = giaoDichList.get(i).getMaGiaoDich();
            data[i][1] = giaoDichList.get(i).getLoaiGiaoDich();
            data[i][2] = giaoDichList.get(i).getThoiGian();
            data[i][3] = giaoDichList.get(i).getThanhTien();
            data[i][4] = giaoDichList.get(i).getNhaVanChuyen();
            data[i][5] = giaoDichList.get(i).getNhanVien();
        }
        return data;
    }
    
    public boolean themGiaoDichVaoModel(String loaiGiaoDich,  String nhaVanChuyen, String nhanVien){
        if(loaiGiaoDich.trim().isEmpty()||nhaVanChuyen.trim().isEmpty()||nhanVien.trim().isEmpty()){
            return false;
        }
        int maNhaVanChuyen = modelNVC.getNhaVanChuyen(nhaVanChuyen);
        int maNhanVien = modelNV.getMaNhanVien(nhanVien);
        try{
            model.insertGiaoDich(loaiGiaoDich, maNhaVanChuyen,maNhanVien);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    public boolean xoaGiaoDich(int maGiaoDich){
        try{
            model.deleteGiaoDich(maGiaoDich);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean capNhatGiaoDichVaoModel(String loaiGiaoDich,  String nhaVanChuyen, String nhanVien, int maGiaoDich){
        if(loaiGiaoDich.trim().isEmpty()||nhaVanChuyen.trim().isEmpty()){
            return false;
        }
        int maNhaVanChuyen = modelNVC.getNhaVanChuyen(nhaVanChuyen);
        int maNhanVien = modelNV.getMaNhanVien(nhanVien);
        try{
            model.updateGiaoDich(loaiGiaoDich, maNhaVanChuyen, maNhanVien, maGiaoDich);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }

    public int getSoLuongTheoLoaiGiaoDich(String loaiGiaoDich) {
        return model.getQuantityByType(loaiGiaoDich);
    }

    public com.senko.warehousemanagement.model.entities.GiaoDich getGiaoDichById(int maGiaoDich) {
        return model.getGiaoDichById(maGiaoDich);
    }
}
