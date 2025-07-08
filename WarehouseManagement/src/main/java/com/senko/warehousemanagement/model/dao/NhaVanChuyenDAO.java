
package com.senko.warehousemanagement.model.dao;

import com.senko.warehousemanagement.model.DatabaseConnection;
import com.senko.warehousemanagement.model.entities.NhaVanChuyen;
import com.senko.warehousemanagement.model.entities.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class NhaVanChuyenDAO {
    public int getNhaVanChuyen(String nhaVanChuyen) {
        int maNhaVanChuyen = 0;
        String query = "SELECT MaNVC FROM NHAVANCHUYEN WHERE TenNVC = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nhaVanChuyen);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maNhaVanChuyen = rs.getInt("MaNVC");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maNhaVanChuyen;
    }
    
    public ArrayList<NhaVanChuyen> getAllNhaVanChuyen(){
        ArrayList<NhaVanChuyen> dsNhaVanChuyen = new ArrayList<>();
        String query = "SELECT * FROM NHAVANCHUYEN";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                NhaVanChuyen current = new NhaVanChuyen(
                    rs.getInt("MaNVC"),
                    rs.getString("TenNVC"),
                    rs.getString("SDT_DAIDIEN")
                );
            
                dsNhaVanChuyen.add(current);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhaVanChuyen;
    }
    
    public void insertNhaVanChuyen(String tenNhaVanChuyen, String soDienThoai){
        String query = "INSERT INTO NhaVanChuyen(TENNVC, SDT_DAIDIEN) VALUES (?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenNhaVanChuyen);
            stmt.setString(2, soDienThoai);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteNhaVanChuyen(int maNhaVanChuyen){
        String query = "DELETE FROM NhaVanChuyen WHERE MaNVC = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,maNhaVanChuyen);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
