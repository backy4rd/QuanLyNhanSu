package quanlynhansu.views;

import java.awt.*;
import javax.swing.*;

import quanlynhansu.controllers.ChucVuController;
import quanlynhansu.controllers.NhanVienController;
import quanlynhansu.models.NhanVien;
import quanlynhansu.providers.AuthorizedUser;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;
import quanlynhansu.views.bangluong.BangLuong;
import quanlynhansu.views.captaikhoan.CapTaiKhoan;
import quanlynhansu.views.dangnhap.DangNhap;
import quanlynhansu.views.khenthuong.KhenThuong;
import quanlynhansu.views.qlchucvu.QuanLyChucVu;
import quanlynhansu.views.qlcosolamviec.QuanLyCoSoLamViec;
import quanlynhansu.views.qlnhanvien.QuanLyNhanVien;
import quanlynhansu.views.timkiemnhanvien.TimKiemNhanVien;

@SuppressWarnings("serial")
public class App extends JFrame {
	NhanVienController nvc = new NhanVienController();
	ChucVuController cvc = new ChucVuController();

	JMenuBar menubar = new JMenuBar();

	JMenu menuQuanLy = new JMenu("Quản lý");
	JMenuItem menuItemQLNV = new JMenuItem("Quản lý nhân viên");
	JMenuItem menuItemQLCSLV = new JMenuItem("Quản lý cơ sở làm việc");
	JMenuItem menuItemQLCV = new JMenuItem("Quản lý chức vụ");

	JMenu menuChucNang = new JMenu("Chức năng");
	JMenuItem menuItemTimNV = new JMenuItem("Tìm kiếm nhân viên");
	JMenuItem menuItemKhenThuongNV = new JMenuItem("Khen thưởng nhân viên");
	JMenuItem menuItemBangLuong = new JMenuItem("Xem bảng lương");

	JMenu menuTienIch = new JMenu("Tiện ích");
	JMenuItem menuItemDangNhap = new JMenuItem("Đăng nhập");
	JMenuItem menuItemCapTK = new JMenuItem("Cấp tài khoản");
	JMenuItem menuItemDiemDanh = new JMenuItem("Điểm danh");
	JMenuItem menuItemDangXuat = new JMenuItem("Đăng xuất");
	JMenuItem menuItemThoat = new JMenuItem("Thoát");

	private int capBacLonNhat;

	public App() {
		super("Quản lý nhân sự");

		createGUI();

		try {
			capBacLonNhat = cvc.getCapBacLonNhat();
		} catch (ControllerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

		toggleFeatures();

		assignListeners();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		new DangNhap(this);
	}

	public void createGUI() {
		setLayout(new BorderLayout());

		menuQuanLy.add(menuItemQLNV);
		menuQuanLy.add(menuItemQLCSLV);
		menuQuanLy.add(menuItemQLCV);

		menuChucNang.add(menuItemTimNV);
		menuChucNang.add(menuItemKhenThuongNV);
		menuChucNang.add(menuItemBangLuong);

		menuTienIch.add(menuItemDiemDanh);
		menuTienIch.add(menuItemDangNhap);
		menuTienIch.add(menuItemCapTK);
		menuTienIch.add(menuItemDangXuat);
		menuTienIch.add(menuItemThoat);

		menubar.add(menuQuanLy);
		menubar.add(menuChucNang);
		menubar.add(menuTienIch);

		this.add(menubar, BorderLayout.NORTH);

		this.add(new JLabel(new ImageIcon(this.getClass().getResource("../images/background.jpg"))), BorderLayout.CENTER);

		this.setSize(1024, 576);
		this.setResizable(false);
		Util.centerScreen(this);
	}

	public void assignListeners() {
		// menu quan ly
		menuItemQLNV.addActionListener(e -> {
			new QuanLyNhanVien();
		});

		menuItemQLCV.addActionListener(e -> {
			new QuanLyChucVu();
		});

		menuItemQLCSLV.addActionListener(e -> {
			new QuanLyCoSoLamViec();
		});

		// menu chuc nang
		menuItemTimNV.addActionListener(e -> {
			new TimKiemNhanVien();
		});

		menuItemKhenThuongNV.addActionListener(e -> {
			new KhenThuong();
		});

		menuItemBangLuong.addActionListener(e -> {
			new BangLuong();
		});

		// menu tien ich
		menuItemDiemDanh.addActionListener(e -> {
			try {
				nvc.diemDanh(AuthorizedUser.getInstance().getNhanVien().getMaNV());
				JOptionPane.showMessageDialog(null, "Điểm danh thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});

		menuItemDangNhap.addActionListener(e -> {
			new DangNhap(this);
		});

		menuItemDangXuat.addActionListener(e -> {
			AuthorizedUser.getInstance().setNhanVien(null);
			toggleFeatures();
		});

		menuItemCapTK.addActionListener(e -> {
			new CapTaiKhoan();
		});

		menuItemThoat.addActionListener(e -> {
			System.exit(ABORT);
		});

	}

	public void toggleFeatures() {
		NhanVien authorizedNV = AuthorizedUser.getInstance().getNhanVien();

		if (authorizedNV == null) {
			menuQuanLy.setEnabled(false);
			menuChucNang.setEnabled(false);

			menuItemQLCSLV.setEnabled(false);
			menuItemQLCV.setEnabled(false);
			menuItemKhenThuongNV.setEnabled(false);
			menuItemQLNV.setEnabled(false);

			menuItemDiemDanh.setEnabled(false);
			menuItemDangXuat.setEnabled(false);
			menuItemCapTK.setEnabled(false);

			menuItemDangNhap.setEnabled(true);
		} else {
			menuQuanLy.setEnabled(true);
			menuChucNang.setEnabled(true);

			if (authorizedNV.getChucVu().getCapBac() == capBacLonNhat) {
				menuItemQLCSLV.setEnabled(true);
				menuItemQLCV.setEnabled(true);
			}

			if (authorizedNV.getChucVu().getCapBac() != 1) {
				menuItemKhenThuongNV.setEnabled(true);
				menuItemQLNV.setEnabled(true);
			}

			menuItemDiemDanh.setEnabled(true);
			menuItemDangXuat.setEnabled(true);
			menuItemCapTK.setEnabled(true);

			menuItemDangNhap.setEnabled(false);
		}
	}
}