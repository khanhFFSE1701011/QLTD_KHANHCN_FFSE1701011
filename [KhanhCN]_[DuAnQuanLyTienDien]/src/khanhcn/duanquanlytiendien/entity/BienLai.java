package khanhcn.duanquanlytiendien.entity;

public class BienLai {
	private String mactBL;
	private String ngayNhapBL;
	private String thangNhapBL;
	private String namNhapBL;
	private String chuKyBL;
	private int chiSoCTMoi;
	private int tienDien;

	public BienLai(String mactBL, String ngayNhapBL, String thangNhapBL, String namNhapBL, String chuKyBL,
			int chiSoCTMoi, int tienDien) {
		super();
		this.mactBL = mactBL;
		this.ngayNhapBL = ngayNhapBL;
		this.thangNhapBL = thangNhapBL;
		this.namNhapBL = namNhapBL;
		this.chuKyBL = chuKyBL;
		this.chiSoCTMoi = chiSoCTMoi;
		this.tienDien = tienDien;
	}

	public String getMactBL() {
		return mactBL;
	}

	public void setMactBL(String mactBL) {
		this.mactBL = mactBL;
	}

	public String getNgayNhapBL() {
		return ngayNhapBL;
	}

	public void setNgayNhapBL(String ngayNhapBL) {
		this.ngayNhapBL = ngayNhapBL;
	}

	public String getThangNhapBL() {
		return thangNhapBL;
	}

	public void setThangNhapBL(String thangNhapBL) {
		this.thangNhapBL = thangNhapBL;
	}

	public String getNamNhapBL() {
		return namNhapBL;
	}

	public void setNamNhapBL(String namNhapBL) {
		this.namNhapBL = namNhapBL;
	}

	public String getChuKyBL() {
		return chuKyBL;
	}

	public void setChuKyBL(String chuKyBL) {
		this.chuKyBL = chuKyBL;
	}

	public int getChiSoCTMoi() {
		return chiSoCTMoi;
	}

	public void setChiSoCTMoi(int chiSoCTMoi) {
		this.chiSoCTMoi = chiSoCTMoi;
	}

	public int getTienDien() {
		return tienDien;
	}

	public void setTienDien(int tienDien) {
		this.tienDien = tienDien;
	}

}
