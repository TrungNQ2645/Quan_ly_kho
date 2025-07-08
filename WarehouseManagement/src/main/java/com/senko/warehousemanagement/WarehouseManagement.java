

package com.senko.warehousemanagement;

import com.senko.warehousemanagement.view.Main;
import com.formdev.flatlaf.FlatLightLaf;
import com.senko.warehousemanagement.view.FrameDangNhap;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class WarehouseManagement {

//    public static void main(String[] args) {
////        try {
////            UIManager.setLookAndFeel( new FlatLightLaf() );
////        } catch( Exception ex ) {
////            System.err.println( "Failed to initialize LaF" );
////        }
////        Main main = new Main();
////        main.setVisible(true);
////        javax.swing.SwingUtilities.invokeLater(() -> {
////                javax.swing.JFrame testFrame = new javax.swing.JFrame("Test Frame từ Tiểu Cơ");
////                testFrame.setSize(400, 300);
////                testFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
////                testFrame.setVisible(true);
////            });
//           try {
//    System.setErr(new java.io.PrintStream("errors.log"));
//} catch (Exception e) {
//    e.printStackTrace();
//}
//    }
        
        public static void main(String[] args) {
        System.out.println("[DEBUG] Ứng dụng bắt đầu chạy...");

        try {
            System.out.println("[DEBUG] Đang thiết lập giao diện FlatLaf...");
            UIManager.setLookAndFeel(new FlatLightLaf());
            System.out.println("[DEBUG] Giao diện FlatLaf thiết lập xong.");
        } catch (Exception ex) {
            System.err.println("[ERROR] Không thể thiết lập giao diện: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Đảm bảo Swing chạy trên luồng Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("[DEBUG] Tạo đối tượng Main...");
                FrameDangNhap frame = new FrameDangNhap();
                System.out.println("[DEBUG] Gọi setVisible(true)...");
                frame.setVisible(true);
                System.out.println("[DEBUG] Giao diện đã hiển thị.");
            } catch (Exception ex) {
                System.err.println("[ERROR] Lỗi khi tạo giao diện: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
