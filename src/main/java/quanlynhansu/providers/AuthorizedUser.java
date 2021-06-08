package quanlynhansu.providers;

import quanlynhansu.models.*;

/**
 * Chứa thông tin đăng nhập
 *
 */
public class AuthorizedUser {
	private static AuthorizedUser instance;
	private NhanVien nhanVien;

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public static void setInstance(AuthorizedUser instance) {
		AuthorizedUser.instance = instance;
	}

	public static AuthorizedUser getInstance() {
		if (instance == null) {
			instance = new AuthorizedUser();
		}

		return instance;
	}

	private AuthorizedUser() {

	}
}
