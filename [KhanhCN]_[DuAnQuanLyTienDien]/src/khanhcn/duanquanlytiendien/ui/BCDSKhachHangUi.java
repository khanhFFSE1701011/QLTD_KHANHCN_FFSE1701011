package khanhcn.duanquanlytiendien.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BCDSKhachHangUi extends JPanel {
	DefaultTableModel dm;
	JTable tbl;
	JComboBox<String> cbo;
	JScrollPane sc;
	
	JPanel pnSouth;

	public BCDSKhachHangUi() {

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

		JPanel Content = new JPanel();
		Content.setLayout(new BoxLayout(Content, BoxLayout.X_AXIS));

		// Tạo JComboBox chứ các Quận
		JPanel pnQuan = new JPanel();
		JLabel lblQuan = new JLabel("Chọn Quận:        ");
		cbo = new JComboBox<String>();
		cbo.setPreferredSize(new Dimension(158, 28));
		cbo.addItem("FFSE1701");

		cbo.addItem("FFSE1702");

		cbo.addItem("FFSE1703");

		cbo.addItem("FFSE1704");
		pnQuan.add(lblQuan);
		pnQuan.add(cbo);
		Content.add(pnQuan);

		// Tạo JComboBox chứ các Phường

		JPanel pnPhuong = new JPanel();
		JLabel lblPhuong = new JLabel("Chọn Phường:   ");
		cbo = new JComboBox<String>();
		cbo.setPreferredSize(new Dimension(158, 28));
		cbo.addItem("FFSE1701");
		cbo.addItem("FFSE1702");
		cbo.addItem("FFSE1703");
		cbo.addItem("FFSE1704");
		pnPhuong.add(lblPhuong);
		pnPhuong.add(cbo);
		Content.add(pnPhuong);

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
		pnSouth.setPreferredSize(new Dimension(580,400));
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
		pnMain.add(CRUD);
		pnMain.add(pnActions);
		pnMain.add(pnSouth);
		this.add(pnMain);

	}

}
