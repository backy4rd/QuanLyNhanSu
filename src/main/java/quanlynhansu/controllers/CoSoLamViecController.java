package quanlynhansu.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.CoSoLamViec;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

public class CoSoLamViecController {
	public CoSoLamViec getCoSoLamViec(String maCS) throws ControllerException {
		try {
			return CoSoLamViec.getCoSoLamViec(maCS);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ArrayList<CoSoLamViec> getCoSoLamViecs() throws ControllerException {
		try {
			return CoSoLamViec.getCoSoLamViecs();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
	
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
