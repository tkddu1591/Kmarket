package kr.co.kmarket.db;

import java.util.ArrayList;
import java.util.List;

public class SQL {

    //km_member
	public static final String SELECT_MEMBER = "SELECT * FROM `km_member` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
    //km_member_point

    //km_member_terms
	public static final String SELECT_TERMS = "SELECT * FROM `km_member_terms`";
	
    //km_product
    public static final List<String> SELECT_PRODUCTS_CATE_L10 = new ArrayList<>();


    /*
     * 조회 쿼리 통합
     * condition
     * 10의자리= 조건(1: 판매건수, 2:상품가격, 3:상품평점, 4:상품리뷰, 5:등록날짜
     * 1의자리 = 높은순(0), 낮은순(1)
     * */
    public static void changeSelectProductCateL10Condition(String condition, String cate2){

        int conditionData1 = Integer.parseInt(condition)/10;
        int conditionData2 = Integer.parseInt(condition)%10;
        String st1 = null;
        String st2 = null;
        if(conditionData1==1){
            st1 = "sold";
        }else if(conditionData1==2){
            st1 = "price";
        }else if(conditionData1==3){
            st1 = "score";
        }else if(conditionData1==4){
            st1 = "review";
        }else if(conditionData1==5){
            st1 = "rDate";
        }
        if(conditionData2==1){
            st2 = "desc";
        }else if(conditionData2==2){
            st2 = "asc";
        }
        SELECT_PRODUCTS_CATE_L10.add("SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY "+st1+" "+st2+" ,prodNo DESC LIMIT ?, 10;");
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

    public static final String SELECT_PRODUCTS_COUNT_CATE = "SELECT COUNT(prodNo) FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ?;";

    //km_product_cart

    //km_product_cate1

    //km_product_cate2

    //km_product_order

    //km_product_order_item

    //km_product_review

	//km_product_register
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
			
	public final static String SELECT_PRODUCT				= "SELECT * FROM `km_product` WHERE `prodNo` =?";
	public final static String SELECT_PRODUCTS_ALL_L10 			= "SELECT * FROM `km_product` WHERE `stock` > 0 LIMIT ?,10";
	public final static String SELECT_PRODUCTS_CATE_L10_ADMIN 		= "SELECT * FROM `km_product` WHERE `stock` > 0 AND `cate`=? LIMIT ?,10";
	public final static String SELECT_COUNT_PRODUCTS_ALL 	= "SELECT COUNT(*) `km_product` WHERE `stock` > 0";
	public final static String SELECT_COUNT_PRODUCTS_CATE 	= "SELECT COUNT(*) `km_product` WEHRE `stock` > 0 AND `cate`=?";
	
	// km_cs_cate
	public final static String SELECT_CSCATE1S_BY_TYPE1 	= "SELECT * FROM `km_cs_cate1` WHERE `cate1`<20";
	public final static String SELECT_CSCATE1S_BY_TYPE2 	= "SELECT * FROM `km_cs_cate1` WHERE `cate1`>=20";
	public final static String SELECT_CSCATE2S_BY_CATE1 	= "SELECT * FROM `km_cs_cate2` WHERE `cate1`=?";
	
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
																	+ "`parent` = 0, "
																	+ "`answerComplete` = 0, "
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
	
	public final static String UPDATE_CSQNA_ANSWERCOMPLETE	= "UPDATE `km_cs_qna` SET `answerComplete` = ? WHERE `qnaNo` = ?";
}
