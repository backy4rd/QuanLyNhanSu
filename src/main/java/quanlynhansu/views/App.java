package quanlynhansu.views;

import java.awt.*;
import javax.swing.*;

import quanlynhansu.providers.AuthorizedUser;
import quanlynhansu.providers.Util;

@SuppressWarnings("serial")
public class App extends JFrame {
	JMenuBar menubar = new JMenuBar();

	JMenu menuDanhMuc = new JMenu("Danh mục");
	JMenuItem menuItemQLNV = new JMenuItem("Quản lý nhân viên");
	JMenuItem menuItemCSLV = new JMenuItem("Cơ sở làm việc");
	JMenuItem menuItemCV = new JMenuItem("Chức vụ");

	JMenu menuTimKiem = new JMenu("Tìm kiếm");
	JMenuItem menuItemTimNV = new JMenuItem("Nhân viên");

	JMenu menuTienIch = new JMenu("Tiện ích");
	JMenuItem menuItemDangNhap = new JMenuItem("Đăng nhập");
	JMenuItem menuItemCapTK = new JMenuItem("Cấp tài khoản");
	JMenuItem menuItemDiemDanh = new JMenuItem("Điểm danh");
	JMenuItem menuItemDangXuat = new JMenuItem("Đăng xuất");
	JMenuItem menuItemThoat = new JMenuItem("Thoát");

	public App() {
		super("Quản lý nhân sự");

		createGUI();

		toggleFeatures();

		assignListeners();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void createGUI() {
		setLayout(new BorderLayout());

		menuDanhMuc.add(menuItemQLNV);
		menuDanhMuc.add(menuItemCSLV);
		menuDanhMuc.add(menuItemCV);

		menuTimKiem.add(menuItemTimNV);

		menuTienIch.add(menuItemDiemDanh);
		menuTienIch.add(menuItemDangNhap);
		menuTienIch.add(menuItemCapTK);
		menuTienIch.add(menuItemDangXuat);
		menuTienIch.add(menuItemThoat);

		menubar.add(menuDanhMuc);
		menubar.add(menuTimKiem);
		menubar.add(menuTienIch);

		this.add(menubar, BorderLayout.NORTH);
		this.setSize(800, 500);
		Util.centerScreen(this);
	}

	public void assignListeners() {
		menuItemDiemDanh.addActionListener(e -> {

		});

		menuItemDangNhap.addActionListener(e -> {
			new FormDangNhap(this);
		});

		menuItemDangXuat.addActionListener(e -> {
			AuthorizedUser.getInstance().setNhanVien(null);
			toggleFeatures();
		});

		menuItemCapTK.addActionListener(e -> {

		});

		menuItemThoat.addActionListener(e -> {
			System.exit(ABORT);
		});
	}

	public void toggleFeatures() {
		if (AuthorizedUser.getInstance().getNhanVien() == null) {
			menuDanhMuc.setEnabled(false);
			menuTimKiem.setEnabled(false);
			menuItemDiemDanh.setEnabled(false);
			menuItemDangXuat.setEnabled(false);
			menuItemCapTK.setEnabled(false);

			menuItemDangNhap.setEnabled(true);
		} else {
			menuDanhMuc.setEnabled(true);
			menuTimKiem.setEnabled(true);
			menuItemDiemDanh.setEnabled(true);
			menuItemDangXuat.setEnabled(true);
			menuItemCapTK.setEnabled(true);

			menuItemDangNhap.setEnabled(false);
		}
	}
}