package khanhcn.duanquanlytiendien.entity; 

public class BienLai {
	private String mactKH;
	private String ngayNhapCT;
	private String thangNhapCT;
	private String namNhapCT;
	private String chuKyCT;
	private int chiSoCTMoi;
	private int tienDien;
	
	public BienLai(String mactKH, String ngayNhapCT, String thangNhapCT, String namNhapCT, String chuKyCT,
			int chiSoCTMoi, int tienDien) {
		super();
		this.mactKH = mactKH;
		this.ngayNhapCT = ngayNhapCT;
		this.thangNhapCT = thangNhapCT;
		this.namNhapCT = namNhapCT;
		this.chuKyCT = chuKyCT;
		this.chiSoCTMoi = chiSoCTMoi;
		this.tienDien = tienDien;
	}

	public String getMactKH() {
		return mactKH;
	}

	public void setMactKH(String mactKH) {
		this.mactKH = mactKH;
	}

	public String getNgayNhapCT() {
		return ngayNhapCT;
	}

	public void setNgayNhapCT(String ngayNhapCT) {
		this.ngayNhapCT = ngayNhapCT;
	}

	public String getThangNhapCT() {
		return thangNhapCT;
	}

	public void setThangNhapCT(String thangNhapCT) {
		this.thangNhapCT = thangNhapCT;
	}

	public String getNamNhapCT() {
		return namNhapCT;
	}

	public void setNamNhapCT(String namNhapCT) {
		this.namNhapCT = namNhapCT;
	}

	public String getChuKyCT() {
		return chuKyCT;
	}

	public void setChuKyCT(String chuKyCT) {
		this.chuKyCT = chuKyCT;
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

