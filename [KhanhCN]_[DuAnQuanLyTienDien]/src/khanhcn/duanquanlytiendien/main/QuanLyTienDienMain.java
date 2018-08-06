package khanhcn.duanquanlytiendien.main;

import java.sql.SQLException;

import khanhcn.duanquanlytiendien.ui.KhachHangUi;

public class QuanLyTienDienMain {

	public static void main(String[] args) throws SQLException {
		KhachHangUi QLTD = new KhachHangUi("CHƯƠNG TRÌNH QUẢN LÝ TIỀN ĐIỆN");
		QLTD.showWindow();
		/*BienLaiUi myBienLai = new BienLaiUi("aa");
		myBienLai.showWindowBL();*/


	}

}
