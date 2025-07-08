
package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.ChiTietGiaoDichDAO;
import com.senko.warehousemanagement.model.dao.KhachHangDAO;
import com.senko.warehousemanagement.model.dao.NhaCungCapDAO;
import com.senko.warehousemanagement.model.dao.VatTuDAO;
import com.senko.warehousemanagement.model.entities.ChiTietNhap;
import com.senko.warehousemanagement.model.entities.ChiTietXuat;
import java.util.ArrayList;

public class ChiTietNhapController {
    private ChiTietGiaoDichDAO model;
    private VatTuDAO modelVT;
    private NhaCungCapDAO modelNCC;
    private KhachHangDAO modelKH;
    
    public ChiTietNhapController(){
        model = new ChiTietGiaoDichDAO();
        modelVT = new VatTuDAO();
        modelNCC = new NhaCungCapDAO();
        modelKH = new KhachHangDAO();
    }
    
    public Object[][] getChiTietNhapFromModel(int maGiaoDich){
        ArrayList<ChiTietNhap> chiTietNhapList = model.getAllChiTietNhap(maGiaoDich);
        
        int colNum = 5;
        int rowNum = chiTietNhapList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = chiTietNhapList.get(i).getMaGiaoDich();
            data[i][1] = chiTietNhapList.get(i).getVatTu();
            data[i][2] = chiTietNhapList.get(i).getSoLuong();
            data[i][3] = chiTietNhapList.get(i).getThanhTien();
            data[i][4] = chiTietNhapList.get(i).getNhaCungCap();
        }
        return data;
    }
    
    public Object[][] getChiTietXuatFromModel(int maGiaoDich){
        ArrayList<ChiTietXuat> chiTietXuatList = model.getAllChiTietXuat(maGiaoDich);
        
        int colNum = 5;
        int rowNum = chiTietXuatList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = chiTietXuatList.get(i).getMaGiaoDich();
            data[i][1] = chiTietXuatList.get(i).getVatTu();
            data[i][2] = chiTietXuatList.get(i).getSoLuong();
            data[i][3] = chiTietXuatList.get(i).getThanhTien();
            data[i][4] = chiTietXuatList.get(i).getKhachHang();
        }
        return data;
    }
    
    public boolean themChiTietNhapVaoModel(String vatTu, String soLuong, String nhaCungCap, int maGiaoDich){
        if(vatTu.trim().isEmpty()||soLuong.trim().isEmpty()||nhaCungCap.trim().isEmpty()){
            return false;
        }
        int maVatTu = modelVT.getMaVatTu(vatTu);
        int maNhaCungCap = modelNCC.getMaNhaCungCap(nhaCungCap);
        try{
            model.insertChiTietNhap(maVatTu, Integer.parseInt(soLuong), maNhaCungCap, maGiaoDich);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public boolean themChiTietXuatVaoModel(String vatTu, String soLuong, String khachHang, int maGiaoDich){
        if(vatTu.trim().isEmpty()||soLuong.trim().isEmpty()||khachHang.trim().isEmpty()){
            return false;
        }
        int maVatTu = modelVT.getMaVatTu(vatTu);
        int maKhachHang = modelKH.getMaKhachHang(khachHang);
        try{
            model.insertChiTietXuat(maVatTu, Integer.parseInt(soLuong), maKhachHang, maGiaoDich);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public boolean xoaChiTietNhap(int maGiaoDich, String vatTu){
        try{
            int maVatTu = modelVT.getMaVatTu(vatTu);
            model.deleteChiTietNhap(maGiaoDich, maVatTu);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean xoaChiTietXuat(int maGiaoDich, String vatTu){
        try{
            int maVatTu = modelVT.getMaVatTu(vatTu);
            model.deleteChiTietXuat(maGiaoDich, maVatTu);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean capNhatChiTietNhapVaoModel(String vatTu, String soLuong, String nhaCungCap, int maGiaoDich){
        if(vatTu.trim().isEmpty()||soLuong.trim().isEmpty()||nhaCungCap.trim().isEmpty()){
            return false;
        }
        int maVatTu = modelVT.getMaVatTu(vatTu);
        int maNhaCungCap = modelNCC.getMaNhaCungCap(nhaCungCap);
        try{
            model.updateChiTietNhap(maVatTu, Integer.parseInt(soLuong), maNhaCungCap, maGiaoDich);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public boolean capNhatChiTietXuatVaoModel(String vatTu, String soLuong, String khachHang, int maGiaoDich){
        if(vatTu.trim().isEmpty()||soLuong.trim().isEmpty()||khachHang.trim().isEmpty()){
            return false;
        }
        int maVatTu = modelVT.getMaVatTu(vatTu);
        int maKhachHang = modelKH.getMaKhachHang(khachHang);
        try{
            model.updateChiTietXuat(maVatTu, Integer.parseInt(soLuong), maKhachHang, maGiaoDich);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }

    public boolean nhapHang(String vatTu, String soLuong, String nhaCungCap, int maGiaoDich){
        if(vatTu.trim().isEmpty()||soLuong.trim().isEmpty()||nhaCungCap.trim().isEmpty()){
            return false;
        }
        int maVatTu = modelVT.getMaVatTu(vatTu);
        int maNhaCungCap = modelNCC.getMaNhaCungCap(nhaCungCap);
        try{
            model.nhapHang(maVatTu, Integer.parseInt(soLuong), maNhaCungCap, maGiaoDich);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean xuatHang(String vatTu, String soLuong, String khachHang, int maGiaoDich){
        if(vatTu.trim().isEmpty()||soLuong.trim().isEmpty()||khachHang.trim().isEmpty()){
            return false;
        }
        int maVatTu = modelVT.getMaVatTu(vatTu);
        int maKhachHang = modelKH.getMaKhachHang(khachHang);
        try{
            model.xuatHang(maVatTu, Integer.parseInt(soLuong), maKhachHang, maGiaoDich);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
