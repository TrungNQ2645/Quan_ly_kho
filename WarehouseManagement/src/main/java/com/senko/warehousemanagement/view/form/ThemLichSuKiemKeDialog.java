
package com.senko.warehousemanagement.view.form;

import com.senko.warehousemanagement.view.stuff.LichSuKiemKeTable;
import com.senko.warehousemanagement.view.stuff.NhanVienTable;
import com.senko.warehousemanagement.view.stuff.VatTuTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ThemLichSuKiemKeDialog extends javax.swing.JDialog {

    private LichSuKiemKeTable table;
    
    public void setTable(LichSuKiemKeTable table){
        this.table = table;
    }
    
    public ThemLichSuKiemKeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    table.addItem(nhanVienField.getText(),
                            vatTuField.getText(),soLuongField.getText(),tinhTrangField.getText());
                    table.repaint();
                    table.revalidate();
                    JOptionPane.showConfirmDialog(null,"Thêm thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                    ThemLichSuKiemKeDialog.this.dispose();
                }
                catch(NumberFormatException err){
                    JOptionPane.showConfirmDialog(null,"Nhập lại đi đại ca","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nhanVienField = new javax.swing.JTextField();
        vatTuField = new javax.swing.JTextField();
        soLuongField = new javax.swing.JTextField();
        tinhTrangField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kiểm kê");

        nhanVienField.setText("Nhân Viên");

        vatTuField.setText("Vật Tư");

        soLuongField.setText("Số Lượng Còn Lại");

        tinhTrangField.setText("Tình Trạng");

        jButton1.setText("OK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nhanVienField)
                            .addComponent(vatTuField)
                            .addComponent(soLuongField, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                            .addComponent(tinhTrangField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jButton1)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(nhanVienField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vatTuField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(soLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tinhTrangField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemLichSuKiemKeDialog dialog = new ThemLichSuKiemKeDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nhanVienField;
    private javax.swing.JTextField soLuongField;
    private javax.swing.JTextField tinhTrangField;
    private javax.swing.JTextField vatTuField;
    // End of variables declaration//GEN-END:variables
}
