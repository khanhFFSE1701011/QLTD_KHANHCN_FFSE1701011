package khanhcn.duanquanlytiendien.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.gjt.mm.mysql.Driver;

import khanhcn.duanquanlytiendien.entity.KhachHang;
import khanhcn.duanquanlytiendien.entity.QuanPhuong;

public class QuanLyTienDienDAO {

	static Connection conn = null;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public com.mysql.jdbc.Connection getConnect(String strServer, String strDatabase, String strUser, String strPwd) {
		String strConnect = "jdbc:mysql://" + strServer + "/" + strDatabase
				+ "?useUnicode=true&characterEncoding=utf-8";
		Properties pro = new Properties();
		pro.put("user", strUser);
		pro.put("password", strPwd);
		try {
			com.mysql.jdbc.Driver driver = new Driver();
			conn = driver.connect(strConnect, pro);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return (com.mysql.jdbc.Connection) conn;
	}

	public ArrayList<QuanPhuong> getQuanKH() {
		ArrayList<QuanPhuong> listPhuong = new ArrayList<QuanPhuong>();
		try {
			String sql = "select id,tenquan from quan WHERE id>=1 AND id<=7";
			PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet result = stm.executeQuery();

			while (result.next()) {
				QuanPhuong quan = new QuanPhuong();
				quan.setId(result.getInt(1));
				quan.setTen(result.getString(2));
				listPhuong.add(quan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPhuong;
	}

	public ArrayList<QuanPhuong> getPhuongKH(int idQuan) {
		ArrayList<QuanPhuong> listPhuong = new ArrayList<>();
		try {
			String sql = "select tenphuong, idphuong from phuong where idquan = '" + idQuan + "'";
			PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet result = stm.executeQuery();
			while (result.next()) {
				QuanPhuong phuong = new QuanPhuong();
				phuong.setTen(result.getString(1));
				phuong.setId(result.getInt(2));
				listPhuong.add(phuong);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return listPhuong;
	}

	public static String getNameQuan(int id) {
		String Quan = "";
		try {
			String sql = "SELECT * from quan WHERE id = '" + id + "'";
			PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Quan = rs.getString("tenquan");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Quan;
	}

	public static String getNamePhuong(int id) {
		String Quan = "";
		try {
			String sql = "SELECT * from phuong WHERE idphuong = '" + id + "'";
			PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Quan = rs.getString("tenphuong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Quan;
	}

	public ArrayList<QuanPhuong> getPhuongKH() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<KhachHang> getDSKhachHang() {

		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();

		try {
			// String queryString = "SELECT khanhhang.makh, khanhhang.tenkh,
			// khanhhang.diachi, khanhhang.mact, phuong.tenphuong, quan.tenquan,
			// khanhhang.dienthoai,khanhhang.email\r\n" +
			// "FROM khanhhang\r\n" +
			// "INNER JOIN phuong\r\n" +
			// "ON (khanhhang.phuong = phuong.idquan)\r\n" +
			// "INNER JOIN quan\r\n" +
			// "ON (khanhhang.quan = quan.id)\r\n" +
			// "";
			String queryString = "SELECT * FROM khanhhang";
			PreparedStatement statement = conn.prepareStatement(queryString);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				String maKH = result.getString("makh");
				String tenKH = result.getString("tenkh");
				String diaChi = result.getString("diachi");
				String maCT = result.getString("mact");
				int quan = result.getInt("quan");
				int phuong = result.getInt("phuong");
				String dienThoai = result.getString("dienthoai");
				String email = result.getString("email");

				dsKH.add(new KhachHang(maKH, tenKH, diaChi, maCT, quan, phuong, dienThoai, email));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsKH;
	}

	// Thêm Khach hang
	public void add(KhachHang kh) {
		try {
			String sql = "insert into khanhhang(makh,tenkh, diachi, mact, phuong, quan, dienthoai, email) values(?,?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, kh.getMaKH());
			statement.setString(2, kh.getHoTen());
			statement.setString(3, kh.getDiaChi());
			statement.setString(4, kh.getMaCT());
			statement.setInt(5, kh.getPhuong());
			statement.setInt(6, kh.getQuan());
			statement.setString(7, kh.getDienThoai());
			statement.setString(8, kh.getEmail());

			int x = statement.executeUpdate();
			if (x > 0) {
				JOptionPane.showMessageDialog(null, "Thêm thành công ! ");
			} else {
				JOptionPane.showMessageDialog(null, "Thêm thất bại ! ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Xóa khách hàng
	public static boolean delete(KhachHang kh) {
		try {
			String sql = "delete from khanhhang where makh=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, kh.getMaKH());

			int x = statement.executeUpdate();
			if (x > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Edit khách hàng
	public static boolean edit(KhachHang kh) {

		try {
			String sql = "update khanhhang set tenkh=?, diachi=?, mact=?, phuong=?, quan=?, dienthoai=?, email=? WHERE makh=?";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, kh.getHoTen());
			statement.setString(2, kh.getDiaChi());
			statement.setString(3, kh.getMaCT());
			statement.setInt(4, kh.getQuan());
			statement.setInt(5, kh.getPhuong());
			statement.setString(6, kh.getDienThoai());
			statement.setString(7, kh.getEmail());
			statement.setString(8, kh.getMaKH());

			int x = statement.executeUpdate();

			if (x > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public static ResultSet getMaCT() {
		try {
			String sql = "select mact from khanhhang";
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
