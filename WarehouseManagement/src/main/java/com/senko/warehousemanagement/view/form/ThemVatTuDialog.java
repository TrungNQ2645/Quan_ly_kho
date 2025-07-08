
package com.senko.warehousemanagement.view.form;

import com.senko.warehousemanagement.view.stuff.VatTuTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ThemVatTuDialog extends javax.swing.JDialog {

    private VatTuTable table;
    private boolean isEdit;
    
    public void setTable(VatTuTable table){
        this.table = table;
    }
    
    public void setEdit(boolean isEdit){
        this.isEdit = isEdit;
    }
    
    public ThemVatTuDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isEdit){
                    try{
                        table.editItem(nameTextField.getText(),
                                typeTextField.getText(),priceNhapTextField.getText());
                        table.repaint();
                        table.revalidate();
                        JOptionPane.showConfirmDialog(null,"Sửa thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                        ThemVatTuDialog.this.dispose();
                    }
                    catch(NumberFormatException err){
                        JOptionPane.showConfirmDialog(null,"Nhập lại đi đại ca","Thông báo", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else{
                    try{
                        table.addItem(nameTextField.getText(),
                                typeTextField.getText(),priceNhapTextField.getText());
                        table.repaint();
                        table.revalidate();
                        JOptionPane.showConfirmDialog(null,"Thêm thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                        ThemVatTuDialog.this.dispose();
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
        this.typeTextField.setText((String)obj[2]);
        this.priceNhapTextField.setText(String.valueOf(obj[3]));
    }
    
    public String getStatusString(int status){
        if(status==0){
            return "Hết hàng";
        }
        if(status==1){
            return "Sắp hết";
        }
        return "Còn hàng";
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        typeTextField = new javax.swing.JTextField();
        priceNhapTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm vật tư");

        nameTextField.setText("Tên Vật Tư");

        typeTextField.setText("Loại Vật Tư");

        priceNhapTextField.setText("Giá Nhập");

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
                            .addComponent(nameTextField)
                            .addComponent(typeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                            .addComponent(priceNhapTextField)))
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
                .addComponent(typeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(priceNhapTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemVatTuDialog dialog = new ThemVatTuDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField priceNhapTextField;
    private javax.swing.JTextField typeTextField;
    // End of variables declaration//GEN-END:variables
}
