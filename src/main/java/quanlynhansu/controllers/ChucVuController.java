package quanlynhansu.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.ChucVu;
import quanlynhansu.providers.ControllerException;

public class ChucVuController {
	public ChucVu getChucVu(String maCV) throws ControllerException {
		try {
			return ChucVu.getChucVu(maCV);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ArrayList<ChucVu> getChucVus() throws ControllerException {
		try {
			return ChucVu.getChucVus();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
}
