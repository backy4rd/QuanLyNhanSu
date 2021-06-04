package quanlynhansu.providers;

import java.sql.*;

public class DBConnection {
	private static final String URI = "jdbc:mysql://localhost:3306/QuanLyNhanSu";
	private static final String USERNAME = "tn208";
	private static final String PASSWORD = "nhom5";

	private static DBConnection instance;

	public Connection conn;

	private DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URI, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public ResultSet executeQuery(String query) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;

	}

	public ResultSet executeQuery(String query, Object[] params) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		appendParamaters(ps, params);
		ResultSet rs = ps.executeQuery();
		return rs;

	}

	public int executeUpdate(String query) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		return ps.executeUpdate();

	}

	public int executeUpdate(String query, Object[] params) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		appendParamaters(ps, params);
		return ps.executeUpdate();

	}

	private void appendParamaters(PreparedStatement ps, Object[] params) throws SQLException {
		for (int i = 0; i < params.length; i++) {
			if (params[i] instanceof String) {
				ps.setString(i + 1, (String) params[i]);
			} else if (params[i] instanceof Integer) {
				ps.setInt(i + 1, (Integer) params[i]);
			} else if (params[i] instanceof Double) {
				ps.setDouble(i + 1, (Double) params[i]);
			} else if (params[i] instanceof Float) {
				ps.setFloat(i + 1, (Float) params[i]);
			} else if (params[i] instanceof Boolean) {
				ps.setBoolean(i + 1, (Boolean) params[i]);
			} else if (params[i] instanceof Date) {
				ps.setDate(i + 1, (Date) params[i]);
			} else {
				ps.setObject(i + 1, params[i]);
			}
		}
	}
}
