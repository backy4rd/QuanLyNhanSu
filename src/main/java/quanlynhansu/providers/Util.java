package quanlynhansu.providers;

import java.awt.Component;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JFrame;

/**
 * Chứa các phương thức cần thiết
 */
public class Util {
	/**
	 * Tạo ID ngẫu nhiên không trùng lặp
	 * @param prefix tiền tố của id, vd: "NV01" thì tiền tố là "NV"
	 * @param tableName tên bảng cần tạo ID
	 * @param field tên trường cần tạo ID
	 * @return id
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
	 * Chú ý: JFrame cần được setSize trước khi gọi hàm này
	 * @param form
	 */
	public static void centerScreen(JFrame form) {
		Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		form.setLocation((dim.width - form.getWidth()) / 2, (dim.height - form.getHeight()) / 2);
	}
}
