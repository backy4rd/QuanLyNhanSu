package quanlynhansu.views.dangnhap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import quanlynhansu.controllers.TaiKhoanController;
import quanlynhansu.models.NhanVien;
import quanlynhansu.providers.AuthorizedUser;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;
import quanlynhansu.views.App;

@SuppressWarnings("serial")
public class DangNhap extends JFrame {
	TaiKhoanController taiKhoanController = new TaiKhoanController();
	private App app;

	private JButton btnDangNhap;
	private JButton btnDong;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	public DangNhap(App app) {
		super("Đăng nhập");
		this.app = app;
		
		initializeComponents();

		createGUI();

		assignListeners();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

		// admin admin điền mặc định
		this.txtUsername.setText("admin");
		this.txtPassword.setText("admin");
	}

	private void initializeComponents() {
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setIcon(new ImageIcon(this.getClass().getResource("../../images/login.png")));
		btnDong = new JButton("Đóng");
		btnDong.setIcon(new ImageIcon(this.getClass().getResource("../../images/close.png")));
		txtUsername = new JTextField(20);
		txtPassword = new JPasswordField(20);

	}

	public void createGUI() {
		this.setLayout(new BorderLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel midPanel = new JPanel(new GridBagLayout());
		JPanel bottomPanel = new JPanel(new GridBagLayout());

		JLabel title = new JLabel("Đăng nhập", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(12, 0, 12, 0);
		topPanel.add(title, gbc);

		JLabel labelUsername = new JLabel("Tên tài khoản: ");
		JLabel labelPassword = new JLabel("Mật khẩu: ");

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
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
		gbc.weightx = 1;
		gbc.insets = new Insets(12, 0, 12, 0);
		bottomPanel.add(btnDangNhap, gbc);
		gbc.gridx++;
		bottomPanel.add(btnDong, gbc);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(350, 200);
		Util.centerScreen(this);
	}

	public void assignListeners() {
		ActionListener handleDangNhap = e -> {
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());

			try {
				NhanVien nv = taiKhoanController.dangNhap(username, password);
				AuthorizedUser.getInstance().setNhanVien(nv);

				// JOptionPane.showMessageDialog(null, "Đăng nhập thành công!", "Thông báo",
				// JOptionPane.INFORMATION_MESSAGE);
				app.toggleFeatures();
				this.dispose();
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
