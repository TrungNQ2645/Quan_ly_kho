package com.senko.warehousemanagement.view.form;

import com.senko.warehousemanagement.view.stuff.GiaoDichTable;
import com.senko.warehousemanagement.view.stuff.KhachHangTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ThemGiaoDichDialog extends javax.swing.JDialog {

    private GiaoDichTable table;
    private boolean isEdit;
    
    public void setTable(GiaoDichTable table){
        this.table = table;
    }
    
    public void setEdit(boolean isEdit){
        this.isEdit = isEdit;
        // Khóa combobox nếu là sửa
        if (jComboBox1 != null) {
            jComboBox1.setEnabled(!isEdit);
        }
    }
    
    public ThemGiaoDichDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jComboBox1.addItem("Nhap");
        jComboBox1.addItem("Xuat");
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isEdit){
                    try{
                        table.editItem((String)jComboBox1.getSelectedItem(),
                                nhaVanChuyenField.getText(), nhanVienField.getText());
                        table.repaint();
                        table.revalidate();
                        JOptionPane.showConfirmDialog(null,"Sửa thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                        ThemGiaoDichDialog.this.dispose();
                    }
                    catch(NumberFormatException err){
                        JOptionPane.showConfirmDialog(null,"Nhập lại đi đại ca","Thông báo", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else{
                    try{
                        table.addItem((String)jComboBox1.getSelectedItem(),
                                nhaVanChuyenField.getText(), nhanVienField.getText());
                        table.repaint();
                        table.revalidate();
                        JOptionPane.showConfirmDialog(null,"Thêm thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                        ThemGiaoDichDialog.this.dispose();
                    }
                    catch(NumberFormatException err){
                        JOptionPane.showConfirmDialog(null,"Nhập lại đi đại ca","Thông báo", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
    }
    
    public void initEditFrame(){
        Object[] obj = table.getItemAt(table.getRow());
        jComboBox1.setSelectedItem((String)obj[1]);
        // Khóa combobox khi sửa (phòng trường hợp setEdit chưa được gọi)
        jComboBox1.setEnabled(false);
        this.nhaVanChuyenField.setText((String)obj[4]);
        nhanVienField.setText((String)obj[5]);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nhaVanChuyenField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        nhanVienField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm giao dịch");

        nhaVanChuyenField.setText("Nhà vận chuyển");

        jButton1.setText("OK");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        nhanVienField.setText("Nhân viên");

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
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nhaVanChuyenField, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                            .addComponent(nhanVienField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jButton1)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nhaVanChuyenField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nhanVienField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemGiaoDichDialog dialog = new ThemGiaoDichDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nhaVanChuyenField;
    private javax.swing.JTextField nhanVienField;
    // End of variables declaration//GEN-END:variables
}
