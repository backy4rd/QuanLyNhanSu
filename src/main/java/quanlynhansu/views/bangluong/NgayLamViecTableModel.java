package quanlynhansu.views.bangluong;

import java.time.YearMonth;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.table.AbstractTableModel;

import quanlynhansu.models.DiemDanh;

class Row {
	public Date cell1;
}

@SuppressWarnings("serial")
public class NgayLamViecTableModel extends AbstractTableModel {
	private final String[] headers = new String[] { "Ngày", "Trạng thái" };
	private ArrayList<DiemDanh> list;

	public void updateData(ArrayList<DiemDanh> diemDanhs, int thang, int nam) {
		int tongNgayTrongThang = YearMonth.of(nam, thang).lengthOfMonth();
		Calendar calendar = Calendar.getInstance();

		list = new ArrayList<>();

		// Lấp đầy những ngày còn trống trong tháng để hiển thị bảng ngày làm việc trong tháng
		for (int i = 1; i <= tongNgayTrongThang; i++) {
			calendar.set(nam, thang - 1, i, 0, 0, 0);
			Date date = new Date(calendar.getTimeInMillis());

			DiemDanh diemDanh = null;
			for (DiemDanh d : diemDanhs) {
				if (d.getNgay().toLocalDate().compareTo(date.toLocalDate()) == 0) {
					diemDanh = d;
					break;
				}
			}

			if (diemDanh == null) {
				list.add(new DiemDanh(null, date));
			} else {
				list.add(diemDanh);
			}
		}

		fireTableDataChanged();
	}

	public int soNgayLV() {
		int soNgayLV = 0;
		for (DiemDanh d : list) {
			if (d.getMaNV() != null)
				soNgayLV++;
		}

		return soNgayLV;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getNgay();
		case 1:
			Date now = new Date(System.currentTimeMillis());
			DiemDanh d = list.get(rowIndex);
			if (d.getNgay().toLocalDate().compareTo(now.toLocalDate()) > 0) {
				return "Chưa đến ngày làm";
			}
			if (d.getMaNV() != null) {
				return "Có mặt";
			}
			if (d.getNgay().getDay() == 0 || d.getNgay().getDay() == 6) {
				return "Ngày nghỉ";
			}
			return "Vắng";
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}
}
