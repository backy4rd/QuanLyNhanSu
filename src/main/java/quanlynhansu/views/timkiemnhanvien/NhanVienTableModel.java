package quanlynhansu.views.timkiemnhanvien;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import quanlynhansu.models.ChucVu;
import quanlynhansu.models.CoSoLamViec;
import quanlynhansu.models.NhanVien;

@SuppressWarnings("serial")
public class NhanVienTableModel extends AbstractTableModel {
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	private final String[] headers = new String[] { "Mã nhân viên", "Tên nhân viên", "Giới tính", "SĐT", "Ngày sinh", "Địa chỉ", "Lương" };
	private ArrayList<NhanVien> source;
	private ArrayList<NhanVien> filtered;
	private String lastSearchKeyword;

	public NhanVienTableModel(ArrayList<NhanVien> source) {
		this.source = source;
		this.filtered = new ArrayList<>(source);
	}

	public void filter(String keyword, CoSoLamViec cslv) {
		this.filtered.clear();
		
		for (NhanVien nv : source) {
			boolean isMatchKeyword = nv.getTenNV().toLowerCase().contains(keyword.toLowerCase());
			boolean isInCSLV = nv.getMaCS().equals(cslv.getMaCS()) || cslv.getMaCS() == null;

			if (isMatchKeyword && isInCSLV) {
				this.filtered.add(nv);
			}
		}
		
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return filtered.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return filtered.get(rowIndex).getMaNV();
		case 1:
			return filtered.get(rowIndex).getTenNV();
		case 2:
			return filtered.get(rowIndex).isNu() ? "Nữ" : "Nam";
		case 3:
			return filtered.get(rowIndex).getSdt();
		case 4:
			return filtered.get(rowIndex).getNgaySinh();
		case 5:
			return filtered.get(rowIndex).getDiaChi();
		case 6:
			return formatter.format(filtered.get(rowIndex).getLuong());
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}
}
