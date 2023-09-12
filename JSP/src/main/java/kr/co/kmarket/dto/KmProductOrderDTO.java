package kr.co.kmarket.dto;

public class KmProductOrderDTO {
    private int ordNo;
    private String ordUid;
    private int ordCount;
    private int ordPrice;
    private int ordDiscount;
    private int ordDelivery;
    private int savePoint;
    private int usedPoint;
    private String recIpName;
    private String recIpHp;
    private String recIpAddr1;
    private String recIpAddr2;
    private int ordPayment;
    private int ordComplete;
    private String ordDate;

    public int getOrdNo() {
        return ordNo;
    }

    public void setOrdNo(int ordNo) {
        this.ordNo = ordNo;
    }

    public String getOrdUid() {
        return ordUid;
    }

    public void setOrdUid(String ordUid) {
        this.ordUid = ordUid;
    }

    public int getOrdCount() {
        return ordCount;
    }

    public void setOrdCount(int ordCount) {
        this.ordCount = ordCount;
    }

    public int getOrdPrice() {
        return ordPrice;
    }

    public void setOrdPrice(int ordPrice) {
        this.ordPrice = ordPrice;
    }

    public int getOrdDiscount() {
        return ordDiscount;
    }

    public void setOrdDiscount(int ordDiscount) {
        this.ordDiscount = ordDiscount;
    }

    public int getOrdDelivery() {
        return ordDelivery;
    }

    public void setOrdDelivery(int ordDelivery) {
        this.ordDelivery = ordDelivery;
    }

    public int getSavePoint() {
        return savePoint;
    }

    public void setSavePoint(int savePoint) {
        this.savePoint = savePoint;
    }

    public int getUsedPoint() {
        return usedPoint;
    }

    public void setUsedPoint(int usedPoint) {
        this.usedPoint = usedPoint;
    }

    public String getRecIpName() {
        return recIpName;
    }

    public void setRecIpName(String recIpName) {
        this.recIpName = recIpName;
    }

    public String getRecIpHp() {
        return recIpHp;
    }

    public void setRecIpHp(String recIpHp) {
        this.recIpHp = recIpHp;
    }

    public String getRecIpAddr1() {
        return recIpAddr1;
    }

    public void setRecIpAddr1(String recIpAddr1) {
        this.recIpAddr1 = recIpAddr1;
    }

    public String getRecIpAddr2() {
        return recIpAddr2;
    }

    public void setRecIpAddr2(String recIpAddr2) {
        this.recIpAddr2 = recIpAddr2;
    }

    public int getOrdPayment() {
        return ordPayment;
    }

    public void setOrdPayment(int ordPayment) {
        this.ordPayment = ordPayment;
    }

    public int getOrdComplete() {
        return ordComplete;
    }

    public void setOrdComplete(int ordComplete) {
        this.ordComplete = ordComplete;
    }

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }
}
