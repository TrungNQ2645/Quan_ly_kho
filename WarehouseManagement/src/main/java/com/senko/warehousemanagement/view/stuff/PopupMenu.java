
package com.senko.warehousemanagement.view.stuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class PopupMenu extends JPopupMenu{
    private JMenuItem addItem;
    private JMenuItem deleteItem;
    
    public JMenuItem getAddItem(){
        return addItem;
    }
    
    public JMenuItem getDeleteItem(){
        return deleteItem;
    }
    
    public PopupMenu(){
        addItem = new JMenuItem("Thêm");
        deleteItem = new JMenuItem("Xóa");
        
        add(addItem);
        add(deleteItem);
    }
}
