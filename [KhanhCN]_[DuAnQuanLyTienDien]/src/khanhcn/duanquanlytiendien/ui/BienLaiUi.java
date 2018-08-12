package khanhcn.duanquanlytiendien.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import khanhcn.duanquanlytiendien.DAO.QuanLyTienDienDAO;

public class BienLaiUi extends JPanel {
	static QuanLyTienDienDAO QLTD = new QuanLyTienDienDAO();
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
	JTextField tfMaKH;
	JTextField tfTenKH;
	JTextField tfDiaChi;
	JTextField tfPhuong;
	JTextField tfQuan;
	JTextField tfDienThoai;
	JTextField tfEmail;

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

	public void search() {
		try {

			String str = txtMaCT.getText();

			Connection conn = QLTD.getConnect("localhost", "khanhcn_ffse1701011", "khanhcn", "123456");
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("select * from khanhhang where mact=?");

			st.setString(1, str);

			// Excuting Query

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				String s = rs.getString(1);

				String s1 = rs.getString(2);

				String s2 = rs.getString(3);

				String s3 = rs.getString(4);

				String s4 = rs.getString(5);

				String s5 = rs.getString(6);

				String s6 = rs.getString(7);

				// Sets Records in TextFields.

				tfMaKH.setText(s);
				tfTenKH.setText(s1);
				tfDiaChi.setText(s2);
				tfPhuong.setText(s3);
				tfQuan.setText(s4);
				tfDienThoai.setText(s5);
				tfEmail.setText(s6);

			} else {

				JOptionPane.showMessageDialog(null, "Khách hàng ko tồn tại !");

			}

			// Create Exception Handler

		} catch (Exception ex) {

			System.out.println(ex);

		}
	}

	public BienLaiUi() {

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

		// Tạo panel mãSV chứa dòng chữ mãSV
		JPanel pnMaCT = new JPanel();
		JLabel lblMaCT = new JLabel("  Mã CT       :");
		txtMaCT = new JTextField();
		txtMaCT.setPreferredSize(new Dimension(158, 28));
		btnSearch = new JButton("Tìm kiếm");
		pnMaCT.add(lblMaCT);
		pnMaCT.add(txtMaCT);
		pnMaCT.add(btnSearch);
		pnLeft.add(pnMaCT);

		// Tạo panel Họ chứa dòng chữ Họ và textbox Họ
		JPanel pnNgayNhap = new JPanel();
		JLabel lbldate = new JLabel("Ngày nhập :");
		txtNgayNhap = new JTextField();
		txtNgayNhap.setPreferredSize(new Dimension(158, 28));
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

		JPanel pnRight = new JPanel();
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		// Tạo JComboBox chứa các tháng
		JPanel pnListmonth = new JPanel();
		JLabel lblmonth = new JLabel("Chọn tháng:  ");

		cbo = new JComboBox<String>();
		cbo.setPreferredSize(new Dimension(60, 28));
		cbo.addItem("1");
		cbo.addItem("2");
		cbo.addItem("3");
		cbo.addItem("4");
		cbo.addItem("5");
		cbo.addItem("6");
		cbo.addItem("7");
		cbo.addItem("8");
		cbo.addItem("9");
		cbo.addItem("10");
		cbo.addItem("11");
		cbo.addItem("12");

		pnListmonth.add(lblmonth);
		pnListmonth.add(cbo);
		pnRight.add(pnListmonth);

		// Thông tin khách hàng
		// Bọc 4 o bên trái
		JPanel pnLeft1 = new JPanel();
		// pnCenterCon.setBackground(Color.GREEN);
		pnLeft1.setLayout(new BoxLayout(pnLeft1, BoxLayout.Y_AXIS));

		// Mã khách hàng
		JPanel pnMaKH = new JPanel();
		JLabel lblMaKH = new JLabel("Mã KH:  ");
		tfMaKH = new JTextField();
		tfMaKH.setPreferredSize(new Dimension(158, 28));
		pnMaKH.add(lblMaKH);
		pnMaKH.add(tfMaKH);
		pnLeft1.add(pnMaKH);

		// Tên khách hàng
		JPanel pnTenKH = new JPanel();
		JLabel lblTenKH = new JLabel("Tên KH: ");
		tfTenKH = new JTextField();
		tfTenKH.setPreferredSize(new Dimension(158, 28));
		pnTenKH.add(lblTenKH);
		pnTenKH.add(tfTenKH);
		pnLeft1.add(pnTenKH);

		// Địa chỉ khách hàng
		JPanel pnDiaChi = new JPanel();
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		tfDiaChi = new JTextField();
		tfDiaChi.setPreferredSize(new Dimension(158, 28));
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(tfDiaChi);
		pnLeft1.add(pnDiaChi);

		JPanel pnRight1 = new JPanel();
		pnRight1.setLayout(new BoxLayout(pnRight1, BoxLayout.Y_AXIS));

		// Tạo JComboBox chứ các Quận
		JPanel pnQuan = new JPanel();
		JLabel lblQuan = new JLabel("Chọn Quận:        ");
		tfQuan = new JTextField();
		tfQuan.setPreferredSize(new Dimension(158, 28));
		pnQuan.add(lblQuan);
		pnQuan.add(tfQuan);
		pnRight1.add(pnQuan);

		// Tạo JComboBox chứ các Phường
		JPanel pnPhuong = new JPanel();
		JLabel lblPhuong = new JLabel("Chọn Phường:   ");
		tfPhuong = new JTextField();
		tfPhuong.setPreferredSize(new Dimension(158, 28));
		pnPhuong.add(lblPhuong);
		pnPhuong.add(tfPhuong);
		pnRight1.add(pnPhuong);

		// Điện thoại khách hàng
		JPanel pnDienThoai = new JPanel();
		JLabel lblDienThoai = new JLabel("Điện thoại:          ");
		tfDienThoai = new JTextField();
		tfDienThoai.setPreferredSize(new Dimension(158, 28));
		pnDienThoai.add(lblDienThoai);
		pnDienThoai.add(tfDienThoai);
		pnRight1.add(pnDienThoai);

		// Email khách hàng
		JPanel pnEmail = new JPanel();
		JLabel lblEmail = new JLabel("Email KH:            ");
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(158, 28));
		pnEmail.add(lblEmail);
		pnEmail.add(tfEmail);
		pnRight1.add(pnEmail);

		pnContent.add(pnLeft1);
		pnContent.add(pnRight1);
		pnMain.add(pnContent);

		// button CRUD
		JButton btnThem = new JButton("      Thêm");
		btnThem.setPreferredSize(new Dimension(100, 50));
		btnThem.setMargin(new Insets(0, 10, 0, 0));
		ImageIcon iconThem = new ImageIcon(
				new ImageIcon("img/Them.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel add = new JLabel(iconThem);
		btnThem.add(add);

		JButton btnSua = new JButton("   Sửa");
		btnSua.setPreferredSize(new Dimension(110, 50));
		btnSua.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconEdit = new ImageIcon(
				new ImageIcon("img/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel edit = new JLabel(iconEdit);
		btnSua.add(edit);

		JButton btnXoa = new JButton("   Xóa");
		btnXoa.setPreferredSize(new Dimension(110, 50));
		btnXoa.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconDelete = new ImageIcon(
				new ImageIcon("img/xoa.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel delete = new JLabel(iconDelete);
		btnXoa.add(delete);

		JButton btnThoat = new JButton("    Thoát");
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
		pnActions.add(btnThem);
		pnActions.add(btnSua);
		pnActions.add(btnXoa);
		pnActions.add(btnThoat);

		// Tạo JComboBox chứa các năm
		JPanel pnListyear = new JPanel();
		JLabel lblyear = new JLabel("Chọn năm:    ");

		cbo = new JComboBox<String>();
		cbo.setPreferredSize(new Dimension(62, 28));
		cbo.addItem("2017");

		cbo.addItem("2018");

		cbo.addItem("2019");

		cbo.addItem("2020");

		pnListyear.add(lblyear);
		pnListyear.add(cbo);
		pnRight.add(pnListyear);

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
				if (txtMaCT.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Khách hàng không tồn tại !");
				} else {
					search();
					pnMaKH.setVisible(true);
					pnDienThoai.setVisible(true);
					pnEmail.setVisible(true);
					pnPhuong.setVisible(true);
					pnQuan.setVisible(true);
					pnTenKH.setVisible(true);
					pnDiaChi.setVisible(true);
					
					pnMaCT.setVisible(false);
					
				}

			}

		};

		btnSearch.addActionListener(btnClickSearch);

		pnChisoCT.setVisible(false);
		pnNgayNhap.setVisible(false);
		pnListmonth.setVisible(false);
		pnListyear.setVisible(false);

		pnMaKH.setVisible(false);
		pnDienThoai.setVisible(false);
		pnEmail.setVisible(false);
		pnPhuong.setVisible(false);
		pnQuan.setVisible(false);
		pnTenKH.setVisible(false);
		pnDiaChi.setVisible(false);

	}

}
