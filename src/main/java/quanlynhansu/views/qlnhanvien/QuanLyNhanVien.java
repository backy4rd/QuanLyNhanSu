package quanlynhansu.views.qlnhanvien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import quanlynhansu.controllers.ChucVuController;
import quanlynhansu.controllers.CoSoLamViecController;
import quanlynhansu.controllers.NhanVienController;
import quanlynhansu.models.ChucVu;
import quanlynhansu.models.CoSoLamViec;
import quanlynhansu.models.NhanVien;
import quanlynhansu.providers.AuthorizedUser;
import quanlynhansu.providers.ControllerException;
import quanlynhansu.providers.Util;

public class QuanLyNhanVien extends JFrame implements ActionListener {
	private NhanVienController NVC = new NhanVienController();
	private CoSoLamViecController CSLVC = new CoSoLamViecController();
	private ChucVuController CVC = new ChucVuController();
	private DecimalFormat decimalFormat = new DecimalFormat("#");

	private JButton btnThem, btnSua, btnXoa, btnLuu, btnKhongLuu, btnDong;
	private NhanVienTableModel dtm;
	private ArrayList<NhanVien> lst;
	private int curPos = 0;// vị trí hiện tại của dòng trên lưới
	private JTable table;
	private JTextField TenNV;
	private JComboBox<String> cbgt;
	private JTextField phone;
	private JTextField birth;
	private JTextField dc;
	private JTextField luong;
	private JComboBox<CoSoLamViec> cbCSLV;
	private JComboBox<ChucVu> cbCV;

	private boolean them;

	public QuanLyNhanVien() {
		super("Quản lý nhân viên");
		setVisible(true);
		setSize(800, 550);
		Util.centerScreen(this);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(4, 4, 4, 4);

		add(sourcePane(), gbc);
		gbc.gridy++;
		add(dataPane(), gbc);

		gbc.gridy = 0;
		gbc.gridx++;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weighty = 1;
		gbc.weightx = 0;
		add(actionPane(), gbc);

		setChinhSua(false);
	}

	public JPanel sourcePane() {
		JPanel pSource = new JPanel();

		pSource.setLayout(new GridBagLayout());
		GridBagConstraints gbc_s = new GridBagConstraints();
		gbc_s.gridx = 0;
		gbc_s.gridy = 0;
		gbc_s.ipady = 1;
		gbc_s.insets = new Insets(2, 0, 2, 0);
		gbc_s.anchor = GridBagConstraints.WEST;

		pSource.add(new JLabel("Tên nhân viên: "), gbc_s);
		gbc_s.gridy++;
		pSource.add(new JLabel("Giới tính: "), gbc_s);
		gbc_s.gridy++;
		pSource.add(new JLabel("Điện thoại: "), gbc_s);
		gbc_s.gridy++;
		pSource.add(new JLabel("Ngày sinh: "), gbc_s);
		gbc_s.gridy++;
		pSource.add(new JLabel("Địa chỉ: "), gbc_s);
		gbc_s.gridy++;
		pSource.add(new JLabel("Lương: "), gbc_s);
		gbc_s.gridy++;
		pSource.add(new JLabel("Chức vụ: "), gbc_s);
		gbc_s.gridy++;
		pSource.add(new JLabel("Cơ sở làm việc: "), gbc_s);

		gbc_s.gridx++;
		gbc_s.gridy = 0;
		gbc_s.weightx = 1;
		gbc_s.fill = GridBagConstraints.HORIZONTAL;

		pSource.add((TenNV = new JTextField(10)), gbc_s);
		gbc_s.gridy++;
		pSource.add((cbgt = new JComboBox<String>(new String[] { "Nam", "Nữ" })), gbc_s);
		gbc_s.gridy++;
		pSource.add((phone = new JTextField(10)), gbc_s);
		gbc_s.gridy++;
		pSource.add((birth = new JTextField(10)), gbc_s);
		gbc_s.gridy++;
		pSource.add((dc = new JTextField(10)), gbc_s);
		gbc_s.gridy++;
		pSource.add((luong = new JTextField(10)), gbc_s);
		try {
			gbc_s.gridy++;

			ArrayList<ChucVu> cvs = CVC
					.getChucVusCapBacNhoHon(AuthorizedUser.getInstance().getNhanVien().getChucVu().getCapBac());
			pSource.add((cbCV = new JComboBox<>(cvs.toArray(new ChucVu[0]))), gbc_s);
			gbc_s.gridy++;
			pSource.add((cbCSLV = new JComboBox<>(CSLVC.getCoSoLamViecs().toArray(new CoSoLamViec[0]))), gbc_s);
		} catch (ControllerException e) {
			e.printStackTrace();
		}

		// Set lại màu khi các combobox bị disable
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer() {
			@Override
			public void paint(Graphics g) {
				setForeground(Color.BLACK);
				super.paint(g);
			}
		};
		cbgt.setRenderer(dlcr);
		cbCSLV.setRenderer(dlcr);
		cbCV.setRenderer(dlcr);

		return pSource;
	}

	public JPanel actionPane() {
		JPanel pAction = new JPanel();
		pAction.setLayout(new GridBagLayout());
		GridBagConstraints gbc_a = new GridBagConstraints();
		gbc_a.gridx = 0;
		gbc_a.gridy = 0;
		gbc_a.gridwidth = GridBagConstraints.REMAINDER;
		gbc_a.fill = GridBagConstraints.HORIZONTAL;
		gbc_a.weightx = 1;
		gbc_a.insets = new Insets(4, 4, 4, 4);

		pAction.add((btnThem = new JButton("Thêm")), gbc_a);
		gbc_a.gridy++;
		pAction.add((btnSua = new JButton("Sửa")), gbc_a);
		gbc_a.gridy++;
		pAction.add((btnXoa = new JButton("Xóa")), gbc_a);
		gbc_a.gridy++;
		pAction.add((btnLuu = new JButton("Lưu")), gbc_a);
		gbc_a.gridy++;
		pAction.add((btnKhongLuu = new JButton("Không lưu")), gbc_a);
		gbc_a.gridy++;
		gbc_a.weighty = 1;
		gbc_a.anchor = GridBagConstraints.SOUTH;
		pAction.add((btnDong = new JButton("Đóng")), gbc_a);

		btnThem.setIcon(new ImageIcon(this.getClass().getResource("../../images/add.png")));
		btnSua.setIcon(new ImageIcon(this.getClass().getResource("../../images/update.png")));
		btnXoa.setIcon(new ImageIcon(this.getClass().getResource("../../images/remove.png")));
		btnLuu.setIcon(new ImageIcon(this.getClass().getResource("../../images/save.png")));
		btnKhongLuu.setIcon(new ImageIcon(this.getClass().getResource("../../images/discard.png")));
		btnDong.setIcon(new ImageIcon(this.getClass().getResource("../../images/close.png")));
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnKhongLuu.addActionListener(this);
		btnDong.addActionListener(this);

		return pAction;
	}

	public JPanel dataPane() {
		JPanel pData = new JPanel();
		pData.setLayout(new GridBagLayout());
		GridBagConstraints gbc_d = new GridBagConstraints();
		gbc_d.anchor = GridBagConstraints.CENTER;
		gbc_d.gridx = 0;
		gbc_d.gridy = 0;
		gbc_d.weightx = 1.0;
		gbc_d.weighty = 1.0;
		gbc_d.fill = GridBagConstraints.BOTH;

		try {
			lst = NVC.getNhanViensCapBacNhoHon(AuthorizedUser.getInstance().getNhanVien().getChucVu().getCapBac());
			dtm = new NhanVienTableModel(lst);
		} catch (Exception e) {
			e.printStackTrace();
		}

		table = new JTable(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// cho chọn 1 dòng
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() != -1)
					setFieldData(dtm.getRow(table.getSelectedRow()));
			}
		});
		pData.add(new JScrollPane(table), gbc_d);
		table.setRowSelectionInterval(curPos, curPos);
		return pData;
	}

	// Gán dữ liệu cho các textfield
	private void setFieldData(NhanVien nv) {
		cbgt.setSelectedIndex(nv.isNu() ? 1 : 0);
		TenNV.setText(nv.getTenNV());
		phone.setText(nv.getSdt());
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		birth.setText(fmt.format(nv.getNgaySinh()));
		dc.setText(nv.getDiaChi());
		luong.setText(decimalFormat.format(nv.getLuong()));

		for (int i = 0; i < cbCV.getItemCount(); i++) {
			if (cbCV.getItemAt(i).getMaCV().equals(nv.getMaCV())) {
				cbCV.setSelectedItem(cbCV.getItemAt(i));
				break;
			}
		}

		for (int i = 0; i < cbCSLV.getItemCount(); i++) {
			if (cbCSLV.getItemAt(i).getMaCS().equals(nv.getMaCS())) {
				cbCSLV.setSelectedItem(cbCSLV.getItemAt(i));
				break;
			}
		}
	}

	// Tạo model từ input
	private NhanVien getModelFromInput() throws NumberFormatException, ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		NhanVien nv = new NhanVien();
		nv.setTenNV(TenNV.getText());
		nv.setNu(cbgt.getSelectedIndex() == 1 ? true : false);
		nv.setDiaChi(dc.getText());
		nv.setNgaySinh(new Date(fmt.parse(birth.getText()).getTime()));
		nv.setSdt(phone.getText());
		nv.setLuong(Double.parseDouble(luong.getText()));
		nv.setMaCS(((CoSoLamViec) cbCSLV.getSelectedItem()).getMaCS());
		nv.setMaCV(((ChucVu) cbCV.getSelectedItem()).getMaCV());

		return nv;
	}

	private void setChinhSua(boolean isEnable) {
		btnThem.setEnabled(!isEnable);
		btnSua.setEnabled(!isEnable);
		btnXoa.setEnabled(!isEnable);

		btnLuu.setEnabled(isEnable);
		btnKhongLuu.setEnabled(isEnable);

		TenNV.setEditable(isEnable);
		dc.setEditable(isEnable);
		birth.setEditable(isEnable);
		phone.setEditable(isEnable);
		luong.setEditable(isEnable);
		cbgt.setEnabled(isEnable);
		cbCV.setEnabled(isEnable);
		cbCSLV.setEnabled(isEnable);

		table.setEnabled(!isEnable);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// khi nhấn nút thêm
		if (e.getSource() == btnThem) {
			them = true;
			TenNV.setText(null);
			dc.setText(null);
			phone.setText(null);
			birth.setText(null);
			luong.setText(null);
			cbgt.setSelectedIndex(0);
			cbCSLV.setSelectedIndex(0);
			cbCV.setSelectedIndex(0);
			setChinhSua(true);
			TenNV.requestFocus();
			return;
		}

		// khi nhấn nút sửa
		if (e.getSource() == btnSua) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			them = false;
			setChinhSua(true);
			TenNV.requestFocus();
			return;
		}

		// khi nhấn nút xóa
		if (e.getSource() == btnXoa) {
			if (table.getSelectedRow() == -1)
				return;
			String maNV = dtm.getRow(table.getSelectedRow()).getMaNV();

			int ds = JOptionPane.showConfirmDialog(null, "Bạn có muốn xa thải nhân viên này không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (ds == JOptionPane.NO_OPTION)
				return;

			try {
				NVC.deleteNhanVien(maNV);
				dtm.removeRow(table.getSelectedRow());
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
			return;
		}

		// khi nhấn nút lưu
		if (e.getSource() == btnLuu) {
			NhanVien nv = null;

			try {
				nv = getModelFromInput();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Lương không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ (dd/mm/yyyy)", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				if (them) {
					nv = NVC.createNhanVien(nv);
					dtm.addRow(nv);
				} else {
					String maNV = dtm.getRow(table.getSelectedRow()).getMaNV();
					nv = NVC.updateNhanVien(maNV, nv);
					dtm.updateRow(table.getSelectedRow(), nv);
				}
			} catch (ControllerException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

			setChinhSua(false);
			if (table.getSelectedRow() != -1) {
				setFieldData(dtm.getRow(table.getSelectedRow()));
			}
			return;
		}

		// khi nhấn nút không lưu
		if (e.getSource() == btnKhongLuu) {
			setChinhSua(false);

			if (table.getSelectedRow() != -1) {
				setFieldData(dtm.getRow(table.getSelectedRow()));
			}
			return;
		}

		// khi nhấn nút đóng
		if (e.getSource() == btnDong) {
			this.dispose();
			return;
		}
	}
}
