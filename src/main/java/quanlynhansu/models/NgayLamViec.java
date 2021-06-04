package quanlynhansu.models;

import java.sql.Date;

public class NgayLamViec {
	private Date ngay;

	public NgayLamViec(Date ngay) {
		this.ngay = ngay;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
}
