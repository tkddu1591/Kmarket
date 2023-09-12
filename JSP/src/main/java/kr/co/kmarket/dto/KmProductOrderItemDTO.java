package kr.co.kmarket.dto;

public class KmProductOrderItemDTO {
    private int ordNo;
    private int prodNo;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;

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

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
