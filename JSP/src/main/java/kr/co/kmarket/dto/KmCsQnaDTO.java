package kr.co.kmarket.dto;

import java.util.ArrayList;
import java.util.List;

import kr.co.kmarket.db.Utils;

public class KmCsQnaDTO {
	
	private int qnaNo;
	private int cate1;
	private int cate2;
	private String title;
	private String content;
	private String file1;
	private String file2;
	private String file3;
	private String file4;
	private String writer;
	private String ordNo; //null 값 넣기 위해 String으로 변경 
	private String prodNo;
	private int parent;
	private int answerComplete;
	private String regip;
	private String rdate;
	
	//추가 필드
	private String writerName;
	private String c1Name;
	private String c2Name;
	private String writerMarking;
	private String rdateSub;
	public String getExtension(String fileName) {
		String ext = fileName;
		return ext;
	}
	public String getRdateSub() {
		return rdateSub;
	}
	public void setRdateSub(String rdateSub) {
		this.rdateSub = rdateSub;
	}
	private List<String> file = new ArrayList<>();
	
	
	public List<String> getFile() {
		return file;
	}
	public String getWriterMarking() {
		return writerMarking;
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
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = Integer.parseInt(qnaNo);
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
		
		if(title != null) {
		String escapedStr1 = title.replaceAll("<br>", "\n");
		String escapedStr2 = escapedStr1.replaceAll("&gt;", ">");
		String escapedStr3 = escapedStr2.replaceAll("&lt;", "<");
		String escapedStr4 = escapedStr3.replaceAll("&quot;", "\"");
		String escapedStr5 = escapedStr4.replaceAll("&nbsp;", " ");
		String escapedStr6 = escapedStr5.replaceAll("&amp;", "&");
		
		this.title = escapedStr6;
		} else {
			this.title = title;
		}
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
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		if(file1 != null) {
			file.add(file1);
		}
		this.file1 = file1;
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		if(file2 != null) {
			file.add(file2);
		}
		this.file2 = file2;
	}
	public String getFile3() {
		file.add(file3);
		return file3;
	}
	public void setFile3(String file3) {
		if(file3 != null) {
			file.add(file3);
		}
		this.file3 = file3;
	}
	public String getFile4() {
		return file4;
	}
	public void setFile4(String file4) {
		if(file4 != null) {
			file.add(file4);
		}
		this.file4 = file4;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writerMarking = Utils.marking(writer, 3);
		this.writer = writer;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public void setParent(String parent) {
		if(parent == null) {
			this.parent = 0;
		}else {
			this.parent = Integer.parseInt(parent);
		}
	}
	public int getAnswerComplete() {
		return answerComplete;
	}
	public void setAnswerComplete(int answerComplete) {
		this.answerComplete = answerComplete;
	}

	public void setAnswerComplete(String answerComplete) {
		if(answerComplete == null) {
			this.answerComplete = 0;
		} else {
			this.answerComplete = Integer.parseInt(answerComplete);
		}
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
		return "KmCsQnaDTO [qnaNo=" + qnaNo + ", cate1=" + cate1 + ", cate2=" + cate2 + ", title=" + title
				+ ", content=" + content + ", file1=" + file1 + ", file2=" + file2 + ", file3=" + file3 + ", file4="
				+ file4 + ", writer=" + writer + ", ordNo=" + ordNo + ", prodNo=" + prodNo + ", parent=" + parent
				+ ", answerComplete=" + answerComplete + ", regip=" + regip + ", rdate=" + rdate + ", writerName="
				+ writerName + ", c1Name=" + c1Name + ", c2Name=" + c2Name + ", writerMarking=" + writerMarking
				+ ", rdateSub=" + rdateSub + ", file=" + file + "]";
	}
	

	
	
}
