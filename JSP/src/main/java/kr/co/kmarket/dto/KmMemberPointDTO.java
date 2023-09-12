package kr.co.kmarket.dto;

public class KmMemberPointDTO {
    private int pointNo;
    private String uid;
    private int orderNo;
    private int point;
    private String pointDate;

    public int getPointNo() {
        return pointNo;
    }

    public void setPointNo(int pointNo) {
        this.pointNo = pointNo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getPointDate() {
        return pointDate;
    }

    public void setPointDate(String pointDate) {
        this.pointDate = pointDate;
    }
}
