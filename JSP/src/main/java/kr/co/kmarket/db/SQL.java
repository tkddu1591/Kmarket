package kr.co.kmarket.db;

import java.util.ArrayList;
import java.util.List;

public class SQL {

    //---------------------------km_member-------------------------
	public static final String INSERT_MEMBER = "INSERT INTO `km_member` SET " 
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
												+ "`company`=?,"
												+ "`ceo`=?,"
												+ "`bizRegNum`=?,"
												+ "`comRegNum`=?,"
												+ "`tel`=?,"
												+ "`manager`=?,"
												+ "`managerHp`=?,"
												+ "`fax`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";



	public static final String SELECT_MEMBER = "SELECT * FROM `km_member` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String SELECT_MEMBER_BY_NAME_AND_EMAIL = "SELECT * FROM `km_member` WHERE `name`=? AND `email`=?";
	
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `km_member` WHERE `uid`=?";
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `km_member` WHERE `hp`=?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `km_member` WHERE `email`=?";
	public static final String SELECT_COUNT_NAME_AND_EMAIL = "SELECT COUNT(*) FROM `km_member` WHERE `name`=? AND `email`=?";
	
	
    //----------------------------km_member_point-----------------------

    //----------------------------km_member_terms-----------------------
	public static final String SELECT_TERMS = "SELECT * FROM `km_member_terms`";

    //------------------------------km_product----------------------------


	public static final List<String> SELECT_PRODUCTS_CATE_L10 = new ArrayList<>();



	/*
     * 조회 쿼리 통합
     *
     * 조건 및 정렬방식 입력시 - 카테고리에 맞는 상품을 조건과 정렬방식을 활용해 DB에서 가져옴.
     *
     * condition = 어떤 조건으로 정렬할 것인지
     * 조건(sold: 판매건수, price:상품가격, score:상품평점, review:상품리뷰, rDate:등록날짜)
     * sort = 정렬 방식
     * 높은순(0), 낮은순(1), 
     * */

	public static void changeSelectProductCateL10(String condition, String sort, String cate2){
        SELECT_PRODUCTS_CATE_L10.clear();
        if(!sort.isEmpty()&& sort!=null) {
        	SELECT_PRODUCTS_CATE_L10.add("SELECT a.*, avg(b.rating) as rating FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE prodCate1=? and prodCate2 = ? and stock>0 group by a.prodNo ORDER BY "+condition+" "+sort+", prodNo DESC LIMIT ?, 10;");
        }else {
        	SELECT_PRODUCTS_CATE_L10.add("SELECT * FROM Kmarket.km_product as a LEFT JOIN Kmarket.km_product_review WHERE "+condition+"=? and stock>0 ORDER BY prodNo DESC LIMIT ?, 10;");	
        }
    }
	public static void changeSelectProductCateL10(String condition, String sort){
        SELECT_PRODUCTS_CATE_L10.clear();
        SELECT_PRODUCTS_CATE_L10.add("SELECT a.*, avg(b.rating) as rating FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE stock>0 group by a.prodNo ORDER BY "+condition+" "+sort+", prodNo DESC LIMIT ?, 10;");
    }
	/*
	조건 입력시 - 조건에 해당하는 상품만 조회함.
	condition = 검색조건
	 */
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



	public static final String SELECT_PRODUCTS_COUNT_ALL = "	SELECT COUNT(*) FROM `km_product` ";
	public static final String SELECT_PRODUCTS_COUNT_CATE12 = "SELECT COUNT(prodNo) FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? and stock>0;";
	public static final String SELECT_PRODUCTS_COUNT_CATE1 = "SELECT COUNT(prodNo) FROM Kmarket.km_product WHERE prodCate2 = ? and stock>0;";


	public final static String INSERT_PRODUCT = """
			INSERT INTO `km_product` SET  
			`prodCate1`=?,
			`prodCate2`=?,
			`prodName`=?,
			`descript`=?,
			`company`=?,
			`price`=?,
			`discount`=?,
			`point`=?,
			`stock`=?,
			`seller`=?,
			`delivery`=?,
			`thumb1`=?,
			`thumb2`=?,
			`thumb3`=?,
			`detail`=?,
			`status`=?,
			`duty`=?,
			`receipt`=?,
			`bizType`=?,
			`origin`=?,
			`ip` =?,
			`rdate` = NOW()""";
	public final static String SELECT_PRODUCT				= "SELECT a.*, AVG(b.rating) as rating FROM `km_product` as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE a.`prodNo` =?";
	public final static String SELECT_PRODUCTS_ALL_L10 			= "SELECT * FROM `km_product` WHERE `stock` > 0 LIMIT ?,10";
	public final static String SELECT_PRODUCTS_CATE_L10_ADMIN 		= "SELECT * FROM `km_product` WHERE `stock` > 0 AND `cate`=? LIMIT ?,10";
	public final static String SELECT_COUNT_PRODUCTS_ALL 	= "SELECT COUNT(*) `km_product` WHERE `stock` > 0";
	public final static String SELECT_COUNT_PRODUCTS_CATE 	= "SELECT COUNT(*) `km_product` WEHRE `stock` > 0 AND `cate`=?";


	public final static String DELETE_PRODUCT = "DELETE * FROM `km_product` WHERE `prodNo`=?";

	//-----------------------------km_product_cart-----------------------

	public static final String INSERT_CART = "INSERT INTO `km_product_cart` SET uid = ?, prodNo =?, count=?, price =?, discount =?, point =?, delivery =?, total = ?, rdate=?;";

	public static final String DELETE_CART_UID = "DELETE FROM `km_product_cart` WHERE uid =?;";
	public static final String SELECT_CARTS = "SELECT a.*, kp.prodName as prodName,kp.descript as descript  FROM `km_product_cart` as a join Kmarket.km_product kp on kp.prodNo = a.prodNo WHERE a.uid=?;";

	//--------------------------km_product_cate1------------------------------

	public final static String SELECT_PRODUCT_CATE1_NAME = "SELECT c1Name from km_product_cate1 where cate1 = ?;";
	public final static String SELECT_PRODUCT_CATE1S = "select * FROM Kmarket.km_product_cate1";
	//--------------------------km_product_cate2------------------------------

	public final static String SELECT_PRODUCT_CATE12_NAME = "SELECT c1Name, c2Name FROM km_product_cate2 as c2 LEFT JOIN Kmarket.km_product_cate1 c1 on c1.cate1 = c2.cate1 where c2.cate1 = ? and c2.cate2 =?;";
	public final static String SELECT_PRODUCT_CATE12 = "select c1.*, c2.cate2, c2Name from km_product_cate1 as c1 join km_product_cate2 as c2 on c1.cate1 = c2.cate1;";
	public final static String SELECT_PRODUCT_CATE2S = "select * FROM Kmarket.km_product_cate2";


	//--------------------------km_product_order------------------------------

	//--------------------------km_product_order_item------------------------------

	//--------------------------km_product_review------------------------------
	public static final String SELECT_PRODUCT_REVIEWS_L5 = "SELECT * FROM km_product_review where prodNo=? ORDER BY revNo DESC LIMIT ?,5;";
	public static final String SELECT_COUNT_REVIEWS_PNO = "SELECT COUNT(revNo) FROM Kmarket.km_product_review WHERE prodNo = ?;";

  
  //--------------------------km_cs_qna------------------------------
  
	
	// km_cs_cate
	public final static String SELECT_CSCATE1S_BY_TYPE1 	= "SELECT * FROM `km_cs_cate1` WHERE `cate1`<20";
	public final static String SELECT_CSCATE1S_BY_TYPE2 	= "SELECT * FROM `km_cs_cate1` WHERE `cate1`>=20";
	public final static String SELECT_CSCATE2S_BY_CATE1 	= "SELECT * FROM `km_cs_cate2` WHERE `cate1`=?";
	public static final String SELECT_CSCATE1_C1NAME = "SELECT `c1Name` FROM `km_cs_cate1 WHERE `cate1`=?";
	public static final String SELECT_CSCATE2_C2NAME = "SELECT `c1Name` FROM `km_cs_cate2 WHERE `cate1`=? AND `cate2`=?";
	
	// km_cs_qna
	public final static String INSERT_CSQNA_QUESTION 		= "INSERT INTO `km_cs_qna` SET "
																	+ "`cate1` = ?, "
																	+ "`cate2` = ?, "
																	+ "`title` = ?, "
																	+ "`content` = ?, "
																	+ "`file1` = ?, "
																	+ "`file2` = ?, "
																	+ "`file3` = ?, "
																	+ "`file4` = ?, "
																	+ "`writer` = ?, "
																	+ "`ordNo` = ?, "
																	+ "`prodNo` = ?, "
																	+ "`parent` = ?, "
																	+ "`answerComplete` = ?, "
																	+ "`regip`=?, "
																	+ "`rdate`=NOW()";
	public final static String INSERT_CSQNA_ANSWER 		= "INSERT INTO `km_cs_qna` SET "
																	+ "`cate1` = ?, "
																	+ "`cate2` = ?, "
																	+ "`title` = ?, "
																	+ "`content` = ?, "
																	+ "`file1` = ?, "
																	+ "`file2` = ?, "
																	+ "`file3` = ?, "
																	+ "`file4` = ?, "
																	+ "`writer` = ?, "
																	+ "`parent` = 0, "
																	+ "`regip`=?, "
																	+ "`rdate`=NOW()";
	public static final String SELECT_CSQNA = """
			SELECT 
			 a.*, b.`name`, c.`c1Name`, d.`c2Name` 
			 FROM `km_cs_qna` AS a 
			 JOIN `km_member` AS b ON a.writer=b.uid 
			 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
			 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1 AND a.cate2=d.cate2 
			 WHERE `qnaNo`=?
			""";
	public static final String SELECT_CSQNA_ANSWER = """
															SELECT 
																 a.*, b.`name` 
																 FROM `km_cs_qna` AS a 
																 JOIN `km_member` AS b ON a.writer=b.uid 
																 WHERE `parent`=?
																""";
			
	public static final String SELECT_CSQNAS = """
																SELECT 
																 a.*, b.`name`, c.`c1Name`, d.`c2Name` 
																 FROM `km_cs_qna` AS a 
																 JOIN `km_member` AS b ON a.writer=b.uid 
																 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
																 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1  AND a.cate2=d.cate2 
																 WHERE `parent`=0 
																 ORDER BY `qnaNo` DESC 
																 LIMIT ?, 10
																 """;
	public static final String SELECT_CSQNAS_BY_CATE1 = "SELECT "
																+ "a.*, b.`name`, c.`c1Name`, d.`c2Name`"
																+ "FROM `km_cs_qna` AS a "
																+ "JOIN `km_member` AS b ON a.writer=b.uid "
																+ "JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 "
																+ "JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1 AND a.cate2=d.cate2 "
																+ "WHERE `parent`=0 AND a.cate1=? "
																+ "ORDER BY `qnaNo` DESC "
																+ "LIMIT ?, 10";
	public final static String SELECT_CSQNA_MAX_NO = "SELECT MAX(`qnaNo`) FROM `km_cs_qna`";
	public final static String SELECT_CSQNA_COUNT = "SELECT count(*) FROM `km_cs_qna`";
	public final static String SELECT_CSQNA_COUNT_BY_CATE1 = "SELECT count(*) FROM `km_cs_qna` WHERE `cate1`=?";
	

	public final static String UPDATE_CSQNA		= "UPDATE `km_cs_qna` SET "
																	+ "`title` = ?, "
																	+ "`content` = ?, "
																	+ "`file1` = ?, "
																	+ "`file2` = ?, "
																	+ "`file3` = ?, "
																	+ "`file4` = ?, "
																	+ "`ordNo` = ?, "
																	+ "`prodNo` = ? "
																	+ "WHERE `qnaNo` = ?";
	public final static String UPDATE_CSQNA_ANSWERCOMPLETE	= "UPDATE `km_cs_qna` SET `answerComplete` = ? WHERE `qnaNo` = ?";

	public static final String DELETE_CSQNA = "DELETE FROM `km_cs_qna` WHERE `qnaNo`=?";



}
