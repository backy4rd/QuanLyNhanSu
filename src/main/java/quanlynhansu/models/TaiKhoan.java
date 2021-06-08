package quanlynhansu.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import quanlynhansu.providers.DBConnection;

public class TaiKhoan {
	private String username;
	private String password;
	private String maNV;

	public TaiKhoan(String username, String password, String maNV) {
		this.username = username;
		this.password = password;
		this.maNV = maNV;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	
	@Override
	public String toString() {
		return this.username;
	}

	public static void createTaiKhoan(TaiKhoan tk) throws SQLException {
		String query = "INSERT INTO TaiKhoan VALUES(?,?,?)";
		Object[] params = { tk.username, tk.password, tk.maNV };
		DBConnection.getInstance().executeUpdate(query, params);
	}

	public static TaiKhoan getTaiKhoan(String username) throws SQLException {
		Object[] params = { username };
		String query = "SELECT * FROM TaiKhoan WHERE Username=?";
		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			if (rs.next()) {
				return new TaiKhoan(rs.getString("Username"), rs.getString("Password"), rs.getString("MaNV"));
			}
			return null;
		}
	}
}
