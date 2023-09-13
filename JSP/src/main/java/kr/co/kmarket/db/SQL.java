package kr.co.kmarket.db;

import java.util.ArrayList;
import java.util.List;

public class SQL {

    //km_member
	public static final String INSERT_MEMBER = "INSERT INTO `km_member` SET " //판매자 SQL문 따로 만들 것
												+ "`uid`=?,"
												+ "`pass`=SHA2(?, 256),"
												+ "`name`=?,"
												+ "`gender`=?,"
												+ "`hp`=?,"
												+ "`email`=?,"
												+ "`type`=?,"
												+ "`point`=?,"
												+ "`level`=?,"
												+ "`zip`=?,"
												+ "`addr1`=?,"
												+ "`addr2`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
												
												
			
	public static final String SELECT_MEMBER = "SELECT * FROM `km_member` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
    //km_member_point

    //km_member_terms
	public static final String SELECT_TERMS = "SELECT * FROM `km_member_terms`";
	
    //km_product


	public static final List<String> SELECT_PRODUCTS_CATE_L10 = new ArrayList<>();
	/*
     * 조회 쿼리 통합
     *
     * 조건 및 정렬방식 입력시 - 카테고리에 맞는 상품을 조건과 정렬방식을 활용해 DB에서 가져옴.
     *
     * condition = 어떤 조건으로 정렬할 것인지
     * 조건(sold: 판매건수, price:상품가격, score:상품평점, review:상품리뷰, rDate:등록날짜)
     * sort = 정렬 방식
     * 높은순(0), 낮은순(1)
     * */

	public static void changeSelectProductCateL10(String condition, String sort){
        SELECT_PRODUCTS_CATE_L10.clear();
        SELECT_PRODUCTS_CATE_L10.add("SELECT a.*, avg(b.rating) as rating FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE prodCate1=? and prodCate2 = ? and stock>0 group by a.prodNo ORDER BY "+condition+" "+sort+", prodNo DESC LIMIT ?, 10;");
    }
	/*
	조건 입력시 - 조건에 해당하는 상품만 조회함.
	condition = 검색조건
	 */
	public static void changeSelectProductCateL10(String condition){
        SELECT_PRODUCTS_CATE_L10.clear();
        SELECT_PRODUCTS_CATE_L10.add("SELECT * FROM Kmarket.km_product as a LEFT JOIN Kmarket.km_product_review WHERE "+condition+"=? and stock>0 ORDER BY prodNo DESC LIMIT ?, 10;");
    }
    /*
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY sold DESC ,prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY sold ASC ,prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY price DESC, prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY price ASC,prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY score DESC, prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY score ASC, prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY review DESC, prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY review ASC, prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY rdate DESC, prodNo DESC LIMIT ?, 10;";
    SELECT_PRODUCT_CATE_L10="SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY rdate ASC, prodNo DESC LIMIT ?, 10;";*/



	public static final String SELECT_PRODUCTS_COUNT_CATE = "SELECT COUNT(prodNo) FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? and stock>0;";


	public final static String INSERT_PRODUCT = "INSERT INTO `km_product` SET  "
			+ "`prodCate1`=?,"
			+ "`prodCate2`=?,"
			+ "`prodName`=?,"
			+ "`descript`=?,"
			+ "`company`=?,"
			+ "`price`=?,"
			+ "`discount`=?,"
			+ "`point`=?,"
			+ "`stock`=?,"
			+ "`delivery`=?,"
			+ "`thumb1`=?,"
			+ "`thumb2`=?,"
			+ "`thumb3`=?,"
			+ "`detail`=?,"
			+ "`status`=?,"
			+ "`duty`=?,"
			+ "`receipt`=?,"
			+ "`bizType`=?,"
			+ "`origin`=?";

	public final static String SELECT_PRODUCT				= "SELECT a.*, AVG(b.rating) as rating FROM `km_product` as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE a.`prodNo` =?";
	public final static String SELECT_PRODUCTS_ALL_L10 			= "SELECT * FROM `km_product` WHERE `stock` > 0 LIMIT ?,10";
	public final static String SELECT_PRODUCTS_CATE_L10_ADMIN 		= "SELECT * FROM `km_product` WHERE `stock` > 0 AND `cate`=? LIMIT ?,10";
	public final static String SELECT_COUNT_PRODUCTS_ALL 	= "SELECT COUNT(*) `km_product` WHERE `stock` > 0";
	public final static String SELECT_COUNT_PRODUCTS_CATE 	= "SELECT COUNT(*) `km_product` WEHRE `stock` > 0 AND `cate`=?";


	//km_product_cart

	//km_product_cate1

	//km_product_cate2

	//km_product_order

	//km_product_order_item

	//km_product_review
	public static final String SELECT_PRODUCT_REVIEWS_L5 = "SELECT * FROM km_product_review where prodNo=? LIMIT ?,5;";
	public static final String SELECT_COUNT_REVIEWS_PNO = "SELECT COUNT(revNo) FROM Kmarket.km_product_review WHERE prodNo = ?;";
}
