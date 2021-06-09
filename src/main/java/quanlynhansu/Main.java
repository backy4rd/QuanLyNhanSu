package quanlynhansu;

import quanlynhansu.controllers.NhanVienController;
import quanlynhansu.models.DiemDanh;
import quanlynhansu.models.NhanVien;
import quanlynhansu.providers.DBConnection;
import quanlynhansu.views.App;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(() -> {
			new App();
		});
		

//		
//		ArrayList<NhanVien> list = new NhanVienController().getNhanViens();
//		Random random = new Random();
//
//		for (int i = 0; i < 120; i++) {
//			Date d = new Date(2021 - 1900, 1, i + 1);
//			java.util.Date c = new java.util.Date(d.getTime());
//			
//
//			if (c.getDay() == 6 || c.getDay() == 0) {
//				continue;
//			}
//				
//			for (NhanVien nv : list) {
//				if (random.nextInt(100) > 90) continue;
//
//				Object[] params = new Object[] { nv.getMaNV(), d };
//				DBConnection.getInstance().executeProcedureUpdate("{ CALL diem_danh(?,?) }", params);
//			}
//
//			System.out.println(i);
//		}
	}
}
