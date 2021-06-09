package quanlynhansu.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.providers.DBConnection;

public class Thuong {
	private String maThuong;
	private double soTien;
	private Date ngayThuong;
	private String maNV;

	public Thuong(String maThuong, double soTien, Date ngayThuong, String maNV) {
		this.maThuong = maThuong;
		this.soTien = soTien;
		this.ngayThuong = ngayThuong;
		this.maNV = maNV;
	}

	public String getMaThuong() {
		return maThuong;
	}

	public void setMaThuong(String maThuong) {
		this.maThuong = maThuong;
	}

	public double getSoTien() {
		return soTien;
	}

	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}

	public Date getNgayThuong() {
		return ngayThuong;
	}

	public void setNgayThuong(Date ngayThuong) {
		this.ngayThuong = ngayThuong;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public static ArrayList<Thuong> getThuongTrongThang(String maNV, int thang, int nam) throws SQLException {

		ArrayList<Thuong> list = new ArrayList<>();
		Object[] params = new Object[] { maNV, thang, nam };
		String query = "SELECT * FROM Thuong WHERE MaNV = ? AND MONTH(NgayThuong) = ? AND YEAR(NgayThuong) = ?";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			while (rs.next()) {
				list.add(new Thuong(rs.getString("MaThuong"), rs.getDouble("SoTien"), rs.getDate("NgayThuong"),
						rs.getString("MaNV")));
			}
		}

		return list;
	}
}
