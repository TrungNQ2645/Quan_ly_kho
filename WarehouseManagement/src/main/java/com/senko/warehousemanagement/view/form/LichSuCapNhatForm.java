
package com.senko.warehousemanagement.view.form;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class LichSuCapNhatForm extends javax.swing.JPanel {

    
    public LichSuCapNhatForm() {
        initComponents();
        
        searchBar1.getSearchField().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
                lichSuCapNhatGiaTable2.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                lichSuCapNhatGiaTable2.filter(searchBar1.getSearchField().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lichSuCapNhatGiaTable2.filter(searchBar1.getSearchField().getText());
            }
            
        });
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBar1 = new com.senko.warehousemanagement.view.component.SearchBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        lichSuCapNhatGiaTable2 = new com.senko.warehousemanagement.view.stuff.LichSuCapNhatGiaTable();

        jScrollPane2.setViewportView(lichSuCapNhatGiaTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private com.senko.warehousemanagement.view.stuff.LichSuCapNhatGiaTable lichSuCapNhatGiaTable2;
    private com.senko.warehousemanagement.view.component.SearchBar searchBar1;
    // End of variables declaration//GEN-END:variables
}
