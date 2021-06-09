package quanlynhansu.views.qlcosolamviec;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import quanlynhansu.controllers.CoSoLamViecController;
import quanlynhansu.models.CoSoLamViec;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

@SuppressWarnings("serial")
public class QuanLyCoSoLamViec extends JFrame {
	private CoSoLamViecController cslvc = new CoSoLamViecController();

	private CSLVTableModel cslvTableModel;
	private JTable tableCSLV;
	private JTextField txtTenCS;
	private JTextField txtDiaChi;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnKhongLuu;
	private JButton btnDong;

	private boolean isAdd;

	public QuanLyCoSoLamViec() {
		super("Quản lý cơ sở làm việc");

		initializeComponents();
		createGUI();
		assignListeners();

		setEditable(false);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeComponents() {
		try {
			cslvTableModel = new CSLVTableModel(cslvc.getCoSoLamViecs());
			tableCSLV = new JTable(cslvTableModel);

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

			txtTenCS = new JTextField();
			txtDiaChi = new JTextField();
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
		JLabel title = new JLabel("Quản lý cơ sở làm việc", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(12, 0, 12, 0);
		topPanel.add(title, gbc);

		// mid panel
		// col 1
		gbc.insets = new Insets(4, 4, 4, 4);
		JLabel labelTenCS = new JLabel("Tên cơ sở: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		midPanel.add(labelTenCS, gbc);
		JLabel labelDiaChi = new JLabel("Địa chỉ: ");
		gbc.gridy++;
		midPanel.add(labelDiaChi, gbc);

		// col 2
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		midPanel.add(txtTenCS, gbc);
		gbc.gridy++;
		midPanel.add(txtDiaChi, gbc);

		// table
		tableCSLV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sTable = new JScrollPane(tableCSLV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
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
		tableCSLV.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displaySelectedRow();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		// event khi nhấn nút thêm
		btnThem.addActionListener(e -> {
			isAdd = true;
			txtTenCS.setText(null);
			txtDiaChi.setText(null);
			setEditable(true);
			txtTenCS.requestFocus();
		});

		// event khi nhấn nút sửa
		btnSua.addActionListener(e -> {
			if (tableCSLV.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn cơ sở để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			isAdd = false;
			setEditable(true);
			txtTenCS.requestFocus();
		});

		// event khi nhấn nút xóa
		btnXoa.addActionListener(e -> {
			String maCS = cslvTableModel.getRow(tableCSLV.getSelectedRow()).getMaCS();

			int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa cơ sở này không?",
					"Cảnh báo", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.NO_OPTION)
				return;

			try {
				cslvc.deleteCoSoLamViec(maCS);
				cslvTableModel.removeRow(tableCSLV.getSelectedRow());
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});

		// event khi nhấn nút lưu
		btnLuu.addActionListener(e -> {
			CoSoLamViec cslv = createCSLVModel();

			try {
				if (isAdd) {
					cslv = cslvc.createCoSoLamViec(cslv);
					cslvTableModel.addRow(cslv);
				} else {
					String maCS = cslvTableModel.getRow(tableCSLV.getSelectedRow()).getMaCS();
					cslv = cslvc.updateCoSoLamViec(maCS, cslv);
					cslvTableModel.updateRow(tableCSLV.getSelectedRow(), cslv);
				}
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

			setEditable(false);
			displaySelectedRow();
		});

		// event khi nhấn nút không lưu
		btnKhongLuu.addActionListener(e -> {
			setEditable(false);
			displaySelectedRow();
		});

		// event khi nhấn nút đóng
		btnDong.addActionListener(e -> {
			this.dispose();
		});
	}

	// điểu khiển các nút và text box
	private void setEditable(boolean flag) {
		btnThem.setEnabled(!flag);
		btnSua.setEnabled(!flag);
		btnXoa.setEnabled(!flag);

		btnLuu.setEnabled(flag);
		btnKhongLuu.setEnabled(flag);

		txtTenCS.setEditable(flag);
		txtDiaChi.setEditable(flag);

		tableCSLV.setEnabled(!flag);
	}

	// hiển thị dòng đang được select lên các text box
	private void displaySelectedRow() {
		if (tableCSLV.getRowCount() == 0)
			return;
		if (tableCSLV.getSelectedRow() == -1)
			return;

		txtTenCS.setText(cslvTableModel.getRow(tableCSLV.getSelectedRow()).getTenCS());
		txtDiaChi.setText(cslvTableModel.getRow(tableCSLV.getSelectedRow()).getDiaChi());
	}

	// tạo CoSoLamViec từ các text box
	private CoSoLamViec createCSLVModel() {
		return new CoSoLamViec(null, txtTenCS.getText(), txtDiaChi.getText());
	}
}
