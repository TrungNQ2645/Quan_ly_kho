
package com.senko.warehousemanagement.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class VatTuForm extends javax.swing.JPanel {

    
    public VatTuForm() {
        initComponents();
        functionBar1.getThemButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemVatTuDialog dialog = new ThemVatTuDialog(null,true);
                dialog.setTable(vatTuTable1);
                dialog.setVisible(true);
                
            }
                
        });
        
        functionBar1.getXoaButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    vatTuTable1.deleteItem();
                    vatTuTable1.repaint();
                    vatTuTable1.revalidate();
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
                    ThemVatTuDialog addDialog = new ThemVatTuDialog(null,true);
                    addDialog.setEdit(true);
                    addDialog.setTable(vatTuTable1);
                    addDialog.initEditFrame();
                    addDialog.setVisible(true);
                    addDialog.repaint();
                    addDialog.revalidate();
                }catch (ArrayIndexOutOfBoundsException aibe){
                    JOptionPane.showConfirmDialog(null,"Chưa chọn vật tư","Thông báo", JOptionPane.PLAIN_MESSAGE);
                }
            } 
                
        });
        searchBar1.getSearchButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBar1.getSearchField().setText("");
            }
            
        });
        
        searchBar1.getSearchField().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
                vatTuTable1.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                vatTuTable1.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                vatTuTable1.filter(searchBar1.getSearchField().getText());
            }
            
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        vatTuTable1 = new com.senko.warehousemanagement.view.stuff.VatTuTable();
        functionBar1 = new com.senko.warehousemanagement.view.component.FunctionBar();
        searchBar1 = new com.senko.warehousemanagement.view.component.SearchBar();

        jScrollPane1.setViewportView(vatTuTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(functionBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
            .addComponent(searchBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(searchBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(functionBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.senko.warehousemanagement.view.component.FunctionBar functionBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.senko.warehousemanagement.view.component.SearchBar searchBar1;
    private com.senko.warehousemanagement.view.stuff.VatTuTable vatTuTable1;
    // End of variables declaration//GEN-END:variables
}
