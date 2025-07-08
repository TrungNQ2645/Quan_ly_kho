package com.senko.warehousemanagement.view.stuff;

import com.senko.warehousemanagement.controller.GiaoDichController;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GiaoDichTable extends JTable{
    private DefaultTableModel model;
    private GiaoDichController controller = new GiaoDichController();
    private TableRowSorter<TableModel> rowSorter;
    
    Object[][] data = controller.getGiaoDichFromModel();
    
    String[] columns = {"Mã giao dịch","Loại giao dịch","Thời gian","Thành tiền","Nhà vận chuyển","Nhân viên"};
    
    private Object[][] originalData; // Lưu dữ liệu gốc khi load bảng

    public GiaoDichTable(){
        model = new DefaultTableModel(data, columns);
        this.setModel(model);
        rowSorter = new TableRowSorter<>(this.getModel());
        setRowSorter(rowSorter);
        setShowHorizontalLines(true);
        setRowHeight(30);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader((String)value);
                return header;
            }
            
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(isSelected){
                    com.setBackground(new Color(224,224,224));
                   
                }
                else {
                    com.setBackground(Color.white);
                }
                return com;
            }
            
        });
        saveOriginalData();
    }
    
    public void refresh(){
        Object[][] data = controller.getGiaoDichFromModel();
        model = new DefaultTableModel(data,columns);
        setModel(model);
        rowSorter = new TableRowSorter<>(model);
        setRowSorter(rowSorter);
        repaint();
        revalidate();
        saveOriginalData();
    }

    private void saveOriginalData() {
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        originalData = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                originalData[i][j] = model.getValueAt(i, j);
            }
        }
    }

    public void addItem(String loaiGiaoDich,  String nhaVanChuyen, String nhanVien){
        controller.themGiaoDichVaoModel(loaiGiaoDich, nhaVanChuyen, nhanVien);
        refresh();
    }
    
    public void deleteItem(){
        int maGiaoDich = (Integer) model.getValueAt(getSelectedRow(), 0);
        controller.xoaGiaoDich(maGiaoDich);
        refresh();
    }
    
    public void editItem(String loaiGiaoDich, String nhaVanChuyen, String nhanVien){
        int selectedRow = getSelectedRow();
        int maGiaoDich = (Integer) model.getValueAt(selectedRow, 0);

        // Tìm dòng tương ứng trong originalData
        Object[] originalRow = null;
        for (Object[] row : originalData) {
            if (row[0].equals(maGiaoDich)) {
                originalRow = row;
                break;
            }
        }

        // Lấy dữ liệu hiện tại từ database
        com.senko.warehousemanagement.model.entities.GiaoDich gdDB = controller.getGiaoDichById(maGiaoDich);
        if (gdDB != null && originalRow != null) {
            String loaiGDDB = gdDB.getLoaiGiaoDich();
            String nhaVanChuyenDB = gdDB.getNhaVanChuyen();
            String nhanVienDB = gdDB.getNhanVien();

            String loaiGDOriginal = String.valueOf(originalRow[1]);
            String nhaVanChuyenOriginal = String.valueOf(originalRow[4]);
            String nhanVienOriginal = String.valueOf(originalRow[5]);

            if (!loaiGDOriginal.equals(loaiGDDB) ||
                !nhaVanChuyenOriginal.equals(nhaVanChuyenDB) ||
                !nhanVienOriginal.equals(nhanVienDB)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Dữ liệu giao dịch đã bị thay đổi bởi cửa sổ khác. Vui lòng tải lại bảng!", "Lỗi đồng bộ dữ liệu", javax.swing.JOptionPane.ERROR_MESSAGE);
                refresh();
                return;
            }
        }
        controller.capNhatGiaoDichVaoModel(loaiGiaoDich, nhaVanChuyen, nhanVien, maGiaoDich);
        refresh();
    }
    public Object[] getItemAt(int row){
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        int columnCount = model.getColumnCount();
        Object[] obj = new Object[columnCount];
        for (int i = 0; i < columnCount; i++) {
            obj[i] = model.getValueAt(row, i);
        }
        return obj;
    }
    public int getRow(){
        return getSelectedRow();
    }
    
    public void filter(String text){
        RowFilter<TableModel, Object> filter = RowFilter.orFilter(Arrays.asList(
        RowFilter.regexFilter("(?i)" + text, 0), // Cột 1 (Tên)
        RowFilter.regexFilter("(?i)" + text, 5)  // Cột 2 (Địa chỉ)
        ));
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null); // Hiện lại tất cả
        } else {
            // (?i) = không phân biệt hoa thường
            rowSorter.setRowFilter(filter); // Lọc theo cột thứ 1
        }
    }
}
