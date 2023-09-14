package kr.co.kmarket.dto;

public class KmProductReviewDTO {
    private int revNo;
    private int prodNo;
    private String content;
    private String uid;
    private int rating;
    private String regIp;
    private String rDate;

    public int getRevNo() {
        return revNo;
    }

    public void setRevNo(int revNo) {
        this.revNo = revNo;
    }

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }
    public String getUidHidden() {
        String hidden = uid.substring(0,3);
        for(int i = 0; i <uid.length()-3; i++) {
            hidden += "*";
        }
        return hidden;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getrDate() {
        return rDate;
    }

    public String getrDateYMD() {
        return rDate.substring(0,10);
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }
}
