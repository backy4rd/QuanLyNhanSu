package quanlynhansu.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import quanlynhansu.controllers.TaiKhoanController;
import quanlynhansu.models.NhanVien;
import quanlynhansu.providers.AuthorizedUser;
import quanlynhansu.providers.ControllerException;

@SuppressWarnings("serial")
public class FormDangNhap extends JFrame {
	TaiKhoanController taiKhoanController = new TaiKhoanController();
	private App app;

	private JButton btnDangNhap = new JButton("Đăng nhập");
	private JButton btnDong = new JButton("Đóng");
	private JTextField txtUsername = new JTextField(20);
	private JPasswordField txtPassword = new JPasswordField(20);

	public FormDangNhap(App app) {
		super("Đăng nhập");
		this.app = app;

		createGUI();

		assignListeners();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void createGUI() {
		this.setLayout(new BorderLayout());

		JPanel midPanel = new JPanel(new GridBagLayout());
		JPanel bottomPanel = new JPanel(new GridBagLayout());

		JLabel labelUsername = new JLabel("Tên tài khoản: ");
		JLabel labelPassword = new JLabel("Mật khẩu: ");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);

		midPanel.add(labelUsername, gbc);
		gbc.gridx++;
		midPanel.add(txtUsername, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		midPanel.add(labelPassword, gbc);
		gbc.gridx++;
		midPanel.add(txtPassword, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(4, 16, 4, 16);
		bottomPanel.add(btnDangNhap, gbc);
		gbc.gridx++;
		bottomPanel.add(btnDong, gbc);

		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(350, 150);
	}

	public void assignListeners() {
		ActionListener handleDangNhap = e -> {
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());

			try {
				NhanVien nv = taiKhoanController.dangNhap(username, password);
				AuthorizedUser.getInstance().setNhanVien(nv);

				JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
				app.toggleFeatures();
				this.dispose();
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage());
			}
		};

		btnDangNhap.addActionListener(handleDangNhap);
		txtPassword.addActionListener(handleDangNhap);
		txtUsername.addActionListener(handleDangNhap);

		btnDong.addActionListener(e -> {
			this.dispose();
		});
	}
}
