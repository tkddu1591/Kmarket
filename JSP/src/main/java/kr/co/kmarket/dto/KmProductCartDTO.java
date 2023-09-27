package kr.co.kmarket.dto;

public class KmProductCartDTO {

    private int cartNo;
    private String  uid;
    private int prodNo;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;
    private String rDate;

    private String prodName;
    private String descript;
    private String thumb1;

    public String getThumb1() {
        return thumb1;
    }

    public void setThumb1(String thumb1) {
        this.thumb1 = thumb1;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getCartNo() {
        return cartNo;
    }

    public void setCartNo(int cartNo) {
        this.cartNo = cartNo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
    public String getPriceWithComma() {
        return String.format("%,d", price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }
    public String getDiscountWithComma() {
        return String.format("%,d", discount);
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPoint() {
        return point;
    }
    public String getPointWithComma() {
        return String.format("%,d", point);
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getDelivery() {
        return delivery;
    }
    public String getDeliveryWithComma() {
        return String.format("%,d", delivery);
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getTotal() {
        return total;
    }
    public String getTotalWithComma() {
        return String.format("%,d", total);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    @Override
    public String toString() {
        return cartNo+","+uid+","+prodNo+","+count+","+price+","+discount+","+delivery+","+point+","+total+","+rDate+","+thumb1+","+prodName+","+descript;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = Integer.parseInt(prodNo);
    }

    public void setCount(String count) {
        this.count = Integer.parseInt(count);
    }

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
    }

    public void setTotal(String total) {
        this.total = Integer.parseInt(total);
    }

    public void setDelivery(String delivery) {
        this.delivery = Integer.parseInt(delivery);
    }

    public void setPoint(String point) {
        this.point = Integer.parseInt(point);
    }

    public void setDiscount(String discount) {
        this.discount = Integer.parseInt(discount);
    }

}
