package quanlynhansu.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.CoSoLamViec;
import quanlynhansu.providers.ControllerException;

public class CoSoLamViecController {
	public CoSoLamViec getChucVu(String maCV) throws ControllerException {
		try {
			return CoSoLamViec.getCoSoLamViec(maCV);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	public ArrayList<CoSoLamViec> getChucVus() throws ControllerException {
		try {
			return CoSoLamViec.getCoSoLamViecs();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
}
