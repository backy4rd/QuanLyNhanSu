package quanlynhansu.views.qlchucvu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import quanlynhansu.controllers.ChucVuController;
import quanlynhansu.models.ChucVu;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

@SuppressWarnings("serial")
public class QuanLyChucVu extends JFrame {
	private ChucVuController cvc = new ChucVuController();

	private ChucVuTableModel chucVuTableModel;
	private JTable tableChucVu;
	private JTextField txtTenCV;
	private JTextField txtCapBac;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnKhongLuu;
	private JButton btnDong;

	private boolean isAdd;

	public QuanLyChucVu() {
		super("Quản lý chức vụ");

		initializeComponents();
		createGUI();
		assignListeners();

		setEditable(false);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeComponents() {
		try {
			chucVuTableModel = new ChucVuTableModel(cvc.getChucVus());
			tableChucVu = new JTable(chucVuTableModel);

			btnThem = new JButton("Thêm");
			btnThem.setIcon(new ImageIcon(this.getClass().getResource("../../images/add.png")));
			btnSua = new JButton("Sửa");
			btnSua.setIcon(new ImageIcon(this.getClass().getResource("../../images/update.png")));
			btnXoa = new JButton("Xóa");
			btnXoa.setIcon(new ImageIcon(this.getClass().getResource("../../images/remove.png")));
			btnLuu = new JButton("Lưu");
			btnLuu.setIcon(new ImageIcon(this.getClass().getResource("../../images/save.png")));
			btnKhongLuu = new JButton("Không Lưu");
			btnKhongLuu.setIcon(new ImageIcon(this.getClass().getResource("../../images/discard.png")));
			btnDong = new JButton("Đóng");
			btnDong.setIcon(new ImageIcon(this.getClass().getResource("../../images/close.png")));

			txtTenCV = new JTextField();
			txtCapBac = new JTextField();
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
		JLabel title = new JLabel("Quản lý chức vụ", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(12, 0, 12, 0);
		topPanel.add(title, gbc);

		// mid panel
		// col 1
		gbc.insets = new Insets(4, 4, 4, 4);
		JLabel labelTenCS = new JLabel("Tên chức vụ: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		midPanel.add(labelTenCS, gbc);
		JLabel labelDiaChi = new JLabel("Cấp bậc: ");
		gbc.gridy++;
		midPanel.add(labelDiaChi, gbc);

		// col 2
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		midPanel.add(txtTenCV, gbc);
		gbc.gridy++;
		midPanel.add(txtCapBac, gbc);

		// table
		tableChucVu.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableChucVu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableChucVu.setRowHeight(24);
		JScrollPane sTable = new JScrollPane(tableChucVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 8, 12, 8);
		bottomPanel.add(btnThem, gbc);
		gbc.gridx++;
		bottomPanel.add(btnSua, gbc);
		gbc.gridx++;
		bottomPanel.add(btnXoa, gbc);
		gbc.gridx++;
		bottomPanel.add(btnLuu, gbc);
		gbc.gridy++;
		gbc.gridx--;
		bottomPanel.add(btnKhongLuu, gbc);
		gbc.gridx++;
		bottomPanel.add(btnDong, gbc);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(500, 600);
		Util.centerScreen(this);
	}

	private void assignListeners() {
		tableChucVu.getSelectionModel().addListSelectionListener(e -> {
			displaySelectedRow();
		});

		btnThem.addActionListener(e -> {
			isAdd = true;
			txtTenCV.setText(null);
			txtCapBac.setText(null);
			setEditable(true);
			txtTenCV.requestFocus();
		});

		btnSua.addActionListener(e -> {
			if (tableChucVu.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn cơ sở để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			isAdd = false;
			setEditable(true);
			txtTenCV.requestFocus();
		});

		btnXoa.addActionListener(e -> {
			String maCV = chucVuTableModel.getRow(tableChucVu.getSelectedRow()).getMaCV();

			int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa chức vụ này không?",
					"Cảnh báo", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.NO_OPTION)
				return;

			try {
				cvc.deleteChucVu(maCV);
				chucVuTableModel.removeRow(tableChucVu.getSelectedRow());
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});

		btnLuu.addActionListener(e -> {
			ChucVu chucVu = null;

			try {
				chucVu = createChucVuModel();
			} catch (NumberFormatException err) {
				JOptionPane.showMessageDialog(null, "Cấp bậc không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				if (isAdd) {
					chucVu = cvc.createChucVu(chucVu);
					chucVuTableModel.addRow(chucVu);
				} else {
					String maCV = chucVuTableModel.getRow(tableChucVu.getSelectedRow()).getMaCV();
					chucVu = cvc.updateChucVu(maCV, chucVu);
					chucVuTableModel.updateRow(tableChucVu.getSelectedRow(), chucVu);
				}
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

			setEditable(false);
			displaySelectedRow();
		});

		btnKhongLuu.addActionListener(e -> {
			setEditable(false);
			displaySelectedRow();
		});

		btnDong.addActionListener(e -> {
			this.dispose();
		});
	}

	private void setEditable(boolean flag) {
		btnThem.setEnabled(!flag);
		btnSua.setEnabled(!flag);
		btnXoa.setEnabled(!flag);

		btnLuu.setEnabled(flag);
		btnKhongLuu.setEnabled(flag);

		txtTenCV.setEditable(flag);
		txtCapBac.setEditable(flag);

		tableChucVu.setEnabled(!flag);
	}

	private void displaySelectedRow() {
		if (tableChucVu.getRowCount() == 0)
			return;
		if (tableChucVu.getSelectedRow() == -1)
			return;

		txtTenCV.setText(chucVuTableModel.getRow(tableChucVu.getSelectedRow()).getTenCV());
		txtCapBac.setText(String.valueOf(chucVuTableModel.getRow(tableChucVu.getSelectedRow()).getCapBac()));
	}

	private ChucVu createChucVuModel() throws NumberFormatException {
		return new ChucVu(null, txtTenCV.getText(), Integer.valueOf(txtCapBac.getText()));
	}

}
