package quanlynhansu.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.providers.DBConnection;

public class CoSoLamViec {
	private String maCS;
	private String tenCS;
	private String diaChi;

	public CoSoLamViec(String maCS, String tenCS, String diaChi) {
		this.maCS = maCS;
		this.tenCS = tenCS;
		this.diaChi = diaChi;
	}

	public String getMaCS() {
		return maCS;
	}

	public void setMaCS(String maCS) {
		this.maCS = maCS;
	}

	public String getTenCS() {
		return tenCS;
	}

	public void setTenCS(String tenCS) {
		this.tenCS = tenCS;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return this.tenCS;
	}

	public static CoSoLamViec getCoSoLamViec(String maCS) throws SQLException {
		Object[] params = { maCS };
		String query = "SELECT * FROM ChucVu WHERE MaCV=?";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			if (rs.next()) {
				return new CoSoLamViec(rs.getString("MaCS"), rs.getString("TenCS"), rs.getString("DiaChi"));
			}
			return null;
		}

	}

	public static ArrayList<CoSoLamViec> getCoSoLamViecs() throws SQLException {
		ArrayList<CoSoLamViec> list = new ArrayList<>();
		String query = "SELECT * FROM CoSoLamViec";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query)) {
			while (rs.next()) {
				list.add(new CoSoLamViec(rs.getString("MaCS"), rs.getString("TenCS"), rs.getString("DiaChi")));
			}
		}

		return list;
	}
}
