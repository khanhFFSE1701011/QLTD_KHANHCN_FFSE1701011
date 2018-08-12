package khanhcn.duanquanlytiendien.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.gjt.mm.mysql.Driver;

import com.mysql.jdbc.Statement;
import khanhcn.duanquanlytiendien.entity.KhachHang;

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

	public static ResultSet getQuan() {
		try {
			String sql = "select tenquan from Quan";
			Statement statement = (Statement) conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet getPhuong(int idquan) {
		try {
			String sql = "select tenphuong from Phuong where idquan = ?";
			PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
			stm.setInt(1, idquan);
			ResultSet result = stm.executeQuery();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<KhachHang> getDSKhachHang() {

		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();

		try {
			String queryString = "SELECT * FROM khanhhang";
			PreparedStatement statement = conn.prepareStatement(queryString);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				String maKH = result.getString("makh");
				String tenKH = result.getString("tenkh");
				String diaChi = result.getString("diachi");
				String maCT = result.getString("mact");
				String phuong = result.getString("phuong");
				String quan = result.getString("quan");
				String dienThoai = result.getString("dienthoai");
				String email = result.getString("email");

				dsKH.add(new KhachHang(maKH, tenKH, diaChi, maCT, phuong, quan, dienThoai, email));
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
			statement.setString(5, kh.getPhuong());
			statement.setString(6, kh.getQuan());
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
			statement.setString(4, kh.getQuan());
			statement.setString(5, kh.getPhuong());
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
