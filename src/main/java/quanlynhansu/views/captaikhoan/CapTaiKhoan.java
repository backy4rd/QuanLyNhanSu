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

public class CapTaiKhoan extends JFrame {
	private NhanVienController nvc = new NhanVienController();
	private TaiKhoanController tkc = new TaiKhoanController();

	private JComboBox<NhanVien> cbNV;
	private JTextField tk;
	private JPasswordField mk;
	private JPasswordField nhapLai;
	private JButton capTKBtn;
	private JButton dongBtn;

	public CapTaiKhoan() {
		super("Cấp tài khoản");

		GUI();
		assignListeners();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void GUI() {
		try {
			int capBac = AuthorizedUser.getInstance().getNhanVien().getChucVu().getCapBac();
			cbNV = new JComboBox<>(nvc.getNhanViensCapBacNhoHon(capBac).toArray(new NhanVien[0]));
		} catch (ControllerException err) {
			JOptionPane.showMessageDialog(null, err.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}


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

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		midPanel.add(cbNV, gbc);
		gbc.gridy++;
		tk = new JTextField();
		midPanel.add(tk, gbc);
		gbc.gridy++;
		mk = new JPasswordField();
		midPanel.add(mk, gbc);
		gbc.gridy++;
		nhapLai = new JPasswordField();
		midPanel.add(nhapLai, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = new Insets(12, 0, 12, 0);
		gbc.fill = GridBagConstraints.REMAINDER;
		capTKBtn = new JButton("Cấp tài khoản");
		capTKBtn.setIcon(new ImageIcon(this.getClass().getResource("../../images/add-user.png")));
		bottomPanel.add(capTKBtn, gbc);
		gbc.gridx++;
		dongBtn = new JButton("Đóng");
		dongBtn.setIcon(new ImageIcon(this.getClass().getResource("../../images/close.png")));
		bottomPanel.add(dongBtn, gbc);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(450, 250);
		Util.centerScreen(this);
	}

	private void assignListeners() {
		capTKBtn.addActionListener(e -> {
			try {
				String username = tk.getText();
				String password = String.valueOf(mk.getPassword());
				String retype = String.valueOf(nhapLai.getPassword());
				NhanVien nv = (NhanVien) cbNV.getSelectedItem();

				if (nv == null) {
					JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

		dongBtn.addActionListener(e -> {
			this.dispose();
		});
	}

}
