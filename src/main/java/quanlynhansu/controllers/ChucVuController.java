package quanlynhansu.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.ChucVu;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

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
	
	public ArrayList<ChucVu> getChucVusCapBacNhoHon(int capBac) throws ControllerException {
		try {
			return ChucVu.getChucVusCapBacNhoHon(capBac);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ChucVu createChucVu(ChucVu chucVu) throws ControllerException {
		try {
			chucVu.setMaCV(Util.generateId("CV", "ChucVu", "MaCV"));
			ChucVu.createChucVu(chucVu);
			return chucVu;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ChucVu updateChucVu(String maCV, ChucVu chucVu) throws ControllerException {
		try {
			if (ChucVu.getChucVu(maCV) == null) {
				throw new ControllerException("Chức vụ không tồn tại");
			}

			chucVu.setMaCV(maCV);
			ChucVu.updateChucVu(maCV, chucVu);
			return chucVu;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
	public void deleteChucVu(String maCV) throws ControllerException {
		try {
			if (ChucVu.getChucVu(maCV) == null) {
				throw new ControllerException("Chức vụ không tồn tại");
			}

			ChucVu.deleteChucVu(maCV);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
}
