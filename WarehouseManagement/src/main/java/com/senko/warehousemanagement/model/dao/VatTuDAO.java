package com.senko.warehousemanagement.model.dao;

import com.senko.warehousemanagement.model.DatabaseConnection;
import com.senko.warehousemanagement.model.entities.VatTu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VatTuDAO {
    
    public ArrayList<VatTu> getAllVatTu(){
        ArrayList<VatTu> dsVatTu = new ArrayList<>();
        String query = "SELECT * FROM VATTU JOIN LOAIVT ON VATTU.MaLVT = LOAIVT.MaLVT"
                + " WHERE VATTU.DAXOA = 0";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                VatTu current = new VatTu(
                    rs.getInt("MaVT"),
                    rs.getString("TenVT"),
                    rs.getString("TenLVT"),
                    rs.getLong("DonGiaNhap"),
                    rs.getLong("DonGiaXuat"),
                    rs.getInt("SoLuong"),
                    rs.getInt("TrangThai")
                );
            
                dsVatTu.add(current);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsVatTu;
    }
    
    public void insertVatTu(String tenVatTu, int maLoaiVatTu, long donGiaNhap){
        String query = "INSERT INTO VATTU(TenVT, MaLVT, DonGiaNhap) VALUES (?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenVatTu);
            stmt.setInt(2, maLoaiVatTu);
            stmt.setLong(3, donGiaNhap);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteVatTu(int maVatTu){
        String query = "UPDATE VATTU SET DAXOA = 1 WHERE MAVT = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,maVatTu);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateVatTu(String tenVatTu, int maLoaiVatTu, int maVatTu){
        String query = "UPDATE VATTU SET TenVT = ?, MaLVT = ? "
                         + "WHERE MaVT = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1,tenVatTu);
            stmt.setInt(2,maLoaiVatTu);
            stmt.setInt(3, maVatTu);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getMaVatTu(String vatTu) {
        int maVatTu = 0;
        String query = "SELECT MAVT FROM VATTU WHERE TENVT = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, vatTu);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maVatTu = rs.getInt("MAVT");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maVatTu;
    }

    public int getSoLuongTheoTrangThai(int trangThai) {
        int soLuong = 0;
        String query = "SELECT COUNT(*) FROM VATTU WHERE TrangThai = ? AND DAXOA = 0";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, trangThai);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                soLuong = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soLuong;
    }

    public VatTu getVatTuById(int maVatTu) {
        VatTu vatTu = null;
        String query = "SELECT VATTU.*, LOAIVT.TenLVT FROM VATTU JOIN LOAIVT ON VATTU.MaLVT = LOAIVT.MaLVT WHERE VATTU.MaVT = ? AND VATTU.DAXOA = 0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maVatTu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vatTu = new VatTu(
                    rs.getInt("MaVT"),
                    rs.getString("TenVT"),
                    rs.getString("TenLVT"),
                    rs.getLong("DonGiaNhap"),
                    rs.getLong("DonGiaXuat"),
                    rs.getInt("SoLuong"),
                    rs.getInt("TrangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vatTu;
    }
}

