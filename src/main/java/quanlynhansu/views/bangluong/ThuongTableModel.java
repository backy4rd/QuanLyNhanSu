package quanlynhansu.views.bangluong;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import quanlynhansu.models.Thuong;

@SuppressWarnings("serial")
public class ThuongTableModel extends AbstractTableModel {
	private final String[] headers = { "Mã thưởng", "Số tiền", "Ngày thưởng" };
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	private ArrayList<Thuong> list = new ArrayList<>();

	public void updateData(ArrayList<Thuong> thuongs) {
		this.list = thuongs;
		fireTableDataChanged();
	}
	
	public double tongThuong() {
		double tongThuong = 0;
		for (Thuong t : list) {
			tongThuong += t.getSoTien();
		}
		return tongThuong;
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
			return list.get(rowIndex).getMaThuong();
		case 1:
			return formatter.format(list.get(rowIndex).getSoTien());
		case 2:
			return list.get(rowIndex).getNgayThuong();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}
}
