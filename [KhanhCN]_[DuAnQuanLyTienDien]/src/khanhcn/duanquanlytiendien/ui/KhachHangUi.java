package khanhcn.duanquanlytiendien.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import khanhcn.duanquanlytiendien.DAO.QuanLyTienDienDAO;
import khanhcn.duanquanlytiendien.entity.KhachHang;
import khanhcn.duanquanlytiendien.entity.QuanPhuong;

public class KhachHangUi extends JFrame {

	static QuanLyTienDienDAO QLTD = new QuanLyTienDienDAO();
	static ArrayList<QuanPhuong> dSPhuong = new ArrayList<QuanPhuong>();
	static ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
	static Connection conn = QLTD.getConnect("localhost", "khanhcn_ffse1701011", "khanhcn", "123456");

	DefaultTableModel dm;
	JTable tbl;
	JComboBox<Object> cbo;
	JScrollPane sc;

	// JComboBox QUẬN PHƯỜNG
	private JComboBox<QuanPhuong> cboQuan, cboPhuong;

	// CÁC JTextField KHÁCH HÀNG
	JTextField txtMaKH;
	JTextField txtTenKH;
	JTextField txtDiaChi;
	JTextField txtMaCT;
	JTextField txtPhuong;
	JTextField txtQuan;
	JTextField txtDienThoai;
	JTextField txtEmail;

	// BUTTON CRUD
	JButton btnThem = new JButton("      Thêm");
	JButton btnSua = new JButton("   Sửa");
	JButton btnXoa = new JButton("   Xóa");
	JButton btnReset = new JButton("        Reset");
	JButton btnThoat = new JButton("    Thoát");

	// DANH MỤC
	JButton khachHang = new JButton("         KHÁCH HÀNG");
	JButton bienLai = new JButton("  BIÊN LAI");
	JButton TKBC = new JButton("       TK BÁO CÁO");
	JButton bcdskh = new JButton("BÁO CÁO DSKH");
	JButton bcthtt = new JButton("BÁO CÁO THTT");

	BienLaiUi myBienLai = new BienLaiUi();
	BCDSKhachHangUi myBCDSKH = new BCDSKhachHangUi();
	BCTHTieuThu myBCTHTT = new BCTHTieuThu();

	// CÁC JPanel CHÍNH
	JPanel pnCenter = new JPanel();
	JPanel pnCenterCon = new JPanel();
	JPanel pnSouth = new JPanel();
	JPanel pnActions = new JPanel();

	// Kết nối database
	public void connectControls() {

		QLTD.getConnect("localhost", "khanhcn_ffse1701011", "khanhcn", "123456");

		/*
		 * if (QLTD != null) {
		 * 
		 * JOptionPane.showMessageDialog(null, "Kết nối MYSQL thành công! "); } else {
		 * 
		 * JOptionPane.showMessageDialog(null, "Kết nối MYSQL thất bại ! "); }
		 */
	}

	// HIỂN THỊ DANH SÁCH KH
	public void hienThiDS() {

		dsKH = QLTD.getDSKhachHang();
		dSPhuong = QLTD.getQuanKH();

		for (int i = 0; i < dsKH.size(); i++) {
			dm.addRow(new String[] { dsKH.get(i).getMaKH(), dsKH.get(i).getHoTen(), dsKH.get(i).getDiaChi(),
					dsKH.get(i).getMaCT(), QuanLyTienDienDAO.getNamePhuong(dsKH.get(i).getPhuong()),
					QuanLyTienDienDAO.getNameQuan(dsKH.get(i).getQuan()), dsKH.get(i).getEmail(),
					dsKH.get(i).getDienThoai() });

		}

	}

	// THÊM KHÁCH HÀNG
	public void addKhachHang() {

		String maKH = txtMaKH.getText();
		String tenKH = txtTenKH.getText();
		String diaChi = txtDiaChi.getText();
		String maCT = txtMaCT.getText();

		String quan = cboQuan.getSelectedItem().toString();
		int idquan = ((QuanPhuong) cboQuan.getSelectedItem()).getId();

		String phuong = cboPhuong.getSelectedItem().toString();
		int idphuong = ((QuanPhuong) cboPhuong.getSelectedItem()).getId();

		String dienThoai = txtDienThoai.getText();
		String email = txtEmail.getText();

		QLTD.add(new KhachHang(maKH, tenKH, diaChi, maCT, idphuong, idquan, dienThoai, email));

		dm.addRow(new String[] { maKH, tenKH, diaChi, maCT, phuong, quan, dienThoai, email });
	}

	// XÓA KHÁCH HÀNG
	public void deleteKhachHang(KhachHang kh) {

		String maKH = txtMaKH.getText();
		String tenKH = txtTenKH.getText();
		String diaChi = txtDiaChi.getText();
		String maCT = txtMaCT.getText();

		String quan = cboQuan.getSelectedItem().toString();
		int idquan = ((QuanPhuong) cboQuan.getSelectedItem()).getId();

		String phuong = cboPhuong.getSelectedItem().toString();
		int idphuong = ((QuanPhuong) cboPhuong.getSelectedItem()).getId();

		String dienThoai = txtDienThoai.getText();
		String email = txtEmail.getText();

		int[] rows = tbl.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			dm.removeRow(rows[i] - i);

		}
		QuanLyTienDienDAO.delete(new KhachHang(maKH, tenKH, diaChi, maCT, idquan, idphuong, dienThoai, email));
	}

	// RESRT THÔNG TIN KH
	public void reset() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtDiaChi.setText("");
		txtMaCT.setText("");
		cboQuan.setSelectedIndex(0);
		txtDienThoai.setText("");
		txtEmail.setText("");
	}

	// EDIT KHÁCH HÀNG
	public void editKhachHang() {
		String maKH = txtMaKH.getText();
		String tenKH = txtTenKH.getText();
		String diaChi = txtDiaChi.getText();
		String maCT = txtMaCT.getText();

		String quan = cboQuan.getSelectedItem().toString();
		int idquan = ((QuanPhuong) cboQuan.getSelectedItem()).getId();

		String phuong = cboPhuong.getSelectedItem().toString();
		int idphuong = ((QuanPhuong) cboPhuong.getSelectedItem()).getId();

		String dienThoai = txtDienThoai.getText();
		String email = txtEmail.getText();

		QuanLyTienDienDAO.edit(new KhachHang(maKH, tenKH, diaChi, maCT, idphuong, idquan, dienThoai, email));

		int row = tbl.getSelectedRow();
		tbl.setValueAt(maKH, row, 0);
		tbl.setValueAt(tenKH, row, 1);
		tbl.setValueAt(diaChi, row, 2);
		tbl.setValueAt(maCT, row, 3);
		tbl.setValueAt(quan, row, 4);
		tbl.setValueAt(phuong, row, 5);
		tbl.setValueAt(dienThoai, row, 6);
		tbl.setValueAt(email, row, 7);

	}

	// SỰ KIỆN MouseListener
	MouseListener tblUserClick = new MouseListener() {

		public void mouseClicked(MouseEvent e) {

			int row = tbl.getSelectedRow();
			String[] col = new String[10];
			col[0] = (String) tbl.getValueAt(row, 0);
			col[1] = (String) tbl.getValueAt(row, 1);
			col[2] = (String) tbl.getValueAt(row, 2);
			col[3] = (String) tbl.getValueAt(row, 3);
			col[4] = (String) tbl.getValueAt(row, 4);
			col[5] = (String) tbl.getValueAt(row, 5);
			col[6] = (String) tbl.getValueAt(row, 6);
			col[7] = (String) tbl.getValueAt(row, 7);

			txtMaKH.setText(col[0]);
			txtTenKH.setText(col[1]);
			txtDiaChi.setText(col[2]);
			txtMaCT.setText(col[3]);
			cboQuan.setSelectedItem(col[4]);
			cboPhuong.setSelectedItem(col[5]);
			txtDienThoai.setText(col[6]);
			txtEmail.setText(col[7]);
			
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
			//	btnThem.setEnabled(false);
			
		
			//////////////////////////

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};

	// GIAO DIỆN KHÁCH HÀNG
	public void addControls() throws SQLException {

		// Nạp container và add main panel
		Container con = getContentPane();
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		pnBorder.setLayout(layout);
		pnBorder.setBackground(Color.decode("#C0C0C0"));

		// Phần header
		JPanel pnNorth = new JPanel();
		pnNorth.setPreferredSize(new Dimension(50, 50));
		pnNorth.setBackground(Color.decode("#F79359"));
		pnBorder.add(pnNorth, BorderLayout.NORTH);
		JLabel lblTitle = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ TIỀN ĐIỆN");
		Font fontTitle = new Font("Courier", Font.ITALIC | Font.BOLD, 22);
		lblTitle.setForeground(Color.white);
		lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		lblTitle.setFont(fontTitle);
		pnNorth.add(lblTitle);

		// Phần bên trái
		JPanel pnWest = new JPanel();
		pnWest.setPreferredSize(new Dimension(200, 350));
		pnWest.setBackground(Color.BLUE);
		pnBorder.add(pnWest, BorderLayout.WEST);
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));

		JButton danhmuc = new JButton("      Danh mục ");
		danhmuc.setPreferredSize(new Dimension(100, 30));
		danhmuc.setMargin(new Insets(0, 2, 0, 0));
		ImageIcon iconDM = new ImageIcon(
				new ImageIcon("img/dmuc.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		JLabel DM = new JLabel(iconDM);
		danhmuc.add(DM);

		// JButton khachHang = new JButton(" KHÁCH HÀNG");
		// Font fontKH = new Font("Courier", Font.LAYOUT_LEFT_TO_RIGHT | Font.BOLD, 12);
		// khachHang.setForeground(Color.BLUE);
		// khachHang.setFont(fontKH);
		khachHang.setPreferredSize(new Dimension(201, 55));
		khachHang.setMargin(new Insets(0, 25, 0, 0));
		ImageIcon iconKh = new ImageIcon(
				new ImageIcon("img/khanhhang.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		JLabel kh = new JLabel(iconKh);
		khachHang.add(kh);

		// JButton bienLai = new JButton(" BIÊN LAI");
		bienLai.setPreferredSize(new Dimension(201, 55));
		bienLai.setMargin(new Insets(0, 25, 0, 0));
		ImageIcon iconBL = new ImageIcon(
				new ImageIcon("img/bienlai.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		JLabel bl = new JLabel(iconBL);
		bienLai.add(bl);

		// JButton TKBC = new JButton(" TK BÁO CÁO");
		TKBC.setPreferredSize(new Dimension(201, 55));
		TKBC.setMargin(new Insets(0, 25, 0, 0));
		ImageIcon iconBC = new ImageIcon(
				new ImageIcon("img/baocao.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		JLabel bc = new JLabel(iconBC);
		TKBC.add(bc);

		// JButton bcdskh = new JButton("BÁO CÁO DSKH");
		bcdskh.setPreferredSize(new Dimension(201, 40));
		bcdskh.setVisible(false);
		// JButton bcthtt = new JButton("BÁO CÁO THTT");
		bcthtt.setPreferredSize(new Dimension(201, 40));
		bcthtt.setVisible(false);

		JPanel tntLeft = new JPanel();
		tntLeft.add(danhmuc);
		tntLeft.add(khachHang);
		tntLeft.add(bienLai);
		tntLeft.add(TKBC);
		tntLeft.add(bcdskh);
		tntLeft.add(bcthtt);
		pnWest.add(tntLeft);

		// Phần trung tâm
		pnCenter = new JPanel();
		pnCenter.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
		pnCenter.setPreferredSize(new Dimension(350, 100));
		// pnCenter.setBackground(Color.YELLOW);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

		// JPanel pnCenterCon = new JPanel();
		pnCenterCon.setBackground(Color.GREEN);
		pnCenterCon.setLayout(new BoxLayout(pnCenterCon, BoxLayout.X_AXIS));

		// Bọc 4 o bên trái
		JPanel pnLeft = new JPanel();
		// pnCenterCon.setBackground(Color.GREEN);
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

		// Mã khách hàng
		JPanel pnMaKH = new JPanel();
		JLabel lblMaKH = new JLabel("Mã KH:  ");
		txtMaKH = new JTextField();
		txtMaKH.setPreferredSize(new Dimension(158, 28));
		pnMaKH.add(lblMaKH);
		pnMaKH.add(txtMaKH);
		pnLeft.add(pnMaKH);

		// Tên khách hàng
		JPanel pnTenKH = new JPanel();
		JLabel lblTenKH = new JLabel("Tên KH: ");
		txtTenKH = new JTextField();
		txtTenKH.setPreferredSize(new Dimension(158, 28));
		pnTenKH.add(lblTenKH);
		pnTenKH.add(txtTenKH);
		pnLeft.add(pnTenKH);

		// Địa chỉ khách hàng
		JPanel pnDiaChi = new JPanel();
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		txtDiaChi = new JTextField();
		txtDiaChi.setPreferredSize(new Dimension(158, 28));
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnLeft.add(pnDiaChi);

		// Mã công tơ
		JPanel pnMaCT = new JPanel();
		JLabel lblMaCT = new JLabel("Mã CT:  ");
		txtMaCT = new JTextField();
		txtMaCT.setPreferredSize(new Dimension(158, 28));
		pnMaCT.add(lblMaCT);
		pnMaCT.add(txtMaCT);
		pnLeft.add(pnMaCT);

		JPanel pnRight = new JPanel();
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		// Tạo JComboBox chứ các Quận
		JPanel pnQuan = new JPanel();
		JLabel lblQuan = new JLabel("Chọn Quận:        ");
		cboQuan = new JComboBox();
		ArrayList<QuanPhuong> tenQuan = new ArrayList<QuanPhuong>();
		tenQuan = QLTD.getQuanKH();
		for (QuanPhuong x : tenQuan) {
			cboQuan.addItem(x);
		}
		cboQuan.addItemListener(chonQuan);
		cboQuan.setPreferredSize(new Dimension(158, 28));

		pnQuan.add(lblQuan);
		pnQuan.add(cboQuan);
		pnRight.add(pnQuan);

		// Tạo JComboBox chứ các Phường
		JPanel pnPhuong = new JPanel();
		JLabel lblPhuong = new JLabel("Chọn Phường:   ");
		cboPhuong = new JComboBox();
		loadDataPhuong();
		cboPhuong.setPreferredSize(new Dimension(158, 28));
		pnPhuong.add(lblPhuong);
		pnPhuong.add(cboPhuong);
		pnRight.add(pnPhuong);

		// Điện thoại khách hàng
		JPanel pnDienThoai = new JPanel();
		JLabel lblDienThoai = new JLabel("Điện thoại:          ");
		txtDienThoai = new JTextField();
		txtDienThoai.setPreferredSize(new Dimension(158, 28));
		pnDienThoai.add(lblDienThoai);
		pnDienThoai.add(txtDienThoai);
		pnRight.add(pnDienThoai);

		// Email khách hàng
		JPanel pnEmail = new JPanel();
		JLabel lblEmail = new JLabel("Email KH:            ");
		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(158, 28));
		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);
		pnRight.add(pnEmail);

		pnCenterCon.add(pnLeft);
		pnCenterCon.add(pnRight);
		pnCenter.add(pnCenterCon);

		// phần button CRUD
		pnActions = new JPanel();
		// JButton btnThem = new JButton(" Thêm");
		btnThem.setPreferredSize(new Dimension(100, 50));
		btnThem.setMargin(new Insets(0, 10, 0, 0));
		ImageIcon iconThem = new ImageIcon(
				new ImageIcon("img/Them.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel add = new JLabel(iconThem);
		btnThem.add(add);
		pnCenter.add(btnThem);

		// JButton btnSua = new JButton(" Sửa");
		btnSua.setPreferredSize(new Dimension(110, 50));
		btnSua.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconEdit = new ImageIcon(
				new ImageIcon("img/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel edit = new JLabel(iconEdit);
		btnSua.add(edit);
		pnCenter.add(btnSua);

		// JButton btnXoa = new JButton(" Xóa");
		btnXoa.setPreferredSize(new Dimension(110, 50));
		btnXoa.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconDelete = new ImageIcon(
				new ImageIcon("img/xoa.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel delete = new JLabel(iconDelete);
		btnXoa.add(delete);
		pnCenter.add(btnXoa);

		// JButton btnDanhSach = new JButton("Reset");
		btnReset.setPreferredSize(new Dimension(110, 50));
		btnReset.setMargin(new Insets(0, 15, 0, 0));
		ImageIcon iconDSKH = new ImageIcon(
				new ImageIcon("img/reset.jpg").getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH));
		JLabel Reset = new JLabel(iconDSKH);
		btnReset.add(Reset);
		pnCenter.add(btnReset);

		// JButton btnThoat = new JButton(" Thoát");
		btnThoat.setPreferredSize(new Dimension(110, 50));
		btnThoat.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconBack = new ImageIcon(
				new ImageIcon("img/trove.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel back = new JLabel(iconBack);
		btnThoat.add(back);
		pnCenter.add(btnThoat);

		// add button
		pnActions.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		pnActions.add(btnThem);
		pnActions.add(btnSua);
		pnActions.add(btnXoa);
		pnActions.add(btnReset);
		pnActions.add(btnThoat);
		pnCenter.add(pnActions);
		pnBorder.add(pnCenter, BorderLayout.CENTER);

		// phần footer
		pnSouth = new JPanel();
		pnSouth.setBackground(Color.decode("#1E7EA4"));
		pnSouth.setPreferredSize(new Dimension(100, 280));
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		pnBorder.add(pnSouth, BorderLayout.SOUTH);

		// Tiêu đề bảng KH
		JLabel lblTitleKH = new JLabel("THÔNG TIN KHÁCH HÀNG");
		Font fontTitleKH = new Font("Courier", Font.ITALIC | Font.BOLD, 16);
		lblTitleKH.setForeground(Color.WHITE);
		lblTitleKH.setBorder(BorderFactory.createEmptyBorder(10, 380, 10, 0));
		lblTitleKH.setFont(fontTitleKH);
		pnSouth.add(lblTitleKH);

		// Table thông tin khách hàng
		dm = new DefaultTableModel();
		dm.addColumn("Mã KH");
		dm.addColumn("Tên KH");
		dm.addColumn("Địa chỉ");
		dm.addColumn("Mã CT ");
		dm.addColumn("Phường");
		dm.addColumn("Quận");
		dm.addColumn("Điện thoại");
		dm.addColumn("Email");

		tbl = new JTable(dm);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		sc = new JScrollPane(tbl);
		pnSouth.add(sc);

		pnCenter.add(myBienLai);
		myBienLai.setVisible(false);
		pnCenter.add(myBCDSKH);
		myBCDSKH.setVisible(false);
		pnCenter.add(myBCTHTT);
		myBCTHTT.setVisible(false);
		
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		con.add(pnBorder);

		/*
		 * if (conn != null) { System.out.println("ABC"); } else {
		 * System.out.println("dmm"); }
		 */
	}

	ItemListener chonQuan = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				loadDataPhuong();
			}
		}
	};

	public void loadDataPhuong() {
		dSPhuong.clear();
		int itemPhuong = cboPhuong.getItemCount();
		for (int i = 0; i < itemPhuong; i++) {
			cboPhuong.removeItemAt(0);
		}

		QuanPhuong itemID = (QuanPhuong) cboQuan.getSelectedItem();
		int iD = itemID.getId();
		dSPhuong = QLTD.getPhuongKH(iD);
		for (QuanPhuong o : dSPhuong) {
			cboPhuong.addItem(o);
		}
	}

	// SỰ KIỆN CRUD
	ActionListener btnResetClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Hiển thị danh sách

			reset();
		}

	};

	// search

	// Event add customer
	ActionListener btnAddClick = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			try {
				if (txtTenKH.getText().equals("") || txtMaKH.getText().equals("") || txtMaCT.getText().equals("")
						|| txtDiaChi.getText().equals("") || cboQuan.getSelectedIndex() < 0
						|| cboPhuong.getSelectedIndex() < 0 || txtDienThoai.getText().equals("")
						|| txtEmail.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");

				} else if (!checkPhoneNumber(txtDienThoai.getText())) {
					JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng, vui lòng nhập lại");
				} else if (!checkEmail(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Email không đúng định dạng, vui lòng nhập lại");
				} else if (checkMaCT(txtMaCT.getText())) {
					JOptionPane.showMessageDialog(null, "Mã công tơ đã bị trùng, vui lòng nhập lại");
				} else {
					addKhachHang();
					reset();

				}
				//
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

	// Event edit customer
	ActionListener btnEditClick = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			try {

				if (txtTenKH.getText().equals("") || txtMaKH.getText().equals("") || txtMaCT.getText().equals("")
						|| txtDiaChi.getText().equals("") || cboQuan.getSelectedIndex() < 0
						|| cboPhuong.getSelectedIndex() < 0 || txtDienThoai.getText().equals("")
						|| txtEmail.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Hãy chọn khách hàng bạn muốn sửa");

				} else if (!checkPhoneNumber(txtDienThoai.getText())) {
					JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng, vui lòng nhập lại");
				} else if (!checkEmail(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Email không đúng định dạng, vui lòng nhập lại");
				} else if (checkMaCT(txtMaCT.getText())) {
					JOptionPane.showMessageDialog(null, "Mã công tơ đã bị trùng, vui lòng nhập lại");
				} else {

					int ret = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa ko ?", "Sữa",
							JOptionPane.YES_NO_OPTION);

					if (ret == JOptionPane.YES_OPTION) {
						editKhachHang();
						reset();
						JOptionPane.showMessageDialog(null, "Update thành công !");
					} else {
						JOptionPane.showMessageDialog(null, "Update thất bại !");
					}

				}
				//
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	};

	// Event delete customer
	ActionListener btnDeleteClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (tbl.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Hãy chọn khách hàng bạn muốn xóa");
			} else {

				int ret = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa ko ?", "Xóa", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION) {
					deleteKhachHang(null);
					reset();
					JOptionPane.showMessageDialog(null, "Delete thành công !");
				} else {
					JOptionPane.showMessageDialog(null, "Delete thất bại !");
				}
			}
		}

	};

	// Event Exit
	// Event delete customer
	ActionListener btnExitClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			int exit = JOptionPane.showConfirmDialog(null, "Thoát chương trình?", "Thoát", JOptionPane.YES_NO_OPTION);

			if (exit == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

	};

	// SetVisible Khách Hàng
	ActionListener btnKhachHangClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			reset();
			myBienLai.setVisible(false);
			pnCenterCon.setVisible(true);
			pnSouth.setVisible(true);
			pnActions.setVisible(true);
			myBCDSKH.setVisible(false);
			myBCTHTT.setVisible(false);

		}
	};

	ActionListener btnBienLaiClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			myBienLai.setVisible(true);
			pnCenterCon.setVisible(false);
			pnSouth.setVisible(false);
			pnActions.setVisible(false);
			myBCDSKH.setVisible(false);
			myBCTHTT.setVisible(false);
		}

	};

	ActionListener TKBCClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			bcdskh.setVisible(true);
			bcthtt.setVisible(true);

		}

	};

	ActionListener btnBCDSKHClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			// BCDSKH
			myBienLai.setVisible(false);
			pnCenterCon.setVisible(false);
			pnSouth.setVisible(false);
			pnActions.setVisible(false);
			myBCDSKH.setVisible(true);
			myBCTHTT.setVisible(false);
		}

	};

	ActionListener btnBCTHTTClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			// BSTHTT
			myBienLai.setVisible(false);
			pnCenterCon.setVisible(false);
			pnSouth.setVisible(false);
			pnActions.setVisible(false);
			myBCDSKH.setVisible(false);
			myBCTHTT.setVisible(true);
			
		}

	};

	// CHECK MÃ CT
	public static boolean checkMaCT(String maCT) throws SQLException {
		ResultSet maCTKH = QuanLyTienDienDAO.getMaCT();
		while (maCTKH.next()) {
			if (maCT.equals(maCTKH.getString("mact"))) {
				return true;
			}
		}
		return false;
	}

	// CHECK MAIL
	public static boolean checkEmail(String email) {
		String ktEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return email.matches(ktEmail);
	}

	// CHECK PHONE
	public static boolean checkPhoneNumber(String phoneNumber) {
		String ktPhone = "(\\+84|0)\\d{9,10}";
		return phoneNumber.matches(ktPhone);
	}

	public KhachHangUi(String title) throws SQLException {
		super(title);
		connectControls();
		addControls();
		addEvents();
		hienThiDS();
	}

	// EVENT KHÁCH HÀNG
	public void addEvents() {
		khachHang.addActionListener(btnKhachHangClick);
		bienLai.addActionListener(btnBienLaiClick);
		TKBC.addActionListener(TKBCClick);
		bcdskh.addActionListener(btnBCDSKHClick);
		bcthtt.addActionListener(btnBCTHTTClick);
		btnThem.addActionListener(btnAddClick);
		btnSua.addActionListener(btnEditClick);
		btnXoa.addActionListener(btnDeleteClick);
		btnReset.addActionListener(btnResetClick);
		btnThoat.addActionListener(btnExitClick);
		// cboQuan.addActionListener(eventQuan1);
		tbl.addMouseListener(tblUserClick);

	}

	public void showWindow() {
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
