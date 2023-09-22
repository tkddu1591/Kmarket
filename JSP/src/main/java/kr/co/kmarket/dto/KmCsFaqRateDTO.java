package kr.co.kmarket.dto;

public class KmCsFaqRateDTO {
	private int faqNo;
	private String uid;
	private int rate;
	public int getFaqNo() {
		return faqNo;
	}
	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}
	public void setFaqNo(String faqNo) {
		this.faqNo = Integer.parseInt(faqNo);
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public void setRate(String rate) {
		this.rate = Integer.parseInt(rate);
	}
	
	
}
