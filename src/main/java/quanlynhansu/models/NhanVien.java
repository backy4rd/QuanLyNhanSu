package quanlynhansu.models;

import quanlynhansu.providers.DBConnection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public static NhanVien getNhanVien(String maNV) {
		Object[] params = { maNV };
		String query = "SELECT * FROM NhanVien WHERE MaNV=?";

		try (ResultSet rs = DBConnection.getInstance().executeQuery(query, params)) {
			if (rs.next()) {
				return new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getBoolean("Nu"),
						rs.getString("SDT"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getDouble("Luong"),
						rs.getString("MaCS"), rs.getString("MaCV"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
