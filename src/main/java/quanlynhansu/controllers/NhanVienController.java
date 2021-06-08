package quanlynhansu.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.DiemDanh;
import quanlynhansu.models.NhanVien;
import quanlynhansu.providers.ControllerException;

public class NhanVienController {
	public NhanVien getNhanVien(String maNV) throws ControllerException {
		try {
			return NhanVien.getNhanVien(maNV);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ArrayList<NhanVien> getNhanViens() throws ControllerException {
		try {
			return NhanVien.getNhanViens();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public void DiemDanh(String maNV) throws ControllerException {
		try {
			Date now = new Date(System.currentTimeMillis());

			if (DiemDanh.getDiemDanh(maNV, now) != null) {
				throw new ControllerException("Đã điểm danh");
			}

			NhanVien.diemDanh(maNV, now);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
}
