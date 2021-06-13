package quanlynhansu.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.ChucVu;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

public class ChucVuController {
	/**
	 * Lấy chức vụ bằng mã chức vụ
	 * @param maCV
	 * @return
	 * @throws ControllerException
	 */
	public ChucVu getChucVu(String maCV) throws ControllerException {
		try {
			return ChucVu.getChucVu(maCV);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Lấy danh sách tất cả các chức vụ
	 * @return
	 * @throws ControllerException
	 */
	public ArrayList<ChucVu> getChucVus() throws ControllerException {
		try {
			return ChucVu.getChucVus();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
	
	/**
	 * Lấy những chức vụ có cấp bậc nhỏ hơn tham số truyền vào
	 * @param capBac
	 * @return
	 * @throws ControllerException
	 */
	public ArrayList<ChucVu> getChucVusCapBacNhoHon(int capBac) throws ControllerException {
		try {
			return ChucVu.getChucVusCapBacNhoHon(capBac);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Tạo chức vụ mới
	 * @param chucVu
	 * @return
	 * @throws ControllerException
	 */
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

	/**
	 * Cập nhật lại chức vụ
	 * @param maCV
	 * @param chucVu
	 * @return
	 * @throws ControllerException
	 */
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

	/**
	 * Xóa chức vụ
	 * @param maCV
	 * @throws ControllerException
	 */
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
	
	/**
	 * Lấy cấp bậc lớn nhất của các chức vụ
	 * @return
	 * @throws ControllerException
	 */
	public int getCapBacLonNhat() throws ControllerException {
		try {
			ArrayList<ChucVu> chucVus = ChucVu.getChucVusLonNhat();
			return chucVus.get(0).getCapBac();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
}
