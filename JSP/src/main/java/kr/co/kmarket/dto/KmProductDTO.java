package kr.co.kmarket.dto;

import java.io.File;
import java.util.*;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class KmProductDTO {
    private int prodNo;
    private int prodCate1;
    private int prodCate2;
    private String prodName;
    private String descript;
    private String company;
    private String seller;
    private int price;
    private int discount;
    private int point;
    private int stock;
    private int sold;
    private int delivery;
    private int hit;
    private int score;
    private int review;
    private String thumb1;
    private String thumb2;
    private String thumb3;
    private String detail;
    private String status;
    private String duty;
    private String receipt;
    private String bizType;
    private String origin;
    private String ip;
    private String rDate;
    private int etc1;
    private int etc2;
    private String etc3;
    private String etc4;
    private String etc5;

    private String wDate;
    private int total;
    private int rating;

    private int discountPrice;

    private String path;
    private int level;
    private List<String> file = new ArrayList<>();
	
	
	public List<String> getFile() {
		return file;
	}
    
    public KmProductDTO(String path) {
        this.path = path;
    }

    public KmProductDTO() {
    }

    private String c1Name;
    private String c2Name;

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

    public int getDiscountPrice() {
        return discountPrice;
    }

    public int getDiscountPrice(int price, int discount) {
        return (price * (100 - discount)) / 100;
    }

    public String getDiscountPriceWithComma() {
        return String.format("%,d", (price * (100 - discount)) / 100);
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setDiscountPrice(int price, int discount) {
        this.discountPrice = (price * (100 - discount)) / 100;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTotal(String total) {
        this.total = Integer.parseInt(total);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotal(int discountPrice, int delivery) {
        this.total = (discountPrice);
    }

    public int getTotal() {
        return total;
    }

    public int getTotal(int discountPrice, int delivery) {
        return (discountPrice + delivery);
    }

    public String getTotalWithComma() {
        return String.format("%,d", total);
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

    public int getProdCate1() {
        return prodCate1;
    }

    public void setProdCate1(int prodCate1) {
        this.prodCate1 = prodCate1;
    }

    public void setProdCate1(String prodCate1) {
        this.prodCate1 = Integer.parseInt(prodCate1);
    }

    public int getProdCate2() {
        return prodCate2;
    }

    public void setProdCate2(int prodCate2) {
        this.prodCate2 = prodCate2;
    }

    public void setProdCate2(String prodCate2) {
        this.prodCate2 = Integer.parseInt(prodCate2);
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
    }

    public int getDiscount() {
        return discount;
    }

    public String getDiscountWithPer() {
        return discount + "%";
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStock(String stock) {
        this.stock = Integer.parseInt(stock);
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
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

    public void setDelivery(String delivery) {
        this.delivery = Integer.parseInt(delivery);
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getThumb1() {
        return thumb1;
    }

    public void setThumb1(String thumb1) {
    	if(thumb1 != null) {
			file.add(thumb1);
		}
        this.thumb1 = thumb1;
    }

    public void setThumb1ForRename(String thumb1) {
        this.thumb1 = fileRename(thumb1);
    }

    public String getThumb2() {
        return thumb2;
    }

    public void setThumb2(String thumb2) {
    	if(thumb2 != null) {
			file.add(thumb2);
		}
        this.thumb2 = thumb2;
    }

    public void setThumb2ForRename(String thumb2) {
        this.thumb2 = fileRename(thumb2);
    }

    public String getThumb3() {
        return thumb3;
    }

    public void setThumb3(String thumb3) {

    	if(thumb3 != null) {
			file.add(thumb3);
		}
        this.thumb3 = thumb3;
    }

    public void setThumb3ForRename(String thumb3) {
        this.thumb3 = fileRename(thumb3);
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {

    	if(detail != null) {
			file.add(detail);
		}
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getwDate() {
        return wDate;
    }

    public void setwDate(String wDate) {
        this.wDate = wDate;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public int getEtc1() {
        return etc1;
    }

    public void setEtc1(int etc1) {
        this.etc1 = etc1;
    }

    public int getEtc2() {
        return etc2;
    }

    public void setEtc2(int etc2) {
        this.etc2 = etc2;
    }

    public String getEtc3() {
        return etc3;
    }

    public void setEtc3(String etc3) {
        this.etc3 = etc3;
    }

    public String getEtc4() {
        return etc4;
    }

    public void setEtc4(String etc4) {
        this.etc4 = etc4;
    }

    public String getEtc5() {
        return etc5;
    }

    public void setEtc5(String etc5) {
        this.etc5 = etc5;
    }

    public String fileRename(String thumb) {
        int i = thumb.lastIndexOf(".");
        String ext = thumb.substring(i);

        String uuid = UUID.randomUUID().toString();
        String sName = uuid + ext;

        File f1 = new File(path + "/" + thumb);
        File f2 = new File(path + "/" + sName);
        f1.renameTo(f2);

        return sName;
    }

    @Override
    public String toString() {
        return "KmProductDTO [prodNo=" + prodNo + ", prodCate1=" + prodCate1 + ", prodCate2=" + prodCate2
                + ", prodName=" + prodName + ", descript=" + descript + ", company=" + company + ", seller=" + seller
                + ", price=" + price + ", discount=" + discount + ", point=" + point + ", stock=" + stock + ", sold="
                + sold + ", delivery=" + delivery + ", hit=" + hit + ", score=" + score + ", review=" + review
                + ", thumb1=" + thumb1 + ", thumb2=" + thumb2 + ", thumb3=" + thumb3 + ", detail=" + detail
                + ", status=" + status + ", duty=" + duty + ", receipt=" + receipt + ", bizType=" + bizType
                + ", origin=" + origin + ", ip=" + ip + ", rDate=" + rDate + ", etc1=" + etc1 + ", etc2=" + etc2
                + ", etc3=" + etc3 + ", etc4=" + etc4 + ", etc5=" + etc5 + ", wDate=" + wDate + ", total=" + total
                + ", rating=" + rating + ", discountPrice=" + discountPrice + ", path=" + path + "]";
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

