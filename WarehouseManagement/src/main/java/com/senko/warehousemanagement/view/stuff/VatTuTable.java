package com.senko.warehousemanagement.view.stuff;
import com.senko.warehousemanagement.controller.VatTuController;
import com.senko.warehousemanagement.model.entities.VatTu;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class VatTuTable extends JTable{
    private DefaultTableModel model;
    private VatTuController controller = new VatTuController();
    private TableRowSorter<TableModel> rowSorter;
    
    Object[][] data = controller.getVatTuFromModel();
    
    String[] columns = {"Mã vật tư","Tên vật tư","Mã loại vật tư","Đơn giá nhập","Đơn giá xuát","Số lượng","Trạng thái"};
    private Object[][] originalData; // Lưu dữ liệu gốc khi load bảng
    
    public VatTuTable(){
        model = new DefaultTableModel(data, columns);
        this.setModel(model);
        rowSorter = new TableRowSorter<>(this.getModel());
        setRowSorter(rowSorter);
        setShowHorizontalLines(true);
        setRowHeight(30);
        // Lưu dữ liệu gốc khi khởi tạo
        saveOriginalData();
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader((String)value);
                if(column==6){
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
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
                //VatTu vt = (VatTu) value;
                if(column!=6){
                    return com;
                }
                return new StatusLabel((int)value);
            }
            
        });
    }
    
    public void refresh(){
        Object[][] data = controller.getVatTuFromModel();
        model = new DefaultTableModel(data,columns);
        setModel(model);
        rowSorter = new TableRowSorter<>(model);
        setRowSorter(rowSorter);
        repaint();
        revalidate();
        // Lưu lại dữ liệu gốc mỗi lần refresh
        saveOriginalData();
    }
    
    // Lưu dữ liệu gốc từ model vào originalData
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
    
    public void addItem(String tenVatTu, String loaiVT, String donGiaNhap){
        controller.themVatTuVaoModel(tenVatTu, loaiVT, donGiaNhap);
        refresh();
    }
    
    public void deleteItem(){
        int maVatTu = (Integer) model.getValueAt(getSelectedRow(), 0);
        controller.xoaVatTu(maVatTu);
        refresh();
    }
    
    public void editItem(String tenVatTu, String loaiVT, String donGiaNhap){
        int selectedRow = getSelectedRow();
        int maVatTu = (Integer) model.getValueAt(selectedRow, 0);

        // Tìm dòng tương ứng trong originalData
        Object[] originalRow = null;
        for (Object[] row : originalData) {
            if (row[0].equals(maVatTu)) {
                originalRow = row;
                break;
            }
        }

        // Lấy dữ liệu hiện tại từ database
        VatTu vatTuDB = controller.getVatTuById(maVatTu);
        if (vatTuDB != null && originalRow != null) {
            // So sánh dữ liệu gốc trên bảng với dữ liệu hiện tại trong DB
            String tenVatTuDB = vatTuDB.getTenVatTu();
            String loaiVTDB = vatTuDB.getLoaiVT();
            String donGiaNhapDB = String.valueOf(vatTuDB.getDonGiaNhap());

            String tenVatTuOriginal = String.valueOf(originalRow[1]);
            String loaiVTOriginal = String.valueOf(originalRow[2]);
            String donGiaNhapOriginal = String.valueOf(originalRow[3]);

            // Nếu dữ liệu gốc trên bảng khác DB => đã có ai đó cập nhật, báo lỗi
            if (!tenVatTuOriginal.equals(tenVatTuDB) ||
                !loaiVTOriginal.equals(loaiVTDB) ||
                !donGiaNhapOriginal.equals(donGiaNhapDB)) {
                JOptionPane.showMessageDialog(this, "Dữ liệu vật tư đã bị thay đổi bởi cửa sổ khác. Vui lòng tải lại bảng!", "Lỗi đồng bộ dữ liệu", JOptionPane.ERROR_MESSAGE);
                refresh();
                return;
            }
        }
        // Cho phép cập nhật vì dữ liệu đồng bộ
        controller.capNhatVatTuVaoModel(tenVatTu, loaiVT, donGiaNhap, maVatTu);
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
        RowFilter.regexFilter("(?i)" + text, 1)  // Cột 2 (Địa chỉ)
        ));
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null); // Hiện lại tất cả
        } else {
            // (?i) = không phân biệt hoa thường
            rowSorter.setRowFilter(filter); // Lọc theo cột thứ 1
        }
    }
}

