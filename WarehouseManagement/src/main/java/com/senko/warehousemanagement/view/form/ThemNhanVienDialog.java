package com.senko.warehousemanagement.view.form;

import com.senko.warehousemanagement.view.stuff.NhanVienTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ThemNhanVienDialog extends javax.swing.JDialog {

    private NhanVienTable table;
    private boolean isEdit;

    public void setTable(NhanVienTable table){
        this.table = table;
    }
    
    public void setEdit(boolean isEdit){
        this.isEdit = isEdit;
    }
    
    public ThemNhanVienDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isEdit){
                    try{
                        table.editItem(
                                nameTextField.getText(),
                                dateTextField.getText(),
                                salaryTextField.getText(),
                                (String)chucVuComboBox.getSelectedItem(),
                                emailField.getText(),
                                usernameField.getText(),
                                passwordField.getText()
                        );
                        table.repaint();
                        table.revalidate();
                        JOptionPane.showConfirmDialog(null,"Sửa thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                        ThemNhanVienDialog.this.dispose();
                    }
                    catch(NumberFormatException err){
                        JOptionPane.showConfirmDialog(null,"Nhập lại đi đại ca","Thông báo", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else{
                    try{
                        table.addItem(
                                nameTextField.getText(),
                                dateTextField.getText(),
                                salaryTextField.getText(),
                                (String)chucVuComboBox.getSelectedItem(),
                                emailField.getText(),
                                usernameField.getText(),
                                passwordField.getText()
                        );
                        table.repaint();
                        table.revalidate();
                        JOptionPane.showConfirmDialog(null,"Thêm thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                        ThemNhanVienDialog.this.dispose();
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
        this.nameTextField.setText((String)obj[1]);
        this.dateTextField.setText(obj[2].toString());
        this.salaryTextField.setText(String.valueOf(obj[3]));
        this.chucVuComboBox.setSelectedItem((String)obj[4]);
        this.emailField.setText((String)obj[5]);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        dateTextField = new javax.swing.JTextField();
        salaryTextField = new javax.swing.JTextField();
        chucVuComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        emailField = new javax.swing.JTextField();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm nhân viên");

        nameTextField.setText("Tên Nhân Viên");

        dateTextField.setText("Ngày Vào Làm");

        salaryTextField.setText("Lương");

        chucVuComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NhapXuat", "KeToan", "NghiemThu" }));

        jButton1.setText("OK");

        emailField.setText("Email");

        usernameField.setText("Tên đăng nhập");

        passwordField.setText("Mật khẩu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameTextField)
                            .addComponent(dateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                            .addComponent(salaryTextField)
                            .addComponent(emailField)
                            .addComponent(usernameField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordField)
                            .addComponent(chucVuComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chucVuComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemNhanVienDialog dialog = new ThemNhanVienDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> chucVuComboBox;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JTextField emailField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField passwordField;
    private javax.swing.JTextField salaryTextField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
