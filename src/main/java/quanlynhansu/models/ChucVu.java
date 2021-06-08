package quanlynhansu.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.providers.DBConnection;

public class ChucVu {
	private String maCV;
	private String tenCV;
	private int capBac;

	public ChucVu(String maCV, String tenCV, int capBac) {
		this.maCV = maCV;
		this.tenCV = tenCV;
		this.capBac = capBac;
	}

	public String getMaCV() {
		return maCV;
	}

	public void setMaCV(String maCV) {
		this.maCV = maCV;
	}

	public String getTenCV() {
		return tenCV;
	}

	public void setTenCV(String tenCV) {
		this.tenCV = tenCV;
	}

	public int getCapBac() {
		return capBac;
	}

	public void setCapBac(int capBac) {
		this.capBac = capBac;
	}

	@Override
	public String toString() {
		return this.tenCV;
	}

	public static ChucVu getChucVu(String maCV) throws SQLException {
		Object[] params = { maCV };
		String query = "SELECT * FROM ChucVu WHERE MaCV=?";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			if (rs.next()) {
				return new ChucVu(rs.getString("MaCV"), rs.getString("TenCV"), rs.getInt("CapBac"));
			}
			return null;
		}
	}

	public static ArrayList<ChucVu> getChucVus() throws SQLException {
		ArrayList<ChucVu> list = new ArrayList<>();
		String query = "SELECT * FROM ChucVu";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query)) {
			while (rs.next()) {
				list.add(new ChucVu(rs.getString("MaCV"), rs.getString("TenCV"), rs.getInt("CapBac")));
			}
		}

		return list;
	}
}
