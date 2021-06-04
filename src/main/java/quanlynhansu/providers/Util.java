package quanlynhansu.providers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Util {
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
}
