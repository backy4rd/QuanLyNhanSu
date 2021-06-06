package quanlynhansu.providers;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JFrame;

public class Util {
	/**
	 * Tạo ID ngẫu nhiên và không trùng lăp
	 * @param	prefix	Tiền tố của ID, vd: "NV01" có tiền tố là "NV"
	 * @param	tableName	Tên bảng cần tạo ID
	 * @param	field	Tên trường của ID
	 */
	public static String generateId(String prefix, String tableName, String field) {
		Random random = new Random();
		String query = String.format("SELECT 1 FROM %s WHERE %s = ?", tableName, field);

		String id = null;
		boolean isExist = true;

		do {
			id = prefix + (random.nextInt((int) Math.pow(10, 10 - prefix.length())));
			System.out.println(id);

			try (ResultSet rs = DBConnection.getInstance().executeQuery(query, new Object[] { id })) {
				isExist = rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
				break;
			}
		} while (isExist);

		return id;
	}
	
	/**
	 * Căn JFrame ra giữa màn hình
	 * Chú ý: JFrame cần được setSize trước khi thực hiện căn giữa
	 */
	public static void centerScreen(JFrame form) {
		Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		form.setLocation((dim.width - form.getWidth()) / 2, (dim.height - form.getHeight())/ 2);
	}
}
