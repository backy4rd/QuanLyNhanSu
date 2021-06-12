package quanlynhansu.views.khenthuong;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import quanlynhansu.controllers.NhanVienController;
import quanlynhansu.models.NhanVien;
import quanlynhansu.providers.AuthorizedUser;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

public class KhenThuong extends JFrame implements ActionListener {
	NhanVienController nvc = new NhanVienController();
	JComboBox<NhanVien> NVCmb;
	JTextField tienThuongTxt;
	JButton thuongButton;
	JButton dongButton;

	public KhenThuong() {
		super("Khen thưởng");

		try {
			NhanVien nv = AuthorizedUser.getInstance().getNhanVien();
			ArrayList<NhanVien> nvs = nvc.getNhanViensCapBacNhoHon(nv.getChucVu().getCapBac());

			NVCmb = new JComboBox<NhanVien>(nvs.toArray(new NhanVien[0]));

			tienThuongTxt = new JTextField();

			thuongButton = new JButton("Thưởng");
			Icon icon = new ImageIcon(this.getClass().getResource("../../images/salary.png"));
			thuongButton.setIcon(icon);

			dongButton = new JButton("Đóng");
			icon = new ImageIcon(this.getClass().getResource("../../images/salary.png"));
			dongButton.setIcon(icon);
		} catch (ControllerException e) {
		}

		JLabel title = new JLabel("Khen thưởng", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(12, 0, 12, 0)));

		this.add(title, BorderLayout.NORTH);
		this.add(mainPanel(), BorderLayout.CENTER);
		this.add(actionPanel(), BorderLayout.SOUTH);

		thuongButton.addActionListener(this);
		dongButton.addActionListener(this);

		this.setSize(330, 180);
		Util.centerScreen(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JPanel mainPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		p.add(new JLabel("Nhân Viên: "), gbc);
		gbc.gridy++;
		p.add(new JLabel("Tiền thưởng: "), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		p.add(NVCmb, gbc);
		gbc.gridy++;
		p.add(tienThuongTxt, gbc);

		return p;
	}

	private JPanel actionPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(8, 2, 12, 2);
		gbc.weightx = 1;
		p.add(thuongButton, gbc);
		gbc.gridx++;
		p.add(dongButton, gbc);

		return p;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == thuongButton) {
			try {
				String maNV = ((NhanVien) NVCmb.getSelectedItem()).getMaNV();
				double tienThuong = Double.parseDouble(tienThuongTxt.getText());
				nvc.khenThuong(maNV, tienThuong);
				JOptionPane.showMessageDialog(null, "Khen thưởng thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ControllerException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == dongButton) {
			this.dispose();

		}
	}
}
