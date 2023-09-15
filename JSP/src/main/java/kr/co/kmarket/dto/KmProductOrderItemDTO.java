package kr.co.kmarket.dto;

import java.text.DecimalFormat;

public class KmProductOrderItemDTO {
    private String descript;
    private String ProdName;
    private int ordNo;
    private int prodNo;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }

    public int getOrdNo() {
        return ordNo;
    }

    public void setOrdNo(int ordNo) {
        this.ordNo = ordNo;
    }

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }
    public String  getPriceWithComma() {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getDelivery() {
        return delivery;
    }
    public String getDeliveryWithComma() {

        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(delivery);
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getTotal() {
        return total;
    }

    public String getTotalWithComma() {

        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(total);

    }

    public void setTotal(int total) {
        this.total = total;
    }
}
