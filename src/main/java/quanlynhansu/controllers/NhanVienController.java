package quanlynhansu.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.DiemDanh;
import quanlynhansu.models.NhanVien;
import quanlynhansu.models.Thuong;
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

			DiemDanh.createDiemDanh(maNV, now);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ArrayList<DiemDanh> getDiemDanhtrongThang(String maNV, int thang, int nam) throws ControllerException {
		try {
			return DiemDanh.getDiemDanhTrongThang(maNV, thang, nam);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ArrayList<Thuong> getThuongTrongThang(String maNV, int thang, int nam) throws ControllerException {
		try {
			return Thuong.getThuongTrongThang(maNV, thang, nam);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ArrayList<NhanVien> getNhanViensCapBacNhoHon(int capBac) throws ControllerException {
		try {
			return NhanVien.getNhanViensCapBacNhoHon(capBac);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

}
