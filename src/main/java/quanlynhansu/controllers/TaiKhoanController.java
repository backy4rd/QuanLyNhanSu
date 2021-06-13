package quanlynhansu.controllers;

import java.sql.SQLException;

import quanlynhansu.models.*;
import quanlynhansu.providers.ControllerException;

public class TaiKhoanController {
	/**
	 * Thực hiện đăng nhập
	 * @param username
	 * @param password
	 * @return Nhân viên sở hữu tài khoản truyền vào
	 * @throws ControllerException
	 */
	public NhanVien dangNhap(String username, String password) throws ControllerException {
		try {
			TaiKhoan tk = TaiKhoan.getTaiKhoan(username);

			if (tk == null) {
				throw new ControllerException("Tài khoản không tồn tại");
			}
			if (!tk.getPassword().equals(password)) {
				throw new ControllerException("Mật khẩu không chính xác");
			}

			NhanVien nv = NhanVien.getNhanVien(tk.getMaNV());
			nv.setChucVu(ChucVu.getChucVu(nv.getMaCV()));
			return nv;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}

	}

	/**
	 * Cấp tài khoản cho nhân viên
	 * @param tk
	 * @throws ControllerException
	 */
	public void capTaiKhoan(TaiKhoan tk) throws ControllerException {
		try {
			if (TaiKhoan.getTaiKhoan(tk.getUsername()) != null) {
				throw new ControllerException("Tài khoản đã tồn tại");
			}
			if (NhanVien.getNhanVien(tk.getMaNV()) == null) {
				throw new ControllerException("nhân viên không tồn tại");
			}

			TaiKhoan.createTaiKhoan(tk);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
}
