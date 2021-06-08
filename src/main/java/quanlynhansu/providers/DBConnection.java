package quanlynhansu.providers;

import java.sql.*;

public class DBConnection {
	private static final String URI = "jdbc:mysql://localhost:3306/QuanLyNhanSu";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

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

	/**
	 * Thực hiện SELECT không tham số
	 * 
	 * @param query
	 * @return ResultSet chứa dữ liệu
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;

	}

	/**
	 * Thực hiện SELECT có tham số
	 * 
	 * @param query
	 * @param params
	 * @return ResultSet chứa dữ liệu
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query, Object[] params) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		appendParamaters(ps, params);
		ResultSet rs = ps.executeQuery();
		return rs;

	}

	/**
	 * Thực hiện INSERT, UPDATE, DELETE không tham số
	 * 
	 * @param query
	 * @return Số lượng dòng bị ảnh hưởng bởi câu truy vấn
	 * @throws SQLException
	 */
	public int executeUpdate(String query) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		return ps.executeUpdate();
	}

	/**
	 * Thực hiện INSERT, UPDATE, DELETE có tham số
	 * 
	 * @param query
	 * @param params
	 * @return Số lượng dòng bị ảnh hưởng bởi câu truy vấn
	 * @throws SQLException
	 */
	public int executeUpdate(String query, Object[] params) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		appendParamaters(ps, params);
		return ps.executeUpdate();

	}

	public ResultSet executeProcedureQuery(String query) throws SQLException {
		CallableStatement cl = conn.prepareCall(query);
		return cl.executeQuery();
	}

	public ResultSet executeProcedureQuery(String query, Object[] params) throws SQLException {
		CallableStatement cl = conn.prepareCall(query);
		appendParamaters(cl, params);
		return cl.executeQuery();
	}

	public int executeProcedureUpdate(String query) throws SQLException {
		CallableStatement cl = conn.prepareCall(query);
		return cl.executeUpdate();
	}

	public int executeProcedureUpdate(String query, Object[] params) throws SQLException {
		CallableStatement cl = conn.prepareCall(query);
		appendParamaters(cl, params);
		return cl.executeUpdate();
	}

	/**
	 * Gán những tham số vào câu truy vấn
	 * 
	 * @param ps
	 * @param params
	 * @throws SQLException
	 */
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
