package com.senko.warehousemanagement.model.dao;

import com.senko.warehousemanagement.model.DatabaseConnection;
import com.senko.warehousemanagement.model.entities.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {
    public ArrayList<NhanVien> getAllNhanVien(){
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        String query = "SELECT * FROM NHANVIEN WHERE DAXOA = 0";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                NhanVien current = new NhanVien(
                    rs.getInt("MaNV"),
                    rs.getString("TenNV"),
                    rs.getDate("NgayVaoLam").toLocalDate(),
                    rs.getLong("Luong"),
                    rs.getString("ChucVu"),
                    rs.getString("TenDangNhap"),
                    rs.getString("MatKhau"),
                    rs.getString("Email")
                );
            
                dsNhanVien.add(current);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public void insertNhanVien(String tenNhanVien, Date ngayVaoLam, long luong, String chucVu, String email, String tenDangNhap, String matKhau){
        if (isEmailExists(email)) {
            System.out.println("Email already exists.");
            return; // Hoặc xử lý theo cách khác nếu cần
        }
        String query = "INSERT INTO NHANVIEN(TenNV, NgayVaoLam, Luong, ChucVu, Email, TenDangNhap, MatKhau) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenNhanVien);
            stmt.setDate(2, ngayVaoLam);
            stmt.setLong(3, luong);
            stmt.setString(4, chucVu);
            stmt.setString(5, email);
            stmt.setString(6, tenDangNhap);
            stmt.setString(7, matKhau);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteNhanVien(int maNhanVien){
        String query = "UPDATE NHANVIEN SET DAXOA = 1 WHERE MANV = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,maNhanVien);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNhanVien(String tenNhanVien, Date ngayVaoLam, long luong, String chucVu, String email, String tenDangNhap, String matKhau, int maNhanVien){
        String query = "UPDATE NHANVIEN SET TenNV = ?, NgayVaoLam = ?, Luong = ?, ChucVu = ?, Email = ?, TenDangNhap = ?, MatKhau = ? WHERE MaNV = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1,tenNhanVien);
            stmt.setDate(2, ngayVaoLam);
            stmt.setLong(3, luong);
            stmt.setString(4, chucVu);
            stmt.setString(5, email);
            stmt.setString(6, tenDangNhap);
            stmt.setString(7, matKhau);
            stmt.setInt(8, maNhanVien);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public int getMaNhanVien(String nhanVien) {
        int maNhanVien = 0;
        String query = "SELECT MANV FROM NHANVIEN WHERE TENNV = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nhanVien);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maNhanVien = rs.getInt("MANV");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maNhanVien;
    }
     
    public NhanVien getNhanVienByUsername(String username){
        NhanVien nhanVien = null;
        String query = "SELECT * FROM NHANVIEN WHERE TENDANGNHAP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nhanVien = new NhanVien(
                   rs.getInt("MaNV"),
                    rs.getString("TenNV"),
                    rs.getDate("NgayVaoLam").toLocalDate(),
                    rs.getLong("Luong"),
                    rs.getString("ChucVu"),
                    rs.getString("TenDangNhap"),
                    rs.getString("MatKhau"),
                    rs.getString("Email")
                );
            }
            return nhanVien;

        } catch (SQLException e) {
            e.printStackTrace();
            return nhanVien;
        }
    }

    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM NHANVIEN WHERE Email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu có ít nhất một bản ghi
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePassword(String email, String newPassword) {
        String query = "UPDATE NHANVIEN SET MatKhau = ? WHERE Email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NhanVien getNhanVienById(int maNhanVien) {
        NhanVien nhanVien = null;
        String query = "SELECT * FROM NHANVIEN WHERE MANV = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maNhanVien);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nhanVien = new NhanVien(
                    rs.getInt("MaNV"),
                    rs.getString("TenNV"),
                    rs.getDate("NgayVaoLam").toLocalDate(),
                    rs.getLong("Luong"),
                    rs.getString("ChucVu"),
                    rs.getString("TenDangNhap"),
                    rs.getString("MatKhau"),
                    rs.getString("Email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }
}
