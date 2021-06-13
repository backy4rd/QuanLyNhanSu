package quanlynhansu.views.timkiemnhanvien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import quanlynhansu.controllers.CoSoLamViecController;
import quanlynhansu.controllers.NhanVienController;
import quanlynhansu.models.CoSoLamViec;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

public class TimKiemNhanVien extends JFrame {
	private NhanVienController nvc = new NhanVienController();
	private CoSoLamViecController cslvc = new CoSoLamViecController();

	private NhanVienTableModel nhanVienTableModel;
	private JTable table;
	private JTextField txtTuKhoa;
	private JComboBox<CoSoLamViec> cmbCSLV;
	private JButton timKiem;
	private JButton dong;

	public TimKiemNhanVien() {
		super("Tìm kiếm nhân viên");

		try {
			nhanVienTableModel = new NhanVienTableModel(nvc.getNhanViens());
			table = new JTable(nhanVienTableModel);

			ArrayList<CoSoLamViec> cslv = cslvc.getCoSoLamViecs();
			cslv.add(0, new CoSoLamViec(null, "Tất cả", null));

			cmbCSLV = new JComboBox<>(cslv.toArray(new CoSoLamViec[0]));
			txtTuKhoa = new JTextField();

			timKiem = new JButton("Tìm kiếm");
			timKiem.setIcon(new ImageIcon(getClass().getResource("../../images/search.png")));
			dong = new JButton("Đóng");
			dong.setIcon(new ImageIcon(getClass().getResource("../../images/close.png")));
		} catch (ControllerException err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(12, 0, 12, 0);
		JLabel title = new JLabel("Tìm kiếm nhân viên");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		topPanel.add(title, gbc);

		add(topPanel, BorderLayout.NORTH);
		add(inputPane(), BorderLayout.CENTER);
		add(actionPane(), BorderLayout.SOUTH);

		handleEvents();

		setSize(850, 600);
		Util.centerScreen(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private JPanel inputPane() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(4, 4, 4, 4);

		JLabel labelTuKhoa = new JLabel("Từ khóa: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(labelTuKhoa, gbc);

		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtTuKhoa, gbc);

		JLabel labelCSLV = new JLabel("Cơ sở làm việc: ");
		gbc.gridx++;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.REMAINDER;
		panel.add(labelCSLV, gbc);

		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(cmbCSLV, gbc);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(24);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);
		JScrollPane sTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 4;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(sTable, gbc);

		return panel;
	}

	private JPanel actionPane() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(4, 8, 12, 8);
		panel.add(timKiem, gbc);
		gbc.gridx++;
		panel.add(dong, gbc);

		return panel;
	}

	private void handleEvents() {
		timKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nhanVienTableModel.filter(txtTuKhoa.getText(), (CoSoLamViec) cmbCSLV.getSelectedItem());
			}
		});

		dong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TimKiemNhanVien.this.dispose();
			}
		});
	}
}
