package quanlynhansu.models;

import quanlynhansu.providers.DBConnection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private boolean nu;
	private String sdt;
	private Date ngaySinh;
	private String diaChi;
	private double luong;
	private String maCS;
	private String maCV;

	// relationship
	private ChucVu chucVu;

	public NhanVien(String maNV, String tenNV, boolean nu, String sdt, Date ngaySinh, String diaChi, double luong,
			String maCS, String maCV) {
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.nu = nu;
		this.sdt = sdt;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.luong = luong;
		this.maCS = maCS;
		this.maCV = maCV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public boolean isNu() {
		return nu;
	}

	public void setNu(boolean nu) {
		this.nu = nu;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public String getMaCS() {
		return maCS;
	}

	public void setMaCS(String maCS) {
		this.maCS = maCS;
	}

	public String getMaCV() {
		return maCV;
	}

	public void setMaCV(String maCV) {
		this.maCV = maCV;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	@Override
	public String toString() {
		return this.tenNV;
	}

	public static NhanVien getNhanVien(String maNV) throws SQLException {
		Object[] params = { maNV };
		String query = "SELECT * FROM NhanVien WHERE MaNV=?";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			if (rs.next()) {
				return new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getBoolean("Nu"),
						rs.getString("SDT"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getDouble("Luong"),
						rs.getString("MaCS"), rs.getString("MaCV"));
			}
			return null;
		}

	}

	public static ArrayList<NhanVien> getNhanViens() throws SQLException {
		ArrayList<NhanVien> list = new ArrayList<>();
		String query = "SELECT * FROM NhanVien";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query)) {
			while (rs.next()) {
				list.add(new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getBoolean("Nu"),
						rs.getString("SDT"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getDouble("Luong"),
						rs.getString("MaCS"), rs.getString("MaCV")));
			}
		}

		return list;
	}

	public static void diemDanh(String maNV, Date ngay) throws SQLException {
		Object[] params = new Object[] { maNV, ngay };
		DBConnection.getInstance().executeProcedureUpdate("{ CALL diem_danh(?, ?) }", params);
	}
}
