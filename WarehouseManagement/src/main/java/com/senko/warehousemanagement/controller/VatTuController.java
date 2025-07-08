package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.LichSuCapNhatGiaDAO;
import com.senko.warehousemanagement.model.entities.VatTu;
import com.senko.warehousemanagement.model.dao.VatTuDAO;
import com.senko.warehousemanagement.model.dao.LoaiVatTuDAO;
import java.util.ArrayList;


public class VatTuController {
    private VatTuDAO model;
    private LoaiVatTuDAO modelLVT;
    private LichSuCapNhatGiaDAO modelCNG;
    
    public VatTuController(){
        model = new VatTuDAO();
        modelLVT = new LoaiVatTuDAO();
        modelCNG = new LichSuCapNhatGiaDAO();
    }
    
    public Object[][] getVatTuFromModel(){
        ArrayList<VatTu> vatTuList = model.getAllVatTu();
        
        int colNum = 7;
        int rowNum = vatTuList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = vatTuList.get(i).getMaVatTu();
            data[i][1] = vatTuList.get(i).getTenVatTu();
            data[i][2] = vatTuList.get(i).getLoaiVT();
            data[i][3] = vatTuList.get(i).getDonGiaNhap();
            data[i][4] = vatTuList.get(i).getDonGiaXuat();
            data[i][5] = vatTuList.get(i).getSoLuong();
            data[i][6] = vatTuList.get(i).getTrangThai();
            
        }
        return data;
    }
    
    public boolean themVatTuVaoModel(String tenVatTu, String loaiVT, String donGiaNhap){
        if(tenVatTu.trim().isEmpty()||loaiVT.trim().isEmpty()||donGiaNhap.trim().isEmpty()){
            return false;
        }
        int maLoaiVatTu = 0;
        maLoaiVatTu = modelLVT.getMaLoaiVatTu(loaiVT.trim());
        System.out.println(maLoaiVatTu);
        try{
            model.insertVatTu(tenVatTu, maLoaiVatTu, Long.parseLong(donGiaNhap));
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    public boolean xoaVatTu(int maVatTu){
        try{
            model.deleteVatTu(maVatTu);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean capNhatVatTuVaoModel(String tenVatTu, String loaiVT, String donGiaNhap, int maVatTu){
        if(tenVatTu.trim().isEmpty()||loaiVT.trim().isEmpty()||donGiaNhap.trim().isEmpty()){
            return false;
        }
        int maLoaiVatTu = 0;
        maLoaiVatTu = modelLVT.getMaLoaiVatTu(loaiVT.trim());
        System.out.println(maLoaiVatTu);
        try{
            modelCNG.thayDoiGia(maVatTu, Long.parseLong(donGiaNhap));
            model.updateVatTu(tenVatTu, maLoaiVatTu, maVatTu);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    public int getSoLuongTheoTrangThai(String trangThai) {
        int trangThaiInt = -1;
        switch (trangThai.toLowerCase()) {
            case "con_hang":
                trangThaiInt = 2;
                break;
            case "het_hang":
                trangThaiInt = 0;
                break;
            case "sap_het":
                trangThaiInt = 1;
                break;
            default:
                throw new IllegalArgumentException("Trang thai khong hop le: " + trangThai);
        }
        return model.getSoLuongTheoTrangThai(trangThaiInt);
    }

    public VatTu getVatTuById(int maVatTu) {
        return model.getVatTuById(maVatTu);
    }
}

