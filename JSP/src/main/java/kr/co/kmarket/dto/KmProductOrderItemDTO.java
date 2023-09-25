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

    private String thumb1;

    public String getThumb1() {
        return thumb1;
    }

    public void setThumb1(String thumb1) {
        this.thumb1 = thumb1;
    }


    private int discountPrice;

    public String getDiscountPriceWithComma() {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format((price*discount/100));
    }
    private int discountPriceTotal;

    public String getDiscountPriceTotalWithComma() {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(price-(price*discount/100));
    }

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

    public void setProdNo(String prodNo) {
        this.prodNo = Integer.parseInt(prodNo);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCount(String count) {
        this.count = Integer.parseInt(count);
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

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setDiscount(String discount) {
        this.discount = Integer.parseInt(discount);
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setPoint(String point) {
        this.point = Integer.parseInt(point);
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
    public void setDelivery(String delivery) {
        this.delivery = Integer.parseInt(delivery);
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

    public void setTotal(String total) {
        this.total = Integer.parseInt(total);
    }

    @Override
    public String toString() {
        return prodNo+","+count+","+price+","+discount+","+point+","+delivery+","+total;
    }

}
