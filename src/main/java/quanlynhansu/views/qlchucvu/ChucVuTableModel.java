package quanlynhansu.views.qlchucvu;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import quanlynhansu.models.ChucVu;

@SuppressWarnings("serial")
public class ChucVuTableModel extends AbstractTableModel {
	private final String[] headers = new String[] { "Mã chức vụ", "Tên chức vụ", "Cấp bậc" };
	private ArrayList<ChucVu> list;

	public ChucVuTableModel(ArrayList<ChucVu> list) {
		this.list = list;
	}
	
	public ChucVu getRow(int index) {
		return list.get(index);
	}
	
	public void removeRow(int index) {
		list.remove(index);
		fireTableDataChanged();
	}
	
	public void addRow(ChucVu cslv) {
		list.add(cslv);
		fireTableDataChanged();
	}
	
	public void updateRow(int index, ChucVu cslv) {
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
			return list.get(rowIndex).getMaCV();
		case 1:
			return list.get(rowIndex).getTenCV();
		case 2:
			return list.get(rowIndex).getCapBac();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}
}
