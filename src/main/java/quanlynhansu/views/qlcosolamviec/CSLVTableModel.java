package quanlynhansu.views.qlcosolamviec;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import quanlynhansu.models.CoSoLamViec;

public class CSLVTableModel extends AbstractTableModel {
	private final String[] headers = new String[] { "Mã cơ sở", "Tên cơ sở", "Địa chỉ" };
	private ArrayList<CoSoLamViec> list;

	public CSLVTableModel(ArrayList<CoSoLamViec> list) {
		this.list = list;
	}
	
	public CoSoLamViec getRow(int index) {
		return list.get(index);
	}
	
	public void removeRow(int index) {
		list.remove(index);
		fireTableDataChanged();
	}
	
	public void addRow(CoSoLamViec cslv) {
		list.add(cslv);
		fireTableDataChanged();
	}
	
	public void updateRow(int index, CoSoLamViec cslv) {
		list.remove(index);
		list.add(index, cslv);
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
			return list.get(rowIndex).getMaCS();
		case 1:
			return list.get(rowIndex).getTenCS();
		case 2:
			return list.get(rowIndex).getDiaChi();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}
}
