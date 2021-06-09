package quanlynhansu.views.captaikhoan;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import quanlynhansu.controllers.NhanVienController;
import quanlynhansu.controllers.TaiKhoanController;
import quanlynhansu.models.NhanVien;
import quanlynhansu.models.TaiKhoan;
import quanlynhansu.providers.AuthorizedUser;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

@SuppressWarnings("serial")
public class CapTaiKhoan extends JFrame {
	private NhanVienController nvc = new NhanVienController();
	private TaiKhoanController tkc = new TaiKhoanController();

	private JComboBox<NhanVien> cbNV;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtRetype;
	private JButton btnCapTK;
	private JButton btnDong;

	public CapTaiKhoan() {
		super("Cấp tài khoản");

		initializeComponents();
		createGUI();
		assignListeners();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeComponents() {
		try {
			int capBac = AuthorizedUser.getInstance().getNhanVien().getChucVu().getCapBac();
			cbNV = new JComboBox<>(nvc.getNhanViensCapBacNhoHon(capBac).toArray(new NhanVien[0]));

			txtUsername = new JTextField();
			txtPassword = new JPasswordField();
			txtRetype = new JPasswordField();
			btnCapTK = new JButton("Cấp tài khoản");
			btnCapTK.setIcon(new ImageIcon(this.getClass().getResource("../../images/add-user.png")));
			btnDong = new JButton("Đóng");
			btnDong.setIcon(new ImageIcon(this.getClass().getResource("../../images/close.png")));
		} catch (ControllerException err) {
			JOptionPane.showMessageDialog(null, err.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void createGUI() {
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel midPanel = new JPanel(new GridBagLayout());
		JPanel bottomPanel = new JPanel(new GridBagLayout());

		JLabel title = new JLabel("Cấp tài khoản", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(12, 0, 12, 0);
		topPanel.add(title, gbc);

		JLabel labelNV = new JLabel("Nhân viên: ");
		JLabel labelUsername = new JLabel("Tên tài khoản: ");
		JLabel labelPassword = new JLabel("Mật khẩu: ");
		JLabel labelRetype = new JLabel("Nhập lại MK: ");

		// col 1
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(4, 4, 4, 4);
		midPanel.add(labelNV, gbc);
		gbc.gridy++;
		midPanel.add(labelUsername, gbc);
		gbc.gridy++;
		midPanel.add(labelPassword, gbc);
		gbc.gridy++;
		midPanel.add(labelRetype, gbc);

		// col 2
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		midPanel.add(cbNV, gbc);
		gbc.gridy++;
		midPanel.add(txtUsername, gbc);
		gbc.gridy++;
		midPanel.add(txtPassword, gbc);
		gbc.gridy++;
		midPanel.add(txtRetype, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = new Insets(12, 0, 12, 0);
		gbc.fill = GridBagConstraints.REMAINDER;
		bottomPanel.add(btnCapTK, gbc);
		gbc.gridx++;
		bottomPanel.add(btnDong, gbc);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(450, 250);
		Util.centerScreen(this);
	}

	private void assignListeners() {
		btnCapTK.addActionListener(e -> {
			try {
				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				String retype = String.valueOf(txtRetype.getPassword());
				NhanVien nv = (NhanVien) cbNV.getSelectedItem();
				
				if (nv == null) {
					JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!password.equals(retype)) {
					JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không chính xác", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				tkc.capTaiKhoan(new TaiKhoan(username, password, nv.getMaNV()));
				JOptionPane.showMessageDialog(null, "Cấp tài khoản thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnDong.addActionListener(e -> {
			this.dispose();
		});
	}

}
