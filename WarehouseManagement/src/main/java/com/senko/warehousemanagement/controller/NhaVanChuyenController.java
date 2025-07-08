
package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.NhaVanChuyenDAO;
import com.senko.warehousemanagement.model.entities.NhaVanChuyen;
import java.util.ArrayList;


public class NhaVanChuyenController {
    private NhaVanChuyenDAO model;
    
    public NhaVanChuyenController(){
        model = new NhaVanChuyenDAO();
    }
    
    public Object[][] getNhaVanChuyenFromModel(){
        ArrayList<NhaVanChuyen> nhaVanChuyenList = model.getAllNhaVanChuyen();
        
        int colNum = 3;
        int rowNum = nhaVanChuyenList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = nhaVanChuyenList.get(i).getMaNhaVanChuyen();
            data[i][1] = nhaVanChuyenList.get(i).getTenNhaVanChuyen();
            data[i][2] = nhaVanChuyenList.get(i).getSdt();
            
        }
        return data;
    }
    
    public boolean themNhaVanChuyenVaoModel(String tenNhaVanChuyen, String soDienThoai){
        if(tenNhaVanChuyen.trim().isEmpty()||soDienThoai.trim().isEmpty()){
            return false;
        }
        try{
            model.insertNhaVanChuyen(tenNhaVanChuyen, soDienThoai);
            return true;
        }   
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean xoaNhaVanChuyen(int maNhaVanChuyen){
        try{
            model.deleteNhaVanChuyen(maNhaVanChuyen);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
