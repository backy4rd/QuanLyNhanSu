package quanlynhansu.views.timkiemnhanvien;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import quanlynhansu.controllers.CoSoLamViecController;
import quanlynhansu.controllers.NhanVienController;
import quanlynhansu.models.CoSoLamViec;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

@SuppressWarnings("serial")
public class TimKiemNhanVien extends JFrame {
	private NhanVienController nvc = new NhanVienController();
	private CoSoLamViecController cslvc = new CoSoLamViecController();

	private NhanVienTableModel nhanVienTableModel;
	private JTable tableNhanVien;
	private JTextField txtTuKhoa;
	private JComboBox<CoSoLamViec> cbCSLV;
	private JButton btnTimKiem;
	private JButton btnDong;

	public TimKiemNhanVien() {
		super("Tìm kiếm nhân viên");

		initializeComponents();
		createGUI();
		assignListeners();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeComponents() {
		try {
			nhanVienTableModel = new NhanVienTableModel(nvc.getNhanViens());
			tableNhanVien = new JTable(nhanVienTableModel);
			
			ArrayList<CoSoLamViec> cslv = cslvc.getCoSoLamViecs();
			cslv.add(0, new CoSoLamViec(null, "Tất cả", null));

			cbCSLV = new JComboBox<>(cslv.toArray(new CoSoLamViec[0]));
			txtTuKhoa = new JTextField();

			btnTimKiem = new JButton("Tìm kiếm");
			btnTimKiem.setIcon(new ImageIcon(this.getClass().getResource("../../images/search.png")));
			btnDong = new JButton("Đóng");
			btnDong.setIcon(new ImageIcon(this.getClass().getResource("../../images/close.png")));
		} catch (ControllerException err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
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
		JLabel title = new JLabel("Tìm kiếm nhân viên", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(12, 0, 12, 0);
		topPanel.add(title, gbc);

		// mid panel
		// col 1
		gbc.insets = new Insets(4, 4, 4, 4);

		JLabel labelTuKhoa = new JLabel("Từ khóa: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		midPanel.add(labelTuKhoa, gbc);

		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		midPanel.add(txtTuKhoa, gbc);

		JLabel labelCSLV = new JLabel("Cơ sở làm việc: ");
		gbc.gridx++;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.REMAINDER;
		midPanel.add(labelCSLV, gbc);

		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		midPanel.add(cbCSLV, gbc);

		// table
		tableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableNhanVien.setRowHeight(24);
		tableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(250);
		JScrollPane sTable = new JScrollPane(tableNhanVien, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 4;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		midPanel.add(sTable, gbc);

		// bottom panel
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(4, 8, 12, 8);
		bottomPanel.add(btnTimKiem, gbc);
		gbc.gridx++;
		bottomPanel.add(btnDong, gbc);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(850, 600);
		Util.centerScreen(this);
	}

	private void assignListeners() {
		btnTimKiem.addActionListener(e -> {
			nhanVienTableModel.filter(txtTuKhoa.getText(), (CoSoLamViec)cbCSLV.getSelectedItem());
		});
		
		btnDong.addActionListener(e -> {
			this.dispose();
		});
	}
}
