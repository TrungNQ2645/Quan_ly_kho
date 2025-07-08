
package com.senko.warehousemanagement.view;
import com.senko.warehousemanagement.model.entities.NhanVien;
import com.senko.warehousemanagement.view.event.EventMenuSelected;
import com.senko.warehousemanagement.view.form.GiaoDichForm;
import com.senko.warehousemanagement.view.form.KhachHangForm;
import com.senko.warehousemanagement.view.form.LichSuCapNhatForm;
import com.senko.warehousemanagement.view.form.LichSuKiemKeForm;
import com.senko.warehousemanagement.view.form.NhanVienForm;
import com.senko.warehousemanagement.view.form.ThongKeTienIchForm;
import com.senko.warehousemanagement.view.form.VatTuForm;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


public class Main extends javax.swing.JFrame {

    
    public Main(NhanVien currentUser) {
        initComponents();
        ImageIcon icon = new ImageIcon("/icon/appicon2.jpg");
        setIconImage(icon.getImage());
        menu1.getWelcomeLabel().setText("Xin chào, " + currentUser.getTenNhanVien() + "!");
        String chucVu = currentUser.getChucVu();
        menu1.addEventMenuSelected(new EventMenuSelected(){
            @Override
            public void selected(int index) {
                if(index == 0){
                   if(chucVu.equals("NhapXuat") || chucVu.equals("NghiemThu") || chucVu.equals("Admin"))
                    addForm(new VatTuForm());
                   else {
                        JOptionPane.showConfirmDialog(null,"Bạn có quyền sao??","Thông báo", JOptionPane.PLAIN_MESSAGE);
                   }
                }
                else if(index==1){
                    if(chucVu.equals("NhapXuat") || chucVu.equals("Admin"))
                     addForm(new KhachHangForm());
                    else 
                        JOptionPane.showConfirmDialog(null,"Bạn có quyền sao??","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
                else if(index==2){
                     addForm(new NhanVienForm());
                }
                else if(index==3){
                    if(chucVu.equals("KeToan") || chucVu.equals("Admin"))
                     addForm(new GiaoDichForm());
                    else 
                        JOptionPane.showConfirmDialog(null,"Bạn có quyền sao??","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
                else if(index==4){
                     addForm(new LichSuCapNhatForm());
                }
                else if (index==5){
                    if(chucVu.equals("NghiemThu") || chucVu.equals("Admin"))
                     addForm(new LichSuKiemKeForm());
                    else 
                        JOptionPane.showConfirmDialog(null,"Bạn có quyền sao??","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
                else if (index==6){
                    addForm(new ThongKeTienIchForm());
                }
                else if (index==7){
                    dispose();
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
                }
            }   
        });
        addForm(new VatTuForm());
    }
    
    
    public void addForm(JComponent com){
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.senko.warehousemanagement.view.stuff.PanelBorder();
        menu1 = new com.senko.warehousemanagement.view.component.Menu();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WarehouseManager-1.0");

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setOpaque(false);

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private com.senko.warehousemanagement.view.component.Menu menu1;
    private com.senko.warehousemanagement.view.stuff.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
