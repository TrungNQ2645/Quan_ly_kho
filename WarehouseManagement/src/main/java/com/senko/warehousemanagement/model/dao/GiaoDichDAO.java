package com.senko.warehousemanagement.model.dao;

import com.senko.warehousemanagement.model.DatabaseConnection;
import com.senko.warehousemanagement.model.entities.GiaoDich;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class GiaoDichDAO {
    public ArrayList<GiaoDich> getAllGiaoDich(){
        ArrayList<GiaoDich> dsGiaoDich = new ArrayList<>();
        String query = "SELECT * FROM GIAODICH JOIN NHAVANCHUYEN ON GIAODICH.MaNVC = NHAVANCHUYEN.MaNVC "
                + "JOIN NHANVIEN ON GIAODICH.MaNV = NHANVIEN.MaNV";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                GiaoDich current = new GiaoDich(
                    rs.getInt("MaGD"),
                    rs.getString("LoaiGD"),
                    rs.getDate("ThoiGian").toLocalDate(),
                    rs.getLong("ThanhTien"),
                    rs.getString("TenNVC"),
                    rs.getString("TenNV")
                );
            
                dsGiaoDich.add(current);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsGiaoDich;
    }
    
    public void insertGiaoDich(String loaiGiaoDich, int maNhaVanChuyen, int maNhanVien){
        String query = "INSERT INTO GIAODICH(LoaiGD, MaNVC, MaNV) VALUES (?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, loaiGiaoDich);
            stmt.setInt(2, maNhaVanChuyen);
            stmt.setInt(3, maNhanVien);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteGiaoDich(int maGiaoDich){
        String query = "DELETE FROM GIAODICH WHERE MaGD = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,maGiaoDich);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateGiaoDich(String loaiGiaoDich, int maNhaVanChuyen,int maNhanVien, int maGiaoDich){
        String query = "UPDATE KHACHHANG SET LoaiGD = ?, MaNVC = ?, MaNV = ?"
                         + "WHERE MaGD = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, loaiGiaoDich);
            stmt.setInt(2, maNhaVanChuyen);
            stmt.setInt(3, maNhanVien);
            stmt.setInt(4, maGiaoDich);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getQuantityByType(String loaiGiaoDich) {
        String query = "SELECT COUNT(*) FROM GIAODICH WHERE LoaiGD = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, loaiGiaoDich);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public GiaoDich getGiaoDichById(int maGiaoDich) {
        GiaoDich giaoDich = null;
        String query = "SELECT GIAODICH.*, NHAVANCHUYEN.TenNVC, NHANVIEN.TenNV FROM GIAODICH " +
                       "JOIN NHAVANCHUYEN ON GIAODICH.MaNVC = NHAVANCHUYEN.MaNVC " +
                       "JOIN NHANVIEN ON GIAODICH.MaNV = NHANVIEN.MaNV " +
                       "WHERE GIAODICH.MaGD = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maGiaoDich);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                giaoDich = new GiaoDich(
                    rs.getInt("MaGD"),
                    rs.getString("LoaiGD"),
                    rs.getDate("ThoiGian").toLocalDate(),
                    rs.getLong("ThanhTien"),
                    rs.getString("TenNVC"),
                    rs.getString("TenNV")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return giaoDich;
    }
}
