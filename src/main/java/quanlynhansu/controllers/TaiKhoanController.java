package quanlynhansu.controllers;

import quanlynhansu.models.*;
import quanlynhansu.providers.ControllerException;

public class TaiKhoanController {
	public NhanVien dangNhap(String username, String password) throws ControllerException {
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
	}

	public void capTaiKhoan(String username, String password, String maNV) throws ControllerException {
		if (TaiKhoan.getTaiKhoan(username) != null) {
			throw new ControllerException("Tài khoản đã tồn tại");
		}
		if (NhanVien.getNhanVien(maNV) == null) {
			throw new ControllerException("nhân viên không tồn tại");
		}

		TaiKhoan.createTaiKhoan(new TaiKhoan(username, password, maNV));
	}
}
