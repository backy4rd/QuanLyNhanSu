package quanlynhansu.models;

import java.sql.Date;

public class Thuong {
	private String maThuong;
	private double soTien;
	private Date ngayThuong;
	private String maNV;

	public Thuong(String maThuong, double soTien, Date ngayThuong, String maNV) {
		this.maThuong = maThuong;
		this.soTien = soTien;
		this.ngayThuong = ngayThuong;
		this.maNV = maNV;
	}

	public String getMaThuong() {
		return maThuong;
	}

	public void setMaThuong(String maThuong) {
		this.maThuong = maThuong;
	}

	public double getSoTien() {
		return soTien;
	}

	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}

	public Date getNgayThuong() {
		return ngayThuong;
	}

	public void setNgayThuong(Date ngayThuong) {
		this.ngayThuong = ngayThuong;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
}
