package kr.co.kmarket.dto;

public class KmCsNoticeDTO {
	private int noticeNo;
	private int cate1;
	private int cate2;
	private String title;
	private String content;
	private String writer;
	private String regip;
	private String rdate;

	private int hit;
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public void setHit(String hit) {
		this.hit = Integer.parseInt(hit);
	}
	
	//추가필드 
	private String c1Name;
	private String c2Name;
	private String rdateSub;
	
	
	public String getRdateSub() {
		return rdateSub;
	}
	public void setRdateSub(String rdateSub) {
		this.rdateSub = rdateSub;
	}
	public String getC1Name() {
		return c1Name;
	}
	public void setC1Name(String c1Name) {
		this.c1Name = c1Name;
	}
	public String getC2Name() {
		return c2Name;
	}
	public void setC2Name(String c2Name) {
		this.c2Name = c2Name;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNO) {
		this.noticeNo = noticeNO;
	}
	public void setNoticeNo(String noticeNO) {
		this.noticeNo = Integer.parseInt(noticeNO);
	}
	public int getCate1() {
		return cate1;
	}
	public void setCate1(int cate1) {
		this.cate1 = cate1;
	}
	public void setCate1(String cate1) {
		this.cate1 = Integer.parseInt(cate1);
	}
	public int getCate2() {
		return cate2;
	}
	public void setCate2(int cate2) {
		this.cate2 = cate2;
	}
	public void setCate2(String cate2) {
		this.cate2 = Integer.parseInt(cate2);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		String escapedStr1 = title.replaceAll("<br>", "\n");
		String escapedStr2 = escapedStr1.replaceAll("&gt;", ">");
		String escapedStr3 = escapedStr2.replaceAll("&lt;", "<");
		String escapedStr4 = escapedStr3.replaceAll("&quot;", "\"");
		String escapedStr5 = escapedStr4.replaceAll("&nbsp;", " ");
		String escapedStr6 = escapedStr5.replaceAll("&amp;", "&");
		
		this.title = escapedStr6;
	}
	public String getContent() {
		String escapedStr1 = content.replaceAll("\n", "<br>");

		String escapedStr2 = escapedStr1.replaceAll("&gt;", ">");
		String escapedStr3 = escapedStr2.replaceAll("&lt;", "<");
		String escapedStr4 = escapedStr3.replaceAll("&quot;", "\"");
		String escapedStr5 = escapedStr4.replaceAll("&nbsp;", " ");
		String escapedStr6 = escapedStr5.replaceAll("&amp;", "&");
		/*
		String escapedStr2 = escapedStr1.replaceAll(">", "&gt;");
		String escapedStr3 = escapedStr2.replaceAll("<", "&lt;");
		String escapedStr4 = escapedStr3.replaceAll("\"", "&quot;");
		String escapedStr5 = escapedStr4.replaceAll(" ", "&nbsp;");
		String escapedStr6 = escapedStr5.replaceAll("&", "&amp;");
		*/
		this.content = escapedStr6;
		return content;
	}
	public String getContentTextArea() {
		String escapedStr1 = content.replaceAll("<br>", "\n");
		String escapedStr2 = escapedStr1.replaceAll("&gt;", ">");
		String escapedStr3 = escapedStr2.replaceAll("&lt;", "<");
		String escapedStr4 = escapedStr3.replaceAll("&quot;", "\"");
		String escapedStr5 = escapedStr4.replaceAll("&nbsp;", " ");
		String escapedStr6 = escapedStr5.replaceAll("&amp;", "&");
		this.content = escapedStr6;
		return content;
	}
	public void setContent(String content) {
		String escapedStr1 = content.replaceAll("<br>", "\n");
		String escapedStr2 = escapedStr1.replaceAll("&gt;", ">");
		String escapedStr3 = escapedStr2.replaceAll("&lt;", "<");
		String escapedStr4 = escapedStr3.replaceAll("&quot;", "\"");
		String escapedStr5 = escapedStr4.replaceAll("&nbsp;", " ");
		String escapedStr6 = escapedStr5.replaceAll("&amp;", "&");
		
		this.content = escapedStr6;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
		this.rdateSub = rdate.substring(2, 10).replace('-', '.');
	}
	@Override
	public String toString() {
		return "KmCsNoticeDTO [noticeNo=" + noticeNo + ", cate1=" + cate1 + ", cate2=" + cate2 + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", regip=" + regip + ", rdate=" + rdate + ", hit="
				+ hit + ", c1Name=" + c1Name + ", c2Name=" + c2Name + ", rdateSub=" + rdateSub + "]";
	}
	
	
}
