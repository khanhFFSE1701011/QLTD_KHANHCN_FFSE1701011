package khanhcn.duanquanlytiendien.entity;

public class QuanPhuong {
	private int id;
	private String ten;


	public String toString() {
		return ten;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public QuanPhuong(int id, String ten) {
		super();
		this.id = id;
		this.ten = ten;
	}


	public QuanPhuong() {
	}


	public String getTen() {
		return ten;
	}


	public void setTen(String ten) {
		this.ten = ten;
	}
}
