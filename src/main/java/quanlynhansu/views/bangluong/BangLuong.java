package quanlynhansu.views.bangluong;

import java.awt.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;

import quanlynhansu.controllers.NhanVienController;
import quanlynhansu.models.DiemDanh;
import quanlynhansu.models.NhanVien;
import quanlynhansu.models.Thuong;
import quanlynhansu.providers.AuthorizedUser;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

@SuppressWarnings("serial")
public class BangLuong extends JFrame {
	private NhanVienController nvc = new NhanVienController();
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();

	private NgayLamViecTableModel ngayLamViecTableModel;
	private ThuongTableModel thuongTableModel;

	private JTable tableNgayLamViec;
	private JTable tableThuong;
	private JComboBox<Integer> cbNam;
	private JComboBox<Integer> cbThang;
	private JTextField txtSoNgayLV;
	private JTextField txtTongLuong;
	private JTextField txtTongThuong;
	private JTextField txtTongCong;
	private JButton btnDong;

	public BangLuong() {
		super("Bảng lương");

		initializeComponents();
		createGUI();
		assignListeners();
		displayData((int) cbThang.getSelectedItem(), (int) cbNam.getSelectedItem());

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeComponents() {
		ngayLamViecTableModel = new NgayLamViecTableModel();
		thuongTableModel = new ThuongTableModel();

		txtSoNgayLV = new JTextField();
		txtSoNgayLV.setEditable(false);

		txtTongLuong = new JTextField();
		txtTongLuong.setEditable(false);

		txtTongThuong = new JTextField();
		txtTongThuong.setEditable(false);

		txtTongCong = new JTextField();
		txtTongCong.setEditable(false);

		btnDong = new JButton("Đóng");
		btnDong.setIcon(new ImageIcon(this.getClass().getResource("../../images/close.png")));

		LocalDate now = LocalDate.now();
		int currentYear = now.getYear();

		cbNam = new JComboBox<>(new Integer[] { currentYear, currentYear - 1, currentYear - 2 });
		cbNam.setSelectedIndex(0);

		cbThang = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		cbThang.setSelectedItem(now.getMonthValue());

		tableNgayLamViec = new JTable(ngayLamViecTableModel);
		tableThuong = new JTable(thuongTableModel);
	}

	private void createGUI() {
		JPanel topPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		topPanel.setLayout(new GridBagLayout());
		midPanel.setLayout(new GridBagLayout());
		bottomPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		// top panel
		JLabel title = new JLabel("Bảng lương", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(12, 0, 12, 0);
		topPanel.add(title, gbc);

		// col 1
		JLabel labelThang = new JLabel("Tháng: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(4, 4, 4, 4);
		midPanel.add(labelThang, gbc);
		JLabel labelNam = new JLabel("Năm: ");
		gbc.gridy++;
		midPanel.add(labelNam, gbc);
		JLabel labelSoNgayLV = new JLabel("Số ngày làm việc: ");
		gbc.gridy++;
		midPanel.add(labelSoNgayLV, gbc);

		// col 2
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		midPanel.add(cbThang, gbc);
		gbc.gridy++;
		midPanel.add(cbNam, gbc);
		gbc.gridy++;
		midPanel.add(txtSoNgayLV, gbc);

		// col 3
		JLabel labelTongLuong = new JLabel("Tổng lương: ");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.insets = new Insets(4, 4, 4, 4);
		midPanel.add(labelTongLuong, gbc);
		JLabel labelTongThuong = new JLabel("Tổng thưởng: ");
		gbc.gridy++;
		midPanel.add(labelTongThuong, gbc);
		JLabel labelTongCong = new JLabel("Tổng cộng: ");
		gbc.gridy++;
		midPanel.add(labelTongCong, gbc);

		// col 4
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		midPanel.add(txtTongLuong, gbc);
		gbc.gridy++;
		midPanel.add(txtTongThuong, gbc);
		gbc.gridy++;
		midPanel.add(txtTongCong, gbc);

		// table ngay lam viec
		tableNgayLamViec.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sTable1 = new JScrollPane(tableNgayLamViec, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		midPanel.add(sTable1, gbc);

		// table thuong
		tableThuong.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sTable2 = new JScrollPane(tableThuong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridy = 3;
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		midPanel.add(sTable2, gbc);

		// bottom panel
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(4, 0, 12, 0);
		bottomPanel.add(btnDong, gbc);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(800, 650);
		Util.centerScreen(this);
	}

	private void assignListeners() {
		cbNam.addActionListener(e -> {
			displayData((int) cbThang.getSelectedItem(), (int) cbNam.getSelectedItem());
		});

		cbThang.addActionListener(e -> {
			displayData((int) cbThang.getSelectedItem(), (int) cbNam.getSelectedItem());
		});

		btnDong.addActionListener(e -> {
			this.dispose();
		});
	}

	private void displayData(int month, int year) {
		try {
			NhanVien nv = AuthorizedUser.getInstance().getNhanVien();
			ArrayList<DiemDanh> diemDanhs = nvc.getDiemDanhtrongThang(nv.getMaNV(), month, year);
			ArrayList<Thuong> thuongs = nvc.getThuongTrongThang(nv.getMaNV(), month, year);
			ngayLamViecTableModel.updateData(diemDanhs, month, year);
			thuongTableModel.updateData(thuongs);

			int soNgayLV = ngayLamViecTableModel.soNgayLV();
			double tongThuong = thuongTableModel.tongThuong();
			double tongLuong = nv.tinhLuong(soNgayLV);

			txtSoNgayLV.setText(String.valueOf(soNgayLV));
			txtTongLuong.setText(formatter.format(tongLuong));
			txtTongThuong.setText(formatter.format(tongThuong));
			txtTongCong.setText(formatter.format(tongLuong + tongThuong));
		} catch (ControllerException err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

	}
}
