
package com.senko.warehousemanagement.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class NhanVienForm extends javax.swing.JPanel {

    
    public NhanVienForm() {
        initComponents();
        functionBar1.getThemButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemNhanVienDialog dialog = new ThemNhanVienDialog(null,true);
                dialog.setTable(nhanVienTable1);
                dialog.setVisible(true);
                
            }
                
        });
        
        functionBar1.getXoaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    nhanVienTable1.deleteItem();
                    nhanVienTable1.repaint();
                    nhanVienTable1.revalidate();
                    JOptionPane.showConfirmDialog(null,"Xóa thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn nhân viên","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
                
        });
        
        functionBar1.getSuaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ThemNhanVienDialog addDialog = new ThemNhanVienDialog(null,true);
                    addDialog.setEdit(true);
                    addDialog.setTable(nhanVienTable1);
                    addDialog.initEditFrame();
                    addDialog.setVisible(true);
                    addDialog.repaint();
                    addDialog.revalidate();
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn nhân viên","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
            
                
        });
        searchBar1.getSearchField().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
                nhanVienTable1.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                nhanVienTable1.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nhanVienTable1.filter(searchBar1.getSearchField().getText());
            }
            
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBar1 = new com.senko.warehousemanagement.view.component.SearchBar();
        functionBar1 = new com.senko.warehousemanagement.view.component.FunctionBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        nhanVienTable1 = new com.senko.warehousemanagement.view.stuff.NhanVienTable();

        jScrollPane1.setViewportView(nhanVienTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(functionBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(searchBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(functionBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.senko.warehousemanagement.view.component.FunctionBar functionBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.senko.warehousemanagement.view.stuff.NhanVienTable nhanVienTable1;
    private com.senko.warehousemanagement.view.component.SearchBar searchBar1;
    // End of variables declaration//GEN-END:variables
}
