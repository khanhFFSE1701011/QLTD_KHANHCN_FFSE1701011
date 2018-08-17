package khanhcn.duanquanlytiendien.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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

import com.mysql.jdbc.Connection;

import khanhcn.duanquanlytiendien.DAO.QuanLyTienDienDAO;
import khanhcn.duanquanlytiendien.entity.KhachHang;
import khanhcn.duanquanlytiendien.entity.QuanPhuong;

public class BCDSKhachHangUi extends JPanel {

	static QuanLyTienDienDAO QLTD = new QuanLyTienDienDAO();
	ArrayList<QuanPhuong> dSPhuong = new ArrayList<QuanPhuong>();
	ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
	static Connection conn = QLTD.getConnect("localhost", "khanhcn_ffse1701011", "khanhcn", "123456");
	private JComboBox<QuanPhuong> cboQuan, cboPhuong;
	DefaultTableModel dm;
	JTable tbl;
	JComboBox<String> cbo;
	JScrollPane sc;
	JPanel pnSouth;
	JButton btnThem = new JButton("      Tìm");
	
	public BCDSKhachHangUi() {
		addControl();
		addEvent();
	}

	public void  addControl() {

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

		JPanel Content = new JPanel();
		Content.setLayout(new BoxLayout(Content, BoxLayout.X_AXIS));

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
		Content.add(pnQuan);

		// Tạo JComboBox chứ các Phường

		JPanel pnPhuong = new JPanel();
		JLabel lblPhuong = new JLabel("Chọn Phường:   ");
		cboPhuong = new JComboBox();
		loadDataPhuong();
		cboPhuong.setPreferredSize(new Dimension(158, 28));
		pnPhuong.add(lblPhuong);
		pnPhuong.add(cboPhuong);
		Content.add(pnPhuong);

		// phần button CRUD
		JPanel CRUD = new JPanel();

		
		btnThem.setPreferredSize(new Dimension(100, 50));
		btnThem.setMargin(new Insets(0, 10, 0, 0));
		ImageIcon iconThem = new ImageIcon(
				new ImageIcon("img/Them.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel add = new JLabel(iconThem);
		btnThem.add(add);
		CRUD.add(btnThem);

		/*JButton btnSua = new JButton("   Sửa");
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
		pnMain.add(btnXoa);*/

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
		//pnActions.add(btnSua);
		//pnActions.add(btnXoa);
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
		pnMain.add(CRUD);
		pnMain.add(pnActions);
		pnMain.add(pnSouth);
		this.add(pnMain);

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

	public void timPhuong() {
		String timkiem = cboPhuong.getSelectedItem().toString();
		dsKH = QLTD.getDSBCKhachHang(timkiem);

		for (int i = 0; i < dsKH.size(); i++) {
			dm.addRow(new String[] { dsKH.get(i).getMaKH(), dsKH.get(i).getHoTen(), dsKH.get(i).getDiaChi(),
					dsKH.get(i).getMaCT(), QuanLyTienDienDAO.getNamePhuong(dsKH.get(i).getPhuong()),
					QuanLyTienDienDAO.getNameQuan(dsKH.get(i).getQuan()), dsKH.get(i).getEmail(),
					dsKH.get(i).getDienThoai() });
		}

	}
	
	ActionListener btnSearchClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			timPhuong();
		}
	};
	
	public void addEvent() {
	btnThem.addActionListener(btnSearchClick);
	}
}
