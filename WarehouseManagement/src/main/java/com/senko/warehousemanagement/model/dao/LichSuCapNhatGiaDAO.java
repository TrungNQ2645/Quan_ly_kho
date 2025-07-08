
package com.senko.warehousemanagement.model.dao;

import com.senko.warehousemanagement.model.DatabaseConnection;
import com.senko.warehousemanagement.model.entities.LichSuCapNhatGia;
import com.senko.warehousemanagement.model.entities.NhanVien;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LichSuCapNhatGiaDAO {
    public ArrayList<LichSuCapNhatGia> getAllLichSuCapNhatGia(){
        ArrayList<LichSuCapNhatGia> dsLichSuCapNhatGia = new ArrayList<>();
        String query = "SELECT * FROM LICHSUCAPNHAT JOIN VATTU ON LICHSUCAPNHAT.MaVT = VATTU.MaVT";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                LichSuCapNhatGia current = new LichSuCapNhatGia(
                    rs.getInt("MaLSCN"),
                    rs.getString("TenVT"),
                    rs.getLong("GiaCu"),
                    rs.getLong("GiaMoi"),
                    rs.getDate("NgayCapNhat").toLocalDate()
                );
            
                dsLichSuCapNhatGia.add(current);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLichSuCapNhatGia;
    }
    
    public boolean thayDoiGia(int maVatTu, long giaMoi) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            // Đặt isolation level nếu cần, ví dụ SERIALIZABLE
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false); // Bắt đầu transaction

            stmt = conn.prepareCall("{call TRANSACTION_THAY_DOI_GIA(?, ?)}");
            stmt.setInt(1, maVatTu);
            stmt.setLong(2, giaMoi);
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
