package quanlynhansu.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import quanlynhansu.models.DiemDanh;
import quanlynhansu.models.NhanVien;
import quanlynhansu.models.Thuong;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

public class NhanVienController {
	/**
	 * Lấy nhân viên qua mã nhân viên
	 * @param maNV
	 * @return
	 * @throws ControllerException
	 */
	public NhanVien getNhanVien(String maNV) throws ControllerException {
		try {
			return NhanVien.getNhanVien(maNV);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Lấy danh sách nhân viên
	 * @return
	 * @throws ControllerException
	 */
	public ArrayList<NhanVien> getNhanViens() throws ControllerException {
		try {
			return NhanVien.getNhanViens();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Điểm danh cho ngày hôm nay
	 * @param maNV
	 * @throws ControllerException
	 */
	public void diemDanh(String maNV) throws ControllerException {
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

	/**
	 * Lấy danh sách Điểm Danh trong tháng chỉ định
	 * @param maNV
	 * @param thang
	 * @param nam
	 * @return
	 * @throws ControllerException
	 */
	public ArrayList<DiemDanh> getDiemDanhtrongThang(String maNV, int thang, int nam) throws ControllerException {
		try {
			return DiemDanh.getDiemDanhTrongThang(maNV, thang, nam);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Lấy danh sách Thưởng trong tháng chỉ định
	 * @param maNV
	 * @param thang
	 * @param nam
	 * @return
	 * @throws ControllerException
	 */
	public ArrayList<Thuong> getThuongTrongThang(String maNV, int thang, int nam) throws ControllerException {
		try {
			return Thuong.getThuongTrongThang(maNV, thang, nam);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Lấy danh sách nhân viên có cấp bậc nhỏ hơn tham số truyền vào
	 * @param capBac
	 * @return
	 * @throws ControllerException
	 */
	public ArrayList<NhanVien> getNhanViensCapBacNhoHon(int capBac) throws ControllerException {
		try {
			return NhanVien.getNhanViensCapBacNhoHon(capBac);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Tạo mới nhân viên
	 * @param nv
	 * @return
	 * @throws ControllerException
	 */
	public NhanVien createNhanVien(NhanVien nv) throws ControllerException{
		try {
			nv.setMaNV(Util.generateId("NV", "NhanVien", "MaNV"));
			NhanVien.createNhanVien(nv);
			return nv;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Cập nhật nhân viên
	 * @param maNV
	 * @param nv
	 * @return
	 * @throws ControllerException
	 */
	public NhanVien updateNhanVien(String maNV, NhanVien nv) throws ControllerException{
		try {
			if (NhanVien.getNhanVien(maNV) == null) {
				throw new ControllerException("Nhân viên không tồn tại");
			}
			NhanVien.updateNhanVien(maNV, nv);
			nv.setMaNV(maNV);
			return nv;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Xóa nhân viên
	 * @param maNV
	 * @throws ControllerException
	 */
	public void deleteNhanVien(String maNV) throws ControllerException{
		try {
			if (NhanVien.getNhanVien(maNV) == null) {
				throw new ControllerException("Nhân viên không tồn tại");
			}
			NhanVien.deleteNhanVien(maNV);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}

	/**
	 * Khen thưởng cho nhân viên
	 * @param maNV
	 * @param tienThuong
	 * @return
	 * @throws ControllerException
	 */
	public Thuong khenThuong(String maNV, double tienThuong) throws ControllerException{
		try {
			if (NhanVien.getNhanVien(maNV) == null) {
				throw new ControllerException("Nhân viên không tồn tại");
			}
			String id = Util.generateId("T", "Thuong", "MaThuong");
			Date ngayThuong = new Date(System.currentTimeMillis());
			Thuong thuong = new Thuong(id, tienThuong, ngayThuong, maNV);
			Thuong.createThuong(thuong);
			return thuong;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ControllerException("Lỗi truy vấn");
		}
	}
}
