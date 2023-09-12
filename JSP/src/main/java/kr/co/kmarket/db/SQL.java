package kr.co.kmarket.db;

public class SQL {

    //km_member
	public static final String SELECT_MEMBER = "SELECT * FROM `km_member` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
    //km_member_point

    //km_member_terms
	public static final String SELECT_TERMS = "SELECT * FROM `km_member_terms`";
	
    //km_product

    //km_product_cart

    //km_product_cate1

    //km_product_cate2

    //km_product_order

    //km_product_order_item

    //km_product_review


}
