package quanlynhansu.models;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	public static ChucVu getChucVu(String maCV) {
		Object[] params = { maCV };
		String query = "SELECT * FROM ChucVu WHERE MaCV=?";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			if (rs.next()) {
				return new ChucVu(rs.getString("MaCV"), rs.getString("TenCV"), rs.getInt("CapBac"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
