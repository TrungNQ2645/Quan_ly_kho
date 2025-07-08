package com.senko.warehousemanagement.view.stuff;
import com.senko.warehousemanagement.controller.NhanVienController;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class NhanVienTable extends JTable{
    private DefaultTableModel model;
    private NhanVienController controller = new NhanVienController();
    private TableRowSorter<TableModel> rowSorter;
    
    Object[][] data = controller.getNhanVienFromModel();
    
    String[] columns = {"Mã nhân viên","Tên nhân viên","Ngày vào làm","Lương","Chức vụ","Email"};
    
    private Object[][] originalData; // Lưu dữ liệu gốc khi load bảng

    public NhanVienTable(){
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
        Object[][] data = controller.getNhanVienFromModel();
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

    public void addItem(String tenNhanVien, String ngayVaoLam, String luong, String chucVu, String email, String tenDangNhap, String matKhau){
        controller.themNhanVienVaoModel(tenNhanVien, ngayVaoLam, luong, chucVu, email, tenDangNhap, matKhau);
        refresh();
    }
    
    public void deleteItem(){
        int maNhanVien = (Integer) model.getValueAt(getSelectedRow(), 0);
        controller.xoaNhanVien(maNhanVien);
        refresh();
    }
    
    public void editItem(String tenNhanVien, String ngayVaoLam, String luong, String chucVu, String email, String tenDangNhap, String matKhau){
        int selectedRow = getSelectedRow();
        int maNhanVien = (Integer) model.getValueAt(selectedRow, 0);

        // Tìm dòng tương ứng trong originalData
        Object[] originalRow = null;
        for (Object[] row : originalData) {
            if (row[0].equals(maNhanVien)) {
                originalRow = row;
                break;
            }
        }

        // Lấy dữ liệu hiện tại từ database
        com.senko.warehousemanagement.model.entities.NhanVien nvDB = controller.getNhanVienById(maNhanVien);
        if (nvDB != null && originalRow != null) {
            String tenNVDB = nvDB.getTenNhanVien();
            String ngayVaoLamDB = nvDB.getNgayVaoLam().toString();
            String luongDB = String.valueOf(nvDB.getLuong());
            String chucVuDB = nvDB.getChucVu();
            String emailDB = nvDB.getEmail();
            String tenDangNhapDB = nvDB.getTenDangNhap();
            String matKhauDB = nvDB.getMatKhau();

            String tenNVOriginal = String.valueOf(originalRow[1]);
            String ngayVaoLamOriginal = String.valueOf(originalRow[2]);
            String luongOriginal = String.valueOf(originalRow[3]);
            String chucVuOriginal = String.valueOf(originalRow[4]);
            String emailOriginal = String.valueOf(originalRow[5]);
            // Không có cột tên đăng nhập và mật khẩu trên bảng, nếu có thì thêm vào

            if (!tenNVOriginal.equals(tenNVDB) ||
                !ngayVaoLamOriginal.equals(ngayVaoLamDB) ||
                !luongOriginal.equals(luongDB) ||
                !chucVuOriginal.equals(chucVuDB) ||
                !emailOriginal.equals(emailDB)) {
                JOptionPane.showMessageDialog(this, "Dữ liệu nhân viên đã bị thay đổi bởi cửa sổ khác. Vui lòng tải lại bảng!", "Lỗi đồng bộ dữ liệu", JOptionPane.ERROR_MESSAGE);
                refresh();
                return;
            }
        }
        controller.capNhatNhanVienVaoModel(tenNhanVien, ngayVaoLam, luong, chucVu, email, tenDangNhap, matKhau, maNhanVien);
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
