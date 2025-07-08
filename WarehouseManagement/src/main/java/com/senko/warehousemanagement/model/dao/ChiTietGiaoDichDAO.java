
package com.senko.warehousemanagement.model.dao;

import com.senko.warehousemanagement.model.DatabaseConnection;
import com.senko.warehousemanagement.model.entities.ChiTietNhap;
import com.senko.warehousemanagement.model.entities.ChiTietXuat;
import com.senko.warehousemanagement.model.entities.NhanVien;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ChiTietGiaoDichDAO {
    public ArrayList<ChiTietNhap> getAllChiTietNhap(int maGiaoDich){
        ArrayList<ChiTietNhap> dsChiTietNhap = new ArrayList<>();
        String query = "SELECT * FROM CT_NHAP JOIN VATTU ON CT_NHAP.MaVT = VATTU.MaVT "
                + "JOIN NHACUNGCAP ON CT_NHAP.MaNCC = NHACUNGCAP.MaNCC "
                + "WHERE CT_NHAP.MaGD = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1,maGiaoDich);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                ChiTietNhap current = new ChiTietNhap(
                    rs.getInt("MaGD"),
                    rs.getString("TenVT"),
                    rs.getInt("SL"),
                    rs.getLong("ThanhTien"),
                    rs.getString("TenNCC")
                );
            
                dsChiTietNhap.add(current);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietNhap;
    }
    
    public void insertChiTietNhap(int maVatTu, int soLuong, int maNhaCungCap, int maGiaoDich){
        String query = "INSERT INTO CT_NHAP(MaGD, MaVT, SL, MaNCC) VALUES (?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maGiaoDich);
            stmt.setInt(2, maVatTu);
            stmt.setInt(3, soLuong);
            stmt.setInt(4, maNhaCungCap);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteChiTietNhap(int maGiaoDich, int maVatTu){
        String query = "DELETE FROM CT_NHAP WHERE MaGD = ? AND MaVT = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,maGiaoDich);
            stmt.setInt(2, maVatTu);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateChiTietNhap(int maVatTu, int soLuong, int maNhaCungCap, int maGiaoDich){
        String query = "UPDATE CT_NHAP SET SL = ?, MaNCC = ?"
                         + "WHERE MaGD = ? AND MAVT = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1, soLuong);
            stmt.setInt(2, maNhaCungCap);
            stmt.setInt(3, maGiaoDich);
            stmt.setInt(4, maVatTu);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<ChiTietXuat> getAllChiTietXuat(int maGiaoDich){
        ArrayList<ChiTietXuat> dsChiTietXuat = new ArrayList<>();
        String query = "SELECT * FROM CT_XUAT JOIN VATTU ON CT_XUAT.MaVT = VATTU.MaVT "
                + "JOIN KHACHHANG ON CT_XUAT.MaKH = KHACHHANG.MaKH "
                + "WHERE CT_XUAT.MaGD = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1,maGiaoDich);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ChiTietXuat current = new ChiTietXuat(
                    rs.getInt("MaGD"),
                    rs.getString("TenVT"),
                    rs.getInt("SL"),
                    rs.getLong("ThanhTien"),
                    rs.getString("TenKH")
                );
            
                dsChiTietXuat.add(current);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietXuat;
    }
    
    public void insertChiTietXuat(int maVatTu, int soLuong, int maKhachHang, int maGiaoDich){
        String query = "INSERT INTO CT_XUAT(MaGD, MaVT, SL, MaKH) VALUES (?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maGiaoDich);
            stmt.setInt(2, maVatTu);
            stmt.setInt(3, soLuong);
            stmt.setInt(4, maKhachHang);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteChiTietXuat(int maGiaoDich, int maVatTu){
        String query = "DELETE FROM CT_XUAT WHERE MaGD = ? AND MaVT = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,maGiaoDich);
            stmt.setInt(2, maVatTu);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateChiTietXuat(int maVatTu, int soLuong, int maKhachHang, int maGiaoDich){
        String query = "UPDATE CT_XUAT SET SL = ?, MaKH = ? "
                         + "WHERE MaGD = ? AND MAVT = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1, soLuong);
            stmt.setInt(2, maKhachHang);
            stmt.setInt(3, maGiaoDich); 
            stmt.setInt(4, maVatTu);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean nhapHang(int maVatTu, int soLuongNhap, int maNhaCungCap, int maGiaoDich) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // Thiết lập isolation level
            conn.setAutoCommit(false); // Bắt đầu transaction

            stmt = conn.prepareCall("{call TRANSACTION_NHAP_HANG(?, ?, ?, ?)}");
            stmt.setInt(1, maVatTu);
            stmt.setInt(2, soLuongNhap);
            stmt.setInt(3, maNhaCungCap);
            stmt.setInt(4, maGiaoDich);
            stmt.execute();

            conn.commit(); // Commit nếu không lỗi
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
    
    public boolean xuatHang(int maVatTu, int soLuongXuat, int maKhachHang, int maGiaoDich) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // Thiết lập isolation level
            conn.setAutoCommit(false); // Bắt đầu transaction

            stmt = conn.prepareCall("{call TRANSACTION_XUAT_HANG(?, ?, ?, ?)}");
            stmt.setInt(1, maVatTu);
            stmt.setInt(2, soLuongXuat);
            stmt.setInt(3, maKhachHang);
            stmt.setInt(4, maGiaoDich);
            stmt.execute();

            conn.commit(); // Commit nếu không lỗi
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}
