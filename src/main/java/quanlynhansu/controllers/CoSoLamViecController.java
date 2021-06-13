package quanlynhansu.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.CoSoLamViec;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

public class CoSoLamViecController {
	/**
	 * Lấy cơ sở theo mã cơ sở
	 * @param maCS
	 * @return
	 * @throws ControllerException
	 */
	public CoSoLamViec getCoSoLamViec(String maCS) throws ControllerException {
		try {
			return CoSoLamViec.getCoSoLamViec(maCS);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Lấy danh sách tất cả các cơ sở
	 * @return
	 * @throws ControllerException
	 */
	public ArrayList<CoSoLamViec> getCoSoLamViecs() throws ControllerException {
		try {
			return CoSoLamViec.getCoSoLamViecs();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
	
	/**
	 * Tạo cơ sở làm việc
	 * @param cslv
	 * @return
	 * @throws ControllerException
	 */
	public CoSoLamViec createCoSoLamViec(CoSoLamViec cslv) throws ControllerException {
		try {
			cslv.setMaCS(Util.generateId("CS", "CoSoLamViec", "MaCS"));
			CoSoLamViec.createCoSoLamViec(cslv);
			return cslv;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Cập nhật cơ sở
	 * @param maCS
	 * @param cslv
	 * @return
	 * @throws ControllerException
	 */
	public CoSoLamViec updateCoSoLamViec(String maCS, CoSoLamViec cslv) throws ControllerException {
		try {
			if (CoSoLamViec.getCoSoLamViec(maCS) == null) {
				throw new ControllerException("Cơ sở làm việc không tồn tại");
			}

			cslv.setMaCS(maCS);
			CoSoLamViec.updateCoSoLamViec(maCS, cslv);
			return cslv;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
	
	/**
	 * Xóa cơ sở
	 * @param maCS
	 * @throws ControllerException
	 */
	public void deleteCoSoLamViec(String maCS) throws ControllerException {
		try {
			if (CoSoLamViec.getCoSoLamViec(maCS) == null) {
				throw new ControllerException("Cơ sở làm việc không tồn tại");
			}

			CoSoLamViec.deleteCoSoLamViec(maCS);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
}
