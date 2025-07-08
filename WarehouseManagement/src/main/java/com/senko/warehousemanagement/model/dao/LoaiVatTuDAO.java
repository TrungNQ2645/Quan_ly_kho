
package com.senko.warehousemanagement.model.dao;

import com.senko.warehousemanagement.model.DatabaseConnection;
import com.senko.warehousemanagement.model.entities.LoaiVatTu;
import com.senko.warehousemanagement.model.entities.NhaVanChuyen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LoaiVatTuDAO {
    public int getMaLoaiVatTu(String loaiVT) {
        int maLoaiVatTu = 0;
        String query = "SELECT MALVT FROM LOAIVT WHERE TENLVT = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, loaiVT);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maLoaiVatTu = rs.getInt("MALVT");
                System.out.println(maLoaiVatTu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maLoaiVatTu;
    }
    
    public ArrayList<LoaiVatTu> getAllLoaiVatTu(){
        ArrayList<LoaiVatTu> dsLoaiVatTu = new ArrayList<>();
        String query = "SELECT * FROM LOAIVT";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                LoaiVatTu current = new LoaiVatTu(
                    rs.getInt("MaLVT"),
                    rs.getString("TenLVT")
                );
            
                dsLoaiVatTu.add(current);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLoaiVatTu;
    }
    
    public void insertLoaiVatTu(String tenLoaiVatTu){
        String query = "INSERT INTO LoaiVT(TENLVT) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenLoaiVatTu);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteLoaiVatTu(int maLoaiVatTu){
        String query = "DELETE FROM LoaiVT WHERE MaLVT = ?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,maLoaiVatTu);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
