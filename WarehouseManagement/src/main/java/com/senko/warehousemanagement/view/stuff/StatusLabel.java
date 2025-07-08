
package com.senko.warehousemanagement.view.stuff;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;


public class StatusLabel extends javax.swing.JPanel {

    private int trangThai;
    
    public StatusLabel(int type) {
        initComponents();
        this.trangThai = type;
        setOpaque(false);
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(trangThai == 0){
            g.setColor(Color.RED);
            jLabel1.setText("Het_Hang");
        }
        else if(trangThai == 1){
            g.setColor(Color.YELLOW);
            jLabel1.setText("Sap_Het");

        }
        else{
            g.setColor(Color.GREEN);
            jLabel1.setText("Con_Hang");
        }
        g.fillRoundRect(5, 5, getWidth()-10, getHeight()-10, 10, 10);
        super.paintComponent(g);
    }

    
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
