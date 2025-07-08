
package com.senko.warehousemanagement.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class GiaoDichForm extends javax.swing.JPanel {

    
    public GiaoDichForm() {
        initComponents();
        functionBar2.getThemButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemGiaoDichDialog dialog = new ThemGiaoDichDialog(null,true);
                dialog.setTable(giaoDichTable2);
                dialog.setVisible(true);
                
            }
                
        });
        
        functionBar2.getXoaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    giaoDichTable2.deleteItem();
                    giaoDichTable2.repaint();
                    giaoDichTable2.revalidate();
                    JOptionPane.showConfirmDialog(null,"Xóa thành công","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn giao dịch","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
                
        });
        
        functionBar2.getSuaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ThemGiaoDichDialog addDialog = new ThemGiaoDichDialog(null,true);
                    addDialog.setEdit(true);
                    addDialog.setTable(giaoDichTable2);
                    addDialog.initEditFrame();
                    addDialog.setVisible(true);
                    addDialog.repaint();
                    addDialog.revalidate();
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn giao dịch","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            }
            
                
        });
        
        searchBar1.getSearchField().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
                giaoDichTable2.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                giaoDichTable2.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                giaoDichTable2.filter(searchBar1.getSearchField().getText());
            }
            
        });
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBar1 = new com.senko.warehousemanagement.view.component.SearchBar();
        functionBar2 = new com.senko.warehousemanagement.view.component.FunctionBar();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        giaoDichTable2 = new com.senko.warehousemanagement.view.stuff.GiaoDichTable();

        jButton2.setText("Chi tiết");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(giaoDichTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(functionBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(3, 3, 3))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(functionBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       try{
           ChiTietGiaoDichForm dialog = new ChiTietGiaoDichForm(null, true);
           dialog.setTable(giaoDichTable2);
           String loaiGiaoDich = giaoDichTable2.getValueAt(giaoDichTable2.getSelectedRow(), 1).toString();
           dialog.initDetails(loaiGiaoDich);
           dialog.setVisible(true);
       }catch(Exception e){
           JOptionPane.showConfirmDialog(null, "Chưa chọn giao dịch","Thông báo", JOptionPane.PLAIN_MESSAGE);
       }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.senko.warehousemanagement.view.component.FunctionBar functionBar2;
    private com.senko.warehousemanagement.view.stuff.GiaoDichTable giaoDichTable2;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane2;
    private com.senko.warehousemanagement.view.component.SearchBar searchBar1;
    // End of variables declaration//GEN-END:variables
}
