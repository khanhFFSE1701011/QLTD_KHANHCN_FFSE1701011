package khanhcn.duanquanlytiendien.entity;

public class KhachHang {
	private String maKH;
	private String hoTen;
	private String diaChi;
	private String phuong;
	private String quan;
	private String dienThoai;
	private String email;
	private String maCT;

	public KhachHang(String maKH, String hoTen, String diaChi, String maCT, String phuong, String quan,
			String dienThoai, String email) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.phuong = phuong;
		this.quan = quan;
		this.dienThoai = dienThoai;
		this.email = email;
		this.maCT = maCT;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getPhuong() {
		return phuong;
	}

	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}

	public String getQuan() {
		return quan;
	}

	public void setQuan(String quan) {
		this.quan = quan;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaCT() {
		return maCT;
	}

	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}

}