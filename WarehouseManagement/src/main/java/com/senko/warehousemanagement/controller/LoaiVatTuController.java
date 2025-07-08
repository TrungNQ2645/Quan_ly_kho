
package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.LoaiVatTuDAO;
import com.senko.warehousemanagement.model.entities.LoaiVatTu;
import java.util.ArrayList;


public class LoaiVatTuController {
    private LoaiVatTuDAO model;
    
    public LoaiVatTuController(){
        model = new LoaiVatTuDAO();
    }
    
    public Object[][] getLoaiVatTuFromModel(){
        ArrayList<LoaiVatTu> loaiVatTuList = model.getAllLoaiVatTu();
        
        int colNum = 2;
        int rowNum = loaiVatTuList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = loaiVatTuList.get(i).getMaLoaiVT();
            data[i][1] = loaiVatTuList.get(i).getTenLoaiVT();
            
        }
        return data;
    }
    
    public boolean themLoaiVatTuVaoModel(String tenLoaiVatTu){
        if(tenLoaiVatTu.trim().isEmpty()){
            return false;
        }
        try{
            model.insertLoaiVatTu(tenLoaiVatTu);
            return true;
        }   
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean xoaLoaiVatTu(int maLoaiVatTu){
        try{
            model.deleteLoaiVatTu(maLoaiVatTu);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
