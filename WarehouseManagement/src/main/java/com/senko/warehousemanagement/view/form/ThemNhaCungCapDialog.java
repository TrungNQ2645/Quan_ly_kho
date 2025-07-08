
package com.senko.warehousemanagement.view.form;

import com.senko.warehousemanagement.view.stuff.KhachHangTable;
import com.senko.warehousemanagement.view.stuff.NhaCungCapTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ThemNhaCungCapDialog extends javax.swing.JDialog {

    private NhaCungCapTable table;
    private boolean isEdit;
    
    public void setTable(NhaCungCapTable table){
        this.table = table;
    }
    
    public void setEdit(boolean isEdit){
        this.isEdit = isEdit;
    }
    
    public ThemNhaCungCapDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isEdit){
                    try{
                        table.editItem(nameTextField.getText(),
                                phoneTextField.getText());
                        table.repaint();
                        table.revalidate();
                        JOptionPane.showConfirmDialog(null,"Sửa thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                        ThemNhaCungCapDialog.this.dispose();
                    }
                    catch(NumberFormatException err){
                        JOptionPane.showConfirmDialog(null,"Nhập lại đi đại ca","Thông báo", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else{
                    try{
                        table.addItem(nameTextField.getText(),
                                phoneTextField.getText());
                        table.repaint();
                        table.revalidate();
                        JOptionPane.showConfirmDialog(null,"Thêm thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                        ThemNhaCungCapDialog.this.dispose();
                    }
                    catch(NumberFormatException err){
                        JOptionPane.showConfirmDialog(null,"Nhập lại đi đại ca","Thông báo", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
    }
    
    public void initEditFrame(){
        Object[] obj = table.getItemAt(table.getSelectedRow());
        this.nameTextField.setText((String)obj[1]);
        this.phoneTextField.setText((String)obj[2]);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm nhà cung cấp");

        phoneTextField.setText("Số Điện Thoại");

        jButton1.setText("OK");

        nameTextField.setText("Tên Nhà Cung Cấp");

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
                            .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                            .addComponent(phoneTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jButton1)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemNhaCungCapDialog dialog = new ThemNhaCungCapDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField phoneTextField;
    // End of variables declaration//GEN-END:variables
}
