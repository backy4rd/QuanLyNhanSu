package quanlynhansu.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.providers.DBConnection;

public class DiemDanh {
	private String maNV;
	private Date ngay;

	public DiemDanh(String maNV, Date ngay) {
		super();
		this.maNV = maNV;
		this.ngay = ngay;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}


	public static void createDiemDanh(String maNV, Date ngay) throws SQLException {
		Object[] params = new Object[] { maNV, ngay };
		DBConnection.getInstance().executeProcedureUpdate("{ CALL diem_danh(?, ?) }", params);
	}

	public static DiemDanh getDiemDanh(String maNV, Date ngay) throws SQLException {
		Object[] params = new Object[] { maNV, ngay };
		String query = "SELECT * FROM DiemDanh WHERE MaNV=? AND Ngay=?";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			if (rs.next()) {
				return new DiemDanh(rs.getString("MaNV"), rs.getDate("Ngay"));
			}
			return null;
		}
	}

	public static ArrayList<DiemDanh> getDiemDanhTrongThang(String maNV, int thang, int nam) throws SQLException {
		ArrayList<DiemDanh> list = new ArrayList<>();
		Object[] params = new Object[] { maNV, thang, nam };
		String query = "SELECT * FROM DiemDanh "
				+ "WHERE MaNV = ? AND MONTH(Ngay) = ? AND YEAR(Ngay) = ?";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			while (rs.next()) {
				list.add(new DiemDanh(rs.getString("MaNV"), rs.getDate("Ngay")));
			}
		}

		return list;
	}
}
