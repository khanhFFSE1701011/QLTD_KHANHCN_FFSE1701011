package khanhcn.duanquanlytiendien.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.gjt.mm.mysql.Driver;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import khanhcn.duanquanlytiendien.DAO.QuanLyTienDienDAO;
import khanhcn.duanquanlytiendien.entity.BienLai;
import khanhcn.duanquanlytiendien.entity.KhachHang;

public class BienLaiUi extends JPanel {
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnThoat;
	static int chiSoCu;
	static int chiSoMoi;

	private DateFormat dateFormat;
	private Date date;

	private String maCT;
	private String stringChiSoCT;
	private String ngayNhapCT;
	private String thangNhapCT;
	private String namNhapCT;
	private String ctCu;
	private int chiSoCT;
	private String chuKy;
	private int tienDien;

	static QuanLyTienDienDAO QLTD = new QuanLyTienDienDAO();
	static ArrayList<BienLai> dsBL = new ArrayList<BienLai>();
	static Connection conn = QLTD.getConnect("localhost", "khanhcn_ffse1701011", "khanhcn", "123456");
	private JTable tbl;
	private JPanel pnTableBL;
	private JTextField txtMaCT;
	private JTextField txtNgayNhap;
	private JTextField txtCSCT;
	private JComboBox<String> cbo;
	private DefaultTableModel dm;
	JButton btnSearch;
	// txt Biên Lai
	JMonthChooser jmc;
	JYearChooser jyc;
	JTextField tfMaKH;
	JTextField tfTenKH;
	JTextField tfDiaChi;
	JTextField tfMaCT;
	JTextField tfPhuong;
	JTextField tfQuan;
	JTextField tfChuKy;
	JTextField tfCTcu;
	JTextField tfThang;
	JTextField tfNam;

	public void getConnect(String strServer, String strDatabase, String strUser, String strPwd) {
		String strConnect = "jdbc:mysql://" + strServer + "/" + strDatabase
				+ "?useUnicode=true&characterEncoding=utf-8";
		Properties pro = new Properties();
		pro.put("user", strUser);
		pro.put("password", strPwd);
		try {
			com.mysql.jdbc.Driver driver = new Driver();
			conn = (Connection) driver.connect(strConnect, pro);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public BienLaiUi() {
		addControls();
		addEvent();
	}

	public void addControls() {

		// Nạp container và add main panel

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

		JPanel CenterBL = new JPanel();
		pnMain.add(CenterBL, BorderLayout.CENTER);
		CenterBL.setLayout(new BoxLayout(CenterBL, BoxLayout.Y_AXIS));

		JPanel pnContent = new JPanel();
		pnContent.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pnContent.setLayout(new BoxLayout(pnContent, BoxLayout.X_AXIS));

		JPanel pnLeft = new JPanel();
		pnLeft.setForeground(Color.RED);
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

		// Tạo panel MCT
		JPanel pnMaCT = new JPanel();
		JLabel lblMaCT = new JLabel("  Mã CT       :");
		txtMaCT = new JTextField();
		txtMaCT.setPreferredSize(new Dimension(100, 28));
		btnSearch = new JButton("Tìm");
		btnSearch.setPreferredSize(new Dimension(55, 28));
		pnMaCT.add(lblMaCT);
		pnMaCT.add(txtMaCT);
		pnMaCT.add(btnSearch);
		pnLeft.add(pnMaCT);

		// Tạo panel Họ chứa dòng chữ Họ và textbox Họ
		JPanel pnNgayNhap = new JPanel();
		JLabel lbldate = new JLabel("Ngày nhập :");
		txtNgayNhap = new JTextField();
		txtNgayNhap.setPreferredSize(new Dimension(158, 28));
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date ngaynhapCT = new Date();
		date = new Date();
		txtNgayNhap.setText(dateFormat.format(date));

		txtNgayNhap.setEnabled(false);
		pnNgayNhap.add(lbldate);
		pnNgayNhap.add(txtNgayNhap);
		pnLeft.add(pnNgayNhap);

		JPanel pnChisoCT = new JPanel();
		JLabel lblCSCT = new JLabel("Chỉ số CT   :");
		txtCSCT = new JTextField();
		txtCSCT.setPreferredSize(new Dimension(158, 28));
		pnChisoCT.add(lblCSCT);
		pnChisoCT.add(txtCSCT);
		pnLeft.add(pnChisoCT);

		JLabel lblMonth = new JLabel("Chu kỳ        :");

		JPanel pnChuKyCLD = new JPanel();
		pnChuKyCLD.setPreferredSize(new Dimension(158, 28));
		jmc = new JMonthChooser();
		jyc = new JYearChooser();
		pnChuKyCLD.add(lblMonth);
		pnChuKyCLD.add(jmc);
		pnChuKyCLD.add(jyc);
		pnLeft.add(pnChuKyCLD);

		JPanel pnRight = new JPanel();
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		// Mã khách hàng
		JPanel pnMaKH = new JPanel();
		JLabel lblMaKH = new JLabel("Mã KH:  ");
		tfMaKH = new JTextField();
		tfMaKH.setPreferredSize(new Dimension(158, 28));
		pnMaKH.add(lblMaKH);
		pnMaKH.add(tfMaKH);
		pnRight.add(pnMaKH);

		// Tên khách hàng
		JPanel pnTenKH = new JPanel();
		JLabel lblTenKH = new JLabel("Tên KH: ");
		tfTenKH = new JTextField();
		tfTenKH.setPreferredSize(new Dimension(158, 28));
		pnTenKH.add(lblTenKH);
		pnTenKH.add(tfTenKH);
		pnRight.add(pnTenKH);

		// Địa chỉ khách hàng
		JPanel pnDiaChi = new JPanel();
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		tfDiaChi = new JTextField();
		tfDiaChi.setPreferredSize(new Dimension(158, 28));
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(tfDiaChi);
		pnRight.add(pnDiaChi);

		// Tạo panel MCT
		JPanel pnMaCT1 = new JPanel();
		JLabel lblMaCT1 = new JLabel("  Mã CT: ");
		tfMaCT = new JTextField();
		tfMaCT.setPreferredSize(new Dimension(158, 28));
		pnMaCT1.add(lblMaCT1);
		pnMaCT1.add(tfMaCT);
		pnRight.add(pnMaCT1);

		// Điện thoại khách hàng
		JPanel pnChuKy = new JPanel();
		JLabel lblDienThoai = new JLabel("Chu kỳ: ");
		tfChuKy = new JTextField();
		tfChuKy.setPreferredSize(new Dimension(158, 28));
		pnChuKy.add(lblDienThoai);
		pnChuKy.add(tfChuKy);
		pnRight.add(pnChuKy);

		// Email khách hàng
		JPanel pnEmail = new JPanel();
		JLabel lblEmail = new JLabel("CT Cũ:    ");
		tfCTcu = new JTextField();
		tfCTcu.setPreferredSize(new Dimension(158, 28));
		pnEmail.add(lblEmail);
		pnEmail.add(tfCTcu);
		pnRight.add(pnEmail);

		// button CRUD

		/*
		 * JButton btnNhapBL = new JButton("          Nhập BL");
		 * btnNhapBL.setPreferredSize(new Dimension(100, 50)); btnNhapBL.setMargin(new
		 * Insets(0, 5, 0, 0)); ImageIcon iconNhap = new ImageIcon( new
		 * ImageIcon("img/bienlai.png").getImage().getScaledInstance(30, 30,
		 * Image.SCALE_SMOOTH)); JLabel nhap = new JLabel(iconNhap);
		 * btnNhapBL.add(nhap);
		 */

		btnThem = new JButton("      Thêm");
		btnThem.setPreferredSize(new Dimension(100, 50));
		btnThem.setMargin(new Insets(0, 10, 0, 0));
		ImageIcon iconThem = new ImageIcon(
				new ImageIcon("img/Them.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel add = new JLabel(iconThem);
		btnThem.add(add);

		btnSua = new JButton("   Sửa");
		btnSua.setPreferredSize(new Dimension(110, 50));
		btnSua.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconEdit = new ImageIcon(
				new ImageIcon("img/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel edit = new JLabel(iconEdit);
		btnSua.add(edit);

		btnXoa = new JButton("   Xóa");
		btnXoa.setPreferredSize(new Dimension(110, 50));
		btnXoa.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconDelete = new ImageIcon(
				new ImageIcon("img/xoa.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel delete = new JLabel(iconDelete);
		btnXoa.add(delete);

		btnThoat = new JButton("    Thoát");
		btnThoat.setPreferredSize(new Dimension(110, 50));
		btnThoat.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconBack = new ImageIcon(
				new ImageIcon("img/trove.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel back = new JLabel(iconBack);
		btnThoat.add(back);

		// add button
		JPanel pnActions = new JPanel();
		pnLeft.setForeground(Color.BLUE);
		pnActions.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
		// pnActions.add(btnNhapBL);
		pnActions.add(btnThem);
		pnActions.add(btnSua);
		pnActions.add(btnXoa);
		pnActions.add(btnThoat);

		// phần Table Biên Lai

		pnTableBL = new JPanel();
		pnTableBL.setBackground(Color.decode("#1E7EA4"));
		pnTableBL.setForeground(Color.RED);
		pnTableBL.setPreferredSize(new Dimension(580, 400));
		pnTableBL.setLayout(new BoxLayout(pnTableBL, BoxLayout.Y_AXIS));
		pnMain.add(pnTableBL, BorderLayout.SOUTH);

		// Tiêu đề bảng BL
		JLabel lblTitleBL = new JLabel("THÔNG TIN BIÊN LAI ");
		Font fontTitleBL = new Font("Courier", Font.ITALIC | Font.BOLD, 15);
		lblTitleBL.setBorder(BorderFactory.createEmptyBorder(10, 300, 10, 0));
		lblTitleBL.setFont(fontTitleBL);
		lblTitleBL.setForeground(Color.WHITE);
		pnTableBL.add(lblTitleBL);

		// Table thông tin biên lai
		dm = new DefaultTableModel();
		dm.addColumn("Mã CT");
		dm.addColumn("Tháng");
		dm.addColumn("Năm");
		dm.addColumn("Chỉ số CT ");
		dm.addColumn("Ngày nhập");
		dm.addColumn("Tổng tiền");

		tbl = new JTable(dm);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sc = new JScrollPane(tbl);

		// add main panel vào container
		pnContent.add(pnLeft);
		pnContent.add(pnRight);
		CenterBL.add(pnContent);
		CenterBL.add(pnActions);
		pnMain.add(CenterBL);
		pnTableBL.add(sc);
		pnMain.add(pnTableBL);

		this.add(pnMain);

		ActionListener btnClickSearch = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					search();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pnMaKH.setVisible(true);
				pnChuKy.setVisible(true);
				pnEmail.setVisible(true);
				pnTenKH.setVisible(true);
				pnDiaChi.setVisible(true);
				pnMaCT1.setVisible(true);

			}

		};

		btnSearch.addActionListener(btnClickSearch);

		pnMaKH.setVisible(false);
		pnChuKy.setVisible(false);
		pnEmail.setVisible(false);
		pnTenKH.setVisible(false);
		pnDiaChi.setVisible(false);
		pnMaCT1.setVisible(false);

	}

	// NHẬP BL
	public void nhap() {
		maCT = txtMaCT.getText();

		stringChiSoCT = txtCSCT.getText();

		chiSoMoi = Integer.parseInt(stringChiSoCT);

		thangNhapCT = txtNgayNhap.getText();

		DateFormat MM = new SimpleDateFormat("MM");

		thangNhapCT = MM.format(date);

		DateFormat yyyy = new SimpleDateFormat("yyyy");

		namNhapCT = yyyy.format(date);

		chiSoCu = Integer.parseInt(ctCu);

		chiSoCT = chiSoMoi - chiSoCu;

	}

	// KIỂM TRA KHÁCH KHÀNG
	public void search() throws SQLException {
		if (!checkMaCT(txtCSCT.getText())) {

			try {

				String str = txtMaCT.getText();

				PreparedStatement st = (PreparedStatement) conn.prepareStatement(
						"SELECT khanhhang.makh, khanhhang.tenkh , khanhhang.diachi, khanhhang.dienthoai , bienlai.mact, bienlai.thang, bienlai.nam , bienlai.chisoct FROM khanhhang INNER JOIN bienlai ON khanhhang.mact = bienlai.mact WHERE bienlai.mact=?");
				st.setString(1, str);
				ResultSet rs = st.executeQuery();

				if (rs.next()) {

					String s = rs.getString(1);

					String s1 = rs.getString(2);

					String s2 = rs.getString(3);

					String s3 = rs.getString(5);

					String s4 = rs.getString(6);

					String s5 = rs.getString(7);

					chiSoCu = rs.getInt(8);
					String ctCu = String.valueOf(chiSoCu);

					// Sets Records in TextFields.

					tfMaKH.setText(s);
					tfTenKH.setText(s1);
					tfDiaChi.setText(s2);
					tfMaCT.setText(s3);
					tfChuKy.setText(s4 + "-" + s5);
					tfCTcu.setText(ctCu);

				} else {

					// System.out.println(2);
					String str1 = txtMaCT.getText();

					PreparedStatement st1 = (PreparedStatement) conn
							.prepareStatement("SELECT * FROM khanhhang WHERE mact = ?");

					st1.setString(1, str1);

					// Excuting Query
					ResultSet rs1 = st1.executeQuery();

					if (rs1.next()) {

						String s1 = rs1.getString(2);
						String s2 = rs1.getString(3);
						String s3 = rs1.getString(4);
						String s4 = rs1.getString(5);

						// Sets Records in TextFields.

						tfMaKH.setText(s1);
						tfTenKH.setText(s2);
						tfDiaChi.setText(s3);
						tfMaCT.setText(s4);
						tfChuKy.setText("Chưa có ! ");
						tfCTcu.setText("0");

					} else {

						JOptionPane.showMessageDialog(null, "Mã CT không tồn tại ! Vui lòng thử lại ");
					}

				}
			} catch (Exception ex) {

				System.out.println(ex);

			}
		}

	}

	// THÊM BIÊN LAI

	public void add() {
		nhap();
		int kWh0den50 = 77450;
		int kWh50den100 = kWh0den50 + 80000;
		int kWh100den200 = kWh50den100 + 185800;
		int kWh200den300 = kWh100den200 + 2340000;
		int kWh300den400 = kWh200den300 + 261500;

		if (chiSoCT < 50) {
			tienDien = chiSoCT * 1549;
		} else if (chiSoCT > 50 && chiSoCT < 100) {
			tienDien = kWh0den50 + (chiSoCT * 1600);
		} else if (chiSoCT > 100 && chiSoCT < 200) {
			tienDien = kWh50den100 + (chiSoCT * 1858);
		} else if (chiSoCT > 200 && chiSoCT < 300) {
			tienDien = kWh100den200 + (chiSoCT * 2340);
		} else if (chiSoCT > 300 && chiSoCT < 400) {
			tienDien = kWh200den300 + (chiSoCT * 2615);
		} else if (chiSoCT > 400) {
			tienDien = kWh300den400 + (chiSoCT * 2710);
		} else {
			JOptionPane.showMessageDialog(null, "Lỗi tính tiền điện !!!");
		}

		String stringTienDien = String.valueOf(tienDien);
		nhap();
		dm.addRow(new String[] { maCT, ngayNhapCT, chuKy, stringChiSoCT, stringTienDien });
		QLTD.addBienLai(new BienLai(maCT, ngayNhapCT, thangNhapCT, namNhapCT, chuKy, chiSoMoi, tienDien));

	}

	// EDIT BIÊN LAI
	public void editBL() {

	}

	// DELETE BIÊN LAI
	public void deleteBL() {

	}

	// EXIT BIÊN LAI
	public void exitBL() {

	}

	// CHECK MÃ CT
	public static boolean checkMaCT(String maCT) throws SQLException {
		ResultSet maCTKH = QuanLyTienDienDAO.getMaCTBL();
		while (maCTKH.next()) {
			if (maCT.equals(maCTKH.getString("mact"))) {
				return true;
			}
		}
		return false;
	}

	/*ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSearch) {
				try {
					search();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (e.getSource() == btnThem) {
				add();

			} else if (e.getSource() == btnThoat) {
				int ret = JOptionPane.showConfirmDialog(null, "Thoát chương trình?", "Thoát",
						JOptionPane.YES_NO_OPTION);

				if (ret == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			} else if (e.getSource() == btnSua) {
				editBL();
			} else if (e.getSource() == btnXoa) {
				deleteBL();
			}

		}
	};*/

	public void addEvent() {
		btnThem.addActionListener(btnAddClick);
	}
	
	ActionListener btnAddClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			add();
		}
	};

}
