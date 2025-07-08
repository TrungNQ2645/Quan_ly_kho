
package com.senko.warehousemanagement.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class KhachHangForm extends javax.swing.JPanel {

    
    public KhachHangForm() {
        initComponents();
        functionBar1.getThemButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemKhachHangDialog dialog = new ThemKhachHangDialog(null,true);
                dialog.setTable(khachHangTable1);
                dialog.setVisible(true);
                
            }
                
        });
        
        functionBar1.getXoaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    khachHangTable1.deleteItem();
                    khachHangTable1.repaint();
                    khachHangTable1.revalidate();
                    JOptionPane.showConfirmDialog(null,"Xóa thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn vật tư","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
                
        });
        
        functionBar1.getSuaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ThemKhachHangDialog addDialog = new ThemKhachHangDialog(null,true);
                    addDialog.setEdit(true);
                    addDialog.setTable(khachHangTable1);
                    addDialog.initEditFrame();
                    addDialog.setVisible(true);
                    addDialog.repaint();
                    addDialog.revalidate();
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn khách hàng","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
            
                
        });
        
        searchBar1.getSearchField().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
                khachHangTable1.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                khachHangTable1.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                khachHangTable1.filter(searchBar1.getSearchField().getText());
            }
            
        });
        
        searchBar1.getSearchButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBar1.getSearchField().setText("");
            }
            
        });
        
        functionBar2.getThemButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemNhaCungCapDialog dialog = new ThemNhaCungCapDialog(null,true);
                dialog.setTable(nhaCungCapTable1);
                dialog.setVisible(true);
                
            }
                
        });
        
        functionBar2.getXoaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    nhaCungCapTable1.deleteItem();
                    nhaCungCapTable1.repaint();
                    nhaCungCapTable1.revalidate();
                    JOptionPane.showConfirmDialog(null,"Xóa thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn vật tư","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
                
        });
        
        functionBar2.getSuaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ThemNhaCungCapDialog addDialog = new ThemNhaCungCapDialog(null,true);
                    addDialog.setEdit(true);
                    addDialog.setTable(nhaCungCapTable1);
                    addDialog.initEditFrame();
                    addDialog.setVisible(true);
                    addDialog.repaint();
                    addDialog.revalidate();
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn khách hàng","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
            
                
        });
        
        searchBar2.getSearchField().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
               nhaCungCapTable1.filter(searchBar2.getSearchField().getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
               nhaCungCapTable1.filter(searchBar2.getSearchField().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               nhaCungCapTable1.filter(searchBar2.getSearchField().getText());
            }
            
        });
        
        searchBar2.getSearchButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBar2.getSearchField().setText("");
            }
            
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        functionBar1 = new com.senko.warehousemanagement.view.component.FunctionBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        khachHangTable1 = new com.senko.warehousemanagement.view.stuff.KhachHangTable();
        searchBar1 = new com.senko.warehousemanagement.view.component.SearchBar();
        searchBar2 = new com.senko.warehousemanagement.view.component.SearchBar();
        functionBar2 = new com.senko.warehousemanagement.view.component.FunctionBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        nhaCungCapTable1 = new com.senko.warehousemanagement.view.stuff.NhaCungCapTable();

        jScrollPane1.setViewportView(khachHangTable1);

        jScrollPane2.setViewportView(nhaCungCapTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(searchBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(functionBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(functionBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(functionBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(functionBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.senko.warehousemanagement.view.component.FunctionBar functionBar1;
    private com.senko.warehousemanagement.view.component.FunctionBar functionBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.senko.warehousemanagement.view.stuff.KhachHangTable khachHangTable1;
    private com.senko.warehousemanagement.view.stuff.NhaCungCapTable nhaCungCapTable1;
    private com.senko.warehousemanagement.view.component.SearchBar searchBar1;
    private com.senko.warehousemanagement.view.component.SearchBar searchBar2;
    // End of variables declaration//GEN-END:variables
}
