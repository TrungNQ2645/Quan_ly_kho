package com.senko.warehousemanagement.controller;

import com.senko.warehousemanagement.model.dao.NhanVienDAO;
import com.senko.warehousemanagement.model.entities.NhanVien;
import java.sql.Date;
import java.util.ArrayList;

public class NhanVienController {
    private NhanVienDAO model;
    
    public NhanVienController(){
        model = new NhanVienDAO();
    }
    
    public Object[][] getNhanVienFromModel(){
        ArrayList<NhanVien> nhanVienList = model.getAllNhanVien();
        
        int colNum = 6;
        int rowNum = nhanVienList.size();
        Object[][] data = new Object[rowNum][colNum];
        
        for(int i = 0;i<rowNum;i++){
            data[i][0] = nhanVienList.get(i).getMaNhanVien();
            data[i][1] = nhanVienList.get(i).getTenNhanVien();
            data[i][2] = nhanVienList.get(i).getNgayVaoLam();
            data[i][3] = nhanVienList.get(i).getLuong();
            data[i][4] = nhanVienList.get(i).getChucVu();
            data[i][5] = nhanVienList.get(i).getEmail();
        }
        return data;
    }
    
    public boolean themNhanVienVaoModel(String tenNhanVien, String ngayVaoLam, String luong, String chucVu, String email, String tenDangNhap, String matKhau){
         // Kiểm tra các trường không được để trống
        if(tenNhanVien.trim().isEmpty()||ngayVaoLam.trim().isEmpty()||luong.trim().isEmpty()||chucVu.trim().isEmpty() || email.trim().isEmpty() || tenDangNhap.trim().isEmpty() || matKhau.trim().isEmpty()){
            return false;
        }
        Date nvl = Date.valueOf(ngayVaoLam);
        try{
            model.insertNhanVien(tenNhanVien, nvl, Long.parseLong(luong), chucVu, email, tenDangNhap, matKhau);
            return true;
        }   
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    public boolean xoaNhanVien(int maNhanVien){
        try{
            model.deleteNhanVien(maNhanVien);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean capNhatNhanVienVaoModel(String tenNhanVien, String ngayVaoLam, String luong, String chucVu, String email, String tenDangNhap, String matKhau, int maNhanVien){
        if(tenNhanVien.trim().isEmpty()||ngayVaoLam.trim().isEmpty()||luong.trim().isEmpty()||chucVu.trim().isEmpty() || email.trim().isEmpty() || tenDangNhap.trim().isEmpty() || matKhau.trim().isEmpty()){
            return false;
        }
        Date nvl = Date.valueOf(ngayVaoLam);
        try{
            model.updateNhanVien(tenNhanVien, nvl, Long.parseLong(luong), chucVu, email, tenDangNhap, matKhau, maNhanVien);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public NhanVien kiemTraDangNhap(String username, char[] password){
        NhanVien nhanVien = model.getNhanVienByUsername(username);
        if(nhanVien != null && new String(password).equals(nhanVien.getMatKhau())){
            return nhanVien;
        }
        return null;
    }

    public boolean kiemTraEmailTonTai(String email) {
        try{
            return model.isEmailExists(email);
        }catch(Exception e){
            return false;
        }
        
    }

    public boolean capNhatMatKhau(String email, String matKhauMoi) {
        try{
           model.updatePassword(email, matKhauMoi);
           return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public com.senko.warehousemanagement.model.entities.NhanVien getNhanVienById(int maNhanVien) {
        return model.getNhanVienById(maNhanVien);
    }
}
