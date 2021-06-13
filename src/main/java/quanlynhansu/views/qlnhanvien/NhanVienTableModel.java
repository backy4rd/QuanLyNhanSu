package quanlynhansu.views.qlnhanvien;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import quanlynhansu.models.NhanVien;

public class NhanVienTableModel extends AbstractTableModel {
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	private final String[] headers = new String[] { "Mã nhân viên", "Tên nhân viên", "Giới tính", "SĐT", "Ngày sinh", "Địa chỉ", "Lương" };
	private ArrayList<NhanVien> list;

	public NhanVienTableModel(ArrayList<NhanVien> source) {
		this.list = source;
	}
	
	public NhanVien getRow(int index) {
		return list.get(index);
	}

	public void removeRow(int index) {
		list.remove(index);
		fireTableDataChanged();
	}
	
	public void addRow(NhanVien nv) {
		list.add(nv);
		fireTableDataChanged();
	}
	
	public void updateRow(int index, NhanVien nv) {
		list.remove(index);
		list.add(index, nv);
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getMaNV();
		case 1:
			return list.get(rowIndex).getTenNV();
		case 2:
			return list.get(rowIndex).isNu() ? "Nữ" : "Nam";
		case 3:
			return list.get(rowIndex).getSdt();
		case 4:
			return list.get(rowIndex).getNgaySinh();
		case 5:
			return list.get(rowIndex).getDiaChi();
		case 6:
			return formatter.format(list.get(rowIndex).getLuong());
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}
}
