
package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.LichSuKiemKeDAO;
import com.senko.warehousemanagement.model.dao.NhanVienDAO;
import com.senko.warehousemanagement.model.dao.VatTuDAO;
import com.senko.warehousemanagement.model.entities.LichSuKiemKe;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class LichSuKiemKeController {
    private LichSuKiemKeDAO model;
    private VatTuDAO modelVT;
    private NhanVienDAO modelNV;
    
    public LichSuKiemKeController(){
        model = new LichSuKiemKeDAO();
        modelVT = new VatTuDAO();
        modelNV = new NhanVienDAO();
    }
    
    public Object[][] getLichSuKiemKeFromModel(){
        ArrayList<LichSuKiemKe> lichSuKiemKeList = model.getAllLichSuKiemKe();
        
        int colNum = 6;
        int rowNum = lichSuKiemKeList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = lichSuKiemKeList.get(i).getMaLichSuKiemKe();
            data[i][1] = lichSuKiemKeList.get(i).getNhanVien();
            data[i][2] = lichSuKiemKeList.get(i).getThoiGian();
            data[i][3] = lichSuKiemKeList.get(i).getVatTu();
            data[i][4] = lichSuKiemKeList.get(i).getSoLuongConLai();
            data[i][5] = lichSuKiemKeList.get(i).getTinhTrang();
            
        }
        return data;
    }
    
    public boolean themLichSuKiemKeVaoModel(String nhanVien, String vatTu, String soLuongConLai, String tinhTrang){
        if(nhanVien.trim().isEmpty()||vatTu.trim().isEmpty()||soLuongConLai.trim().isEmpty()||tinhTrang.trim().isEmpty()){
            return false;
        }
        int maNhanVien = modelNV.getMaNhanVien(nhanVien);
        int maVatTu = modelVT.getMaVatTu(vatTu);
        try{
            model.insertLichSuKiemKe(maNhanVien, maVatTu, Integer.parseInt(soLuongConLai), tinhTrang);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public boolean kiemKe(String nhanVien, String vatTu, String soLuongConLai, String tinhTrang){
        if(nhanVien.trim().isEmpty()||vatTu.trim().isEmpty()||soLuongConLai.trim().isEmpty()||tinhTrang.trim().isEmpty()){
            return false;
        }
        int maNhanVien = modelNV.getMaNhanVien(nhanVien);
        int maVatTu = modelVT.getMaVatTu(vatTu);
        try{
            model.kiemKe(maNhanVien, maVatTu, Integer.parseInt(soLuongConLai), tinhTrang);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
