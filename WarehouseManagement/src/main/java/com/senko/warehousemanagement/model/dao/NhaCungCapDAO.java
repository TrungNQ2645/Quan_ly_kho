
package com.senko.warehousemanagement.model.dao;

import com.senko.warehousemanagement.model.DatabaseConnection;
import com.senko.warehousemanagement.model.entities.LoaiVatTu;
import com.senko.warehousemanagement.model.entities.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class NhaCungCapDAO {
    public int getMaNhaCungCap(String nhaCungCap) {
        int maNhaCungCap = 0;
        String query = "SELECT MANCC FROM NHACUNGCAP WHERE TENNCC = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nhaCungCap);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maNhaCungCap = rs.getInt("MANCC");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maNhaCungCap;
    }
    
    public ArrayList<NhaCungCap> getAllNhaCungCap(){
        ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<>();
        String query = "SELECT * FROM NHACUNGCAP WHERE DAXOA = 0";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                NhaCungCap current = new NhaCungCap(
                    rs.getInt("MaNCC"),
                    rs.getString("TenNCC"),
                    rs.getString("SDT")
                );

                dsNhaCungCap.add(current);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhaCungCap;
    }

    public void insertNhaCungCap(String tenNhaCungCap, String soDienThoai){
        String query = "INSERT INTO NHACUNGCAP(TENNCC, SDT) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenNhaCungCap);
            stmt.setString(2, soDienThoai);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteNhaCungCap(int maNhaCungCap){
        String query = "UPDATE NHACUNGCAP SET DAXOA = 1 WHERE MANCC = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,maNhaCungCap);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNhaCungCap(String tenNhaCungCap, String soDienThoai, int maNhaCungCap){
        String query = "UPDATE NHACUNGCAP SET TENNCC = ?, SDT = ? WHERE MANCC = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, tenNhaCungCap);
            stmt.setString(2, soDienThoai);
            stmt.setInt(3, maNhaCungCap);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
