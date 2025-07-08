
package com.senko.warehousemanagement.view.stuff;

import com.senko.warehousemanagement.view.event.EventMenuSelected;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class ListMenu<E extends Object> extends JList<E> {
    private DefaultListModel model;
    private int selectedMenu = -1;
    private int hoveredMenu = -1;
    private EventMenuSelected event;
    
    public void addEventMenuSelected(EventMenuSelected event){
        this.event = event;
    }
    
    public ListMenu(){
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                Object obj = model.getElementAt(index);
                if(obj instanceof String){
                    selectedMenu = index;
                    if(event!=null){
                       event.selected(index);
                    }
                }
                else {
                    selectedMenu = index;
                }
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredMenu = -1;
                repaint();
            }
            
        });
        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                Object obj = model.getElementAt(index);
                if(obj instanceof String){
                    hoveredMenu = index;
                }
                else {
                    hoveredMenu = index;
                }
                repaint();
            }

            
            
        });
    }
    
    public ListCellRenderer getCellRenderer(){
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                String data = (String) value;
                MenuItem item = new MenuItem(data);
                item.setSelected(index == selectedMenu);
                item.setHovered(index == hoveredMenu);
                return item;
            }
          
        };
    }
    
    public void addItem(String data){
        model.addElement(data);
    }
}
