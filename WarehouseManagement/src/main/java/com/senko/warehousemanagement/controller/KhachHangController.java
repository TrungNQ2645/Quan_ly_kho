
package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.KhachHangDAO;
import com.senko.warehousemanagement.model.entities.KhachHang;
import com.senko.warehousemanagement.model.entities.VatTu;
import java.util.ArrayList;

public class KhachHangController {
    private KhachHangDAO model;
    
    public KhachHangDAO getModel(){
        return model;
    }
    
    public KhachHangController(){
        model = new KhachHangDAO();
    }
    
        public Object[][] getKhachHangFromModel(){
        ArrayList<KhachHang> khachHangList = model.getAllKhachHang();
        
        int colNum = 3;
        int rowNum = khachHangList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = khachHangList.get(i).getMaKhachHang();
            data[i][1] = khachHangList.get(i).getTenKhachHang();
            data[i][2] = khachHangList.get(i).getSoDienThoai();
        }
        return data;
    }
    
    public boolean themKhachHangVaoModel(String tenKhachHang, String soDienThoai){
        if(tenKhachHang.trim().isEmpty()||soDienThoai.trim().isEmpty()){
            return false;
        }
        try{
            model.insertKhachHang(tenKhachHang, soDienThoai);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    public boolean xoaKhachHang(int maKhachHang){
        try{
            model.deleteKhachHang(maKhachHang);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean capNhatKhachHangVaoModel(String tenKhachHang, String soDienThoai, int maKhachHang){
        if(tenKhachHang.trim().isEmpty()||soDienThoai.trim().isEmpty()){
            return false;
        }
        try{
            model.updateKhachHang(tenKhachHang, soDienThoai, maKhachHang);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
}
