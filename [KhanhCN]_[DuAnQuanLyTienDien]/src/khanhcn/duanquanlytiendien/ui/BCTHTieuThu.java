package khanhcn.duanquanlytiendien.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BCTHTieuThu extends JPanel {
	JPanel pnTieuChi;
	JPanel pnThoiGian;
	JPanel pnPhuong;
	JPanel pnQuan;
	JPanel pnMaKH;
	JPanel pnListyear;
	JPanel pnListmonth;

	JPanel pnListyearKT;
	JPanel pnListmonthKT;

	JPanel pnListmonthKTD;
	JPanel pnListyearKD;

	JPanel pnListmonthKD;
	JPanel pnListyearKTD;
	JTextField txtMaKH;
	DefaultTableModel dm;
	JTable tbl;
	JComboBox<String> CBtieuChiKH;
	JComboBox<String> CBKTimeNam;
	JComboBox<String> CBKTimeThang;
	JComboBox<String> CBKTimeNamD;
	JComboBox<String> CBKTimeThangD;
	JComboBox<String> CBthoiGian;
	JComboBox<String> CBquan;
	JComboBox<String> CBphuong;
	JComboBox<String> CBnam;
	JComboBox<String> CBthang;
	JScrollPane sc;
	String TCKH;

	JPanel pnSouth;

	public BCTHTieuThu() {

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

		JPanel Content = new JPanel();
		Content.setLayout(new BoxLayout(Content, BoxLayout.Y_AXIS));

		// Tạo JComboBox chứa các Tiêu chí khách hàng
		pnTieuChi = new JPanel();
		JLabel lblTC = new JLabel("Chọn tiêu chí:        ");
		CBtieuChiKH = new JComboBox<String>();
		CBtieuChiKH.setPreferredSize(new Dimension(158, 28));
		CBtieuChiKH.addItem("Chọn");
		CBtieuChiKH.addItem("Tất cả KH");
		CBtieuChiKH.addItem("KH theo khu vực");
		CBtieuChiKH.addItem("KH cụ thể");
		pnTieuChi.add(lblTC);
		pnTieuChi.add(CBtieuChiKH);
		Content.add(pnTieuChi);

		// Tạo JComboBox chứa cái thời gian
		pnThoiGian = new JPanel();
		JLabel lblTime = new JLabel("Chọn thời gian:    ");
		CBthoiGian = new JComboBox<String>();
		CBthoiGian.setPreferredSize(new Dimension(158, 28));
		CBthoiGian.addItem("Chọn");
		CBthoiGian.addItem("Theo năm");
		CBthoiGian.addItem("Khoảng thời gian");
		CBthoiGian.addItem("Theo kỳ");
		pnThoiGian.add(lblTime);
		pnThoiGian.add(CBthoiGian);
		Content.add(pnThoiGian);

		// Mã khách hàng
		pnMaKH = new JPanel();
		JLabel lblMaKH = new JLabel("Mã KH:        ");
		txtMaKH = new JTextField();
		txtMaKH.setPreferredSize(new Dimension(158, 28));
		pnMaKH.add(lblMaKH);
		pnMaKH.add(txtMaKH);
		Content.add(pnMaKH);

		// Tạo JComboBox chứ các Quận
		pnQuan = new JPanel();
		JLabel lblQuan = new JLabel("Chọn Quận:        ");
		CBquan = new JComboBox<String>();
		CBquan.setPreferredSize(new Dimension(158, 28));
		CBquan.addItem("FFSE1701");

		CBquan.addItem("FFSE1702");

		CBquan.addItem("FFSE1703");

		CBquan.addItem("FFSE1704");
		pnQuan.add(lblQuan);
		pnQuan.add(CBquan);
		Content.add(pnQuan);

		// Tạo JComboBox chứ các Phường
		pnPhuong = new JPanel();
		JLabel lblPhuong = new JLabel("Chọn Phường:   ");
		CBphuong = new JComboBox<String>();
		CBphuong.setPreferredSize(new Dimension(158, 28));
		CBphuong.addItem("FFSE1701");
		CBphuong.addItem("FFSE1702");
		CBphuong.addItem("FFSE1703");
		CBphuong.addItem("FFSE1704");
		pnPhuong.add(lblPhuong);
		pnPhuong.add(CBphuong);
		Content.add(pnPhuong);

		// Tạo JComboBox chứa các tháng
		pnListmonth = new JPanel();
		JLabel lblmonth = new JLabel("Chọn tháng:  ");

		CBthang = new JComboBox<String>();
		CBthang.setPreferredSize(new Dimension(60, 28));
		CBthang.addItem("1");
		CBthang.addItem("2");
		CBthang.addItem("3");
		CBthang.addItem("4");
		CBthang.addItem("5");
		CBthang.addItem("6");
		CBthang.addItem("7");
		CBthang.addItem("8");
		CBthang.addItem("9");
		CBthang.addItem("10");
		CBthang.addItem("11");
		CBthang.addItem("12");

		pnListmonth.add(lblmonth);
		pnListmonth.add(CBthang);
		Content.add(pnListmonth);

		// Tạo JComboBox chứa các năm
		pnListyear = new JPanel();
		JLabel lblyear = new JLabel("Chọn năm:    ");

		CBnam = new JComboBox<String>();
		CBnam.setPreferredSize(new Dimension(62, 28));
		CBnam.addItem("2017");
		CBnam.addItem("2018");
		CBnam.addItem("2019");
		CBnam.addItem("2020");
		pnListyear.add(lblyear);
		pnListyear.add(CBnam);
		Content.add(pnListyear);
		
		JPanel KhoangTG = new JPanel();
		KhoangTG.setLayout(new BoxLayout(KhoangTG, BoxLayout.X_AXIS));


		// KHOẢNG THỜI GIAN
		JPanel KhoangTG1 = new JPanel();
		KhoangTG1.setLayout(new BoxLayout(KhoangTG1, BoxLayout.Y_AXIS));

		// Tạo JComboBox chứa các tháng (từ)
		pnListmonthKT = new JPanel();
		JLabel lblmonthKT = new JLabel("Từ tháng:  ");

		CBKTimeThang = new JComboBox<String>();
		CBKTimeThang.setPreferredSize(new Dimension(60, 28));
		CBKTimeThang.addItem("Tháng");
		CBKTimeThang.addItem("1");
		CBKTimeThang.addItem("2");
		CBKTimeThang.addItem("3");
		CBKTimeThang.addItem("4");
		CBKTimeThang.addItem("5");
		CBKTimeThang.addItem("6");
		CBKTimeThang.addItem("7");
		CBKTimeThang.addItem("8");
		CBKTimeThang.addItem("9");
		CBKTimeThang.addItem("10");
		CBKTimeThang.addItem("11");
		CBKTimeThang.addItem("12");

		pnListmonthKT.add(lblmonthKT);
		pnListmonthKT.add(CBKTimeThang);
		KhoangTG1.add(pnListmonthKT);

		// Tạo JComboBox chứa các năm (từ)
		pnListyearKT = new JPanel();
		JLabel lblyearKT = new JLabel("Từ năm:    ");

		CBKTimeNam = new JComboBox<String>();
		CBKTimeNam.setPreferredSize(new Dimension(62, 28));
		CBKTimeNam.addItem("Năm");
		CBKTimeNam.addItem("2017");
		CBKTimeNam.addItem("2018");
		CBKTimeNam.addItem("2019");
		CBKTimeNam.addItem("2020");

		pnListyearKT.add(lblyearKT);
		pnListyearKT.add(CBKTimeNam);
		KhoangTG1.add(pnListyearKT);
		
		JPanel KhoangTG2 = new JPanel();
		KhoangTG2.setLayout(new BoxLayout(KhoangTG2, BoxLayout.Y_AXIS));

		// Tạo JComboBox chứa các tháng (đến)
		pnListmonthKTD = new JPanel();
		JLabel lblmonthKD = new JLabel("Đến tháng:  ");

		CBKTimeThangD = new JComboBox<String>();
		CBKTimeThangD.setPreferredSize(new Dimension(60, 28));
		CBKTimeThangD.addItem("Tháng");
		CBKTimeThangD.addItem("1");
		CBKTimeThangD.addItem("2");
		CBKTimeThangD.addItem("3");
		CBKTimeThangD.addItem("4");
		CBKTimeThangD.addItem("5");
		CBKTimeThangD.addItem("6");
		CBKTimeThangD.addItem("7");
		CBKTimeThangD.addItem("8");
		CBKTimeThangD.addItem("9");
		CBKTimeThangD.addItem("10");
		CBKTimeThangD.addItem("11");
		CBKTimeThangD.addItem("12");

		pnListmonthKTD.add(lblmonthKD);
		pnListmonthKTD.add(CBKTimeThangD);
		KhoangTG2.add(pnListmonthKTD);

		// Tạo JComboBox chứa các năm (đến)
		pnListyearKTD = new JPanel();
		JLabel lblyearKD = new JLabel("Đến năm:    ");

		CBKTimeNamD = new JComboBox<String>();
		CBKTimeNamD.setPreferredSize(new Dimension(62, 28));
		CBKTimeNamD.addItem("Năm");
		CBKTimeNamD.addItem("2017");
		CBKTimeNamD.addItem("2018");
		CBKTimeNamD.addItem("2019");
		CBKTimeNamD.addItem("2020");

		pnListyearKTD.add(lblyearKD);
		pnListyearKTD.add(CBKTimeNamD);
		KhoangTG2.add(pnListyearKTD);

		// phần button CRUD
		JPanel CRUD = new JPanel();

		JButton btnThem = new JButton("      Thêm");
		btnThem.setPreferredSize(new Dimension(100, 50));
		btnThem.setMargin(new Insets(0, 10, 0, 0));
		ImageIcon iconThem = new ImageIcon(
				new ImageIcon("img/Them.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel add = new JLabel(iconThem);
		btnThem.add(add);
		CRUD.add(btnThem);

		JButton btnSua = new JButton("   Sửa");
		btnSua.setPreferredSize(new Dimension(110, 50));
		btnSua.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconEdit = new ImageIcon(
				new ImageIcon("img/edit.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel edit = new JLabel(iconEdit);
		btnSua.add(edit);
		CRUD.add(btnSua);

		JButton btnXoa = new JButton("   Xóa");
		btnXoa.setPreferredSize(new Dimension(110, 50));
		btnXoa.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconDelete = new ImageIcon(
				new ImageIcon("img/xoa.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel delete = new JLabel(iconDelete);
		btnXoa.add(delete);
		pnMain.add(btnXoa);

		JButton btnThoat = new JButton("    Thoát");
		btnThoat.setPreferredSize(new Dimension(110, 50));
		btnThoat.setMargin(new Insets(0, 18, 0, 0));
		ImageIcon iconBack = new ImageIcon(
				new ImageIcon("img/trove.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel back = new JLabel(iconBack);
		btnThoat.add(back);
		CRUD.add(btnThoat);

		// add button
		JPanel pnActions = new JPanel();
		pnActions.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
		pnActions.add(btnThem);
		pnActions.add(btnSua);
		pnActions.add(btnXoa);
		pnActions.add(btnThoat);

		pnSouth = new JPanel();
		pnSouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pnSouth.setBackground(Color.decode("#1E7EA4"));
		pnSouth.setPreferredSize(new Dimension(580, 400));
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		pnMain.add(pnSouth, BorderLayout.SOUTH);

		// Tiêu đề bảng KH
		JLabel lblTitleKH = new JLabel("BÁO CÁO DSKH KHÁCH HÀNG");
		Font fontTitleKH = new Font("Courier", Font.ITALIC | Font.BOLD, 16);
		lblTitleKH.setForeground(Color.WHITE);
		lblTitleKH.setBorder(BorderFactory.createEmptyBorder(10, 300, 10, 0));
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
		
		pnMain.add(Content);
		KhoangTG.add(KhoangTG1);
		KhoangTG.add(KhoangTG2);
		Content.add(KhoangTG);
		pnMain.add(CRUD);
		pnMain.add(pnActions);
		pnMain.add(pnSouth);

		pnThoiGian.setVisible(false);
		pnMaKH.setVisible(false);
		pnPhuong.setVisible(false);
		pnQuan.setVisible(false);
		pnListyear.setVisible(false);
		pnListmonth.setVisible(false);
		pnListmonthKT.setVisible(false);
		pnListyearKT.setVisible(false);
		pnListmonthKTD.setVisible(false);
		pnListyearKTD.setVisible(false);

		this.add(pnMain);

		// Tiêu chí khách hàng
		CBtieuChiKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectedObject = CBtieuChiKH.getSelectedItem();
				TCKH = (String) selectedObject;
				// System.out.println(TCKH);
				// TIEUCHI
				if (TCKH == "Chọn") {
					pnThoiGian.setVisible(false);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListyear.setVisible(false);
					pnListmonth.setVisible(false);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);

					

				} else if (TCKH == "Tất cả KH") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListyear.setVisible(false);
					pnListmonth.setVisible(false);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);

				} else if (TCKH == "KH cụ thể") {

					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(true);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListyear.setVisible(false);
					pnListmonth.setVisible(false);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);

				} else if (TCKH == "KH theo khu vực") {

					pnMaKH.setVisible(false);
					pnPhuong.setVisible(true);
					pnQuan.setVisible(true);
					pnThoiGian.setVisible(true);
					pnListyear.setVisible(false);
					pnListmonth.setVisible(false);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);
				}

			}

		});

		// Thời gian
		CBthoiGian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectedObject = CBthoiGian.getSelectedItem();
				String time = (String) selectedObject;
				// System.out.println(TCKH);
				// TIEUCHI
				if (time == "Chọn") {
					pnThoiGian.setVisible(false);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListyear.setVisible(false);
					pnListmonth.setVisible(false);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);

				} else if (TCKH == "Tất cả KH" & time == "Theo năm") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListyear.setVisible(true);
					pnListmonth.setVisible(false);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);

				} else if (TCKH == "Tất cả KH" & time == "Khoảng thời gian") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListyear.setVisible(false);
					pnListmonth.setVisible(false);
					pnListmonthKT.setVisible(true);
					pnListyearKT.setVisible(true);
					pnListmonthKTD.setVisible(true);
					pnListyearKTD.setVisible(true);

				} else if (TCKH == "Tất cả KH" & time == "Theo kỳ") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListyear.setVisible(true);
					pnListmonth.setVisible(true);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);

				} else if (TCKH == "KH cụ thể" & time == "Theo năm") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(true);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListmonth.setVisible(false);
					pnListyear.setVisible(true);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);
					
				} else if (TCKH == "KH cụ thể" & time == "Khoảng thời gian") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(true);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListmonth.setVisible(false);
					pnListyear.setVisible(false);
					pnListmonthKT.setVisible(true);
					pnListyearKT.setVisible(true);
					pnListmonthKTD.setVisible(true);
					pnListyearKTD.setVisible(true);

				} else if (TCKH == "KH cụ thể" & time == "Theo kỳ") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(false);
					pnQuan.setVisible(false);
					pnListmonth.setVisible(true);
					pnListyear.setVisible(true);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);

				} else if (TCKH == "KH theo khu vực" & time == "Theo năm") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(true);
					pnQuan.setVisible(true);
					pnListmonth.setVisible(false);
					pnListyear.setVisible(true);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);

				} else if (TCKH == "KH theo khu vực" & time == "Khoảng thời gian") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(true);
					pnQuan.setVisible(true);
					pnListmonth.setVisible(false);
					pnListyear.setVisible(false);
					pnListmonthKT.setVisible(true);
					pnListyearKT.setVisible(true);
					pnListmonthKTD.setVisible(true);
					pnListyearKTD.setVisible(true);

				} else if (TCKH == "KH theo khu vực" & time == "Theo kỳ") {
					pnThoiGian.setVisible(true);
					pnMaKH.setVisible(false);
					pnPhuong.setVisible(true);
					pnQuan.setVisible(true);
					pnListmonth.setVisible(false);
					pnListyear.setVisible(false);
					pnListmonthKT.setVisible(false);
					pnListyearKT.setVisible(false);
					pnListmonthKTD.setVisible(false);
					pnListyearKTD.setVisible(false);
				}
			}

		});

	}
}
