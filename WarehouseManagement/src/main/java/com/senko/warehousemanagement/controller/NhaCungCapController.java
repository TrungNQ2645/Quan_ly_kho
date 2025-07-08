
package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.KhachHangDAO;
import com.senko.warehousemanagement.model.dao.LoaiVatTuDAO;
import com.senko.warehousemanagement.model.dao.NhaCungCapDAO;
import com.senko.warehousemanagement.model.entities.LoaiVatTu;
import com.senko.warehousemanagement.model.entities.NhaCungCap;
import java.util.ArrayList;

public class NhaCungCapController {
    private NhaCungCapDAO model;
    
    public NhaCungCapDAO getModel(){
        return model;
    }
    
    public NhaCungCapController(){
        model = new NhaCungCapDAO();
    }
    
    public Object[][] getNhaCungCapFromModel(){
        ArrayList<NhaCungCap> nhaCungCapList = model.getAllNhaCungCap();
        
        int colNum = 3;
        int rowNum = nhaCungCapList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = nhaCungCapList.get(i).getMaNhaCungCap();
            data[i][1] = nhaCungCapList.get(i).getTenNhaCungCap();
            data[i][2] = nhaCungCapList.get(i).getSoDienThoai();
        }
        return data;
    }
    
    public boolean themNhaCungCapVaoModel(String tenNhaCungCap, String soDienThoai){
        if(tenNhaCungCap.trim().isEmpty()||soDienThoai.trim().isEmpty()){
            return false;
        }
        try{
            model.insertNhaCungCap(tenNhaCungCap,soDienThoai);
            return true;
        }   
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean xoaNhaCungCap(int maNhaCungCap){
        try{
            model.deleteNhaCungCap(maNhaCungCap);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean capNhatNhaCungCapVaoModel(String tenNhaCungCap, String soDienThoai, int maNhaCungCap){
        if(tenNhaCungCap.trim().isEmpty()||soDienThoai.trim().isEmpty()){
            return false;
        }
        try{
            model.updateNhaCungCap(tenNhaCungCap, soDienThoai, maNhaCungCap);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
}
