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
	public static final String SELECT_MEMBER_BY_UID = "SELECT * FROM `km_member` WHERE `uid`=?";
	public static final String SELECT_MEMBER_BY_NAME_AND_EMAIL = "SELECT * FROM `km_member` WHERE `name`=? AND `email`=?";

	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `km_member` WHERE `uid`=?";
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `km_member` WHERE `hp`=?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `km_member` WHERE `email`=?";
	public static final String SELECT_COUNT_NAME_AND_EMAIL = "SELECT COUNT(*) FROM `km_member` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_UID_AND_EMAIL = "SELECT COUNT(*) FROM `km_member` WHERE `uid`=? AND `email`=?";

	public static final String UPDATE_PASS = "UPDATE `km_member` SET `pass`=SHA2(?, 256) WHERE `uid`=?";

	//----------------------------km_member_point-----------------------


	public static final String INSERT_POINT = "INSERT INTO Kmarket.km_member_point ( uid, ordNo, point, pointDate)\n" +
			"VALUES (?, ?, ?, NOW())";

	//----------------------------km_member_terms-----------------------
	public static final String SELECT_TERMS = "SELECT * FROM `km_member_terms`";

	//------------------------------km_product----------------------------


	public static final List<String> SELECT_PRODUCTS_CATE_L10 = new ArrayList<>();
	public static final String UPDATE_POINT = "UPDATE `km_member` SET `point`=point+? WHERE `uid`=?";
	public static final String UPDATE_PRODUCT= "UPDATE `km_product` SET `sold`=sold+?, stock=stock-? WHERE `prodNo`=?";
	public static final String UPDATE_PRODUCT_HIT = "UPDATE `km_product` SET `hit`=hit+1 WHERE `prodNo`=? ";
	public static final String SELECT_PRODUCT_SEARCH = "SELECT a.*, avg(b.rating) as rating, c.level FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo JOIN km_member as c on a.seller=c.uid WHERE prodName LIKE concat('%',?,'%') and stock>0 AND `isRemoved` = 0 group by a.prodNo ORDER BY prodNo DESC Limit ?, 10;";
	public static final String SELECT_PRODUCT_SEARCH_COUNT = "SELECT COUNT(prodNo) as count FROM Kmarket.km_product WHERE prodName LIKE concat('%',?,'%') and stock>0 AND `isRemoved` = 0;";









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
			SELECT_PRODUCTS_CATE_L10.add("SELECT a.*, avg(b.rating) as rating FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE prodCate1=? and prodCate2 = ? AND `isRemoved` = 0 and stock>0 group by a.prodNo ORDER BY "+condition+" "+sort+", prodNo DESC LIMIT ?, 10;");
		}else {
			SELECT_PRODUCTS_CATE_L10.add("SELECT * FROM Kmarket.km_product as a LEFT JOIN Kmarket.km_product_review WHERE "+condition+"=? and stock>0 AND `isRemoved` = 0 ORDER BY prodNo DESC LIMIT ?, 10;");
		}
	}
	public static void changeSelectProductCateL10(String condition, String sort){
		SELECT_PRODUCTS_CATE_L10.clear();
		SELECT_PRODUCTS_CATE_L10.add("SELECT a.*, avg(b.rating) as rating FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE stock>0 AND `isRemoved` = 0 group by a.prodNo ORDER BY "+condition+" "+sort+", prodNo DESC LIMIT ?, 10;");
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



	public static final String SELECT_PRODUCTS_COUNT_ALL = "	SELECT COUNT(*) FROM `km_product` where stock>0 AND `isRemoved` = 0;";
	public static final String SELECT_PRODUCTS_COUNT_CATE12 = "SELECT COUNT(prodNo) FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? and stock>0 AND `isRemoved` = 0;";
	public static final String SELECT_PRODUCTS_COUNT_CATE1 = "SELECT COUNT(prodNo) FROM Kmarket.km_product WHERE prodCate2 = ? and stock>0 AND `isRemoved` = 0;";


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
	public final static String SELECT_PRODUCTS_ALL_L10 			= "SELECT * FROM `km_product` WHERE `stock` > 0 LIMIT ?,10 WHERE `isRemoved` = 0";
	public final static String SELECT_PRODUCTS_CATE_L10_ADMIN 		= "SELECT * FROM `km_product` WHERE `stock` > 0 AND `cate`=? AND `isRemoved` = 0 LIMIT ?,10 ";
	public final static String SELECT_COUNT_PRODUCTS_ALL 	= "SELECT COUNT(*) `km_product` WHERE `stock` > 0 AND `isRemoved` = 0";
	public final static String SELECT_COUNT_PRODUCTS_CATE 	= "SELECT COUNT(*) `km_product` WEHRE `stock` > 0 AND `cate`=? AND `isRemoved` = 0";


	public final static String UPDATE_PRODUCT_ADMIN = """
			UPDATE  `km_product` SET  
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
			`rdate` = NOW()
			WHERE `prodNo`= ?
			""";
	public final static String DELETE_PRODUCT = "DELETE FROM `km_product` WHERE `prodNo`=?";
	public final static String UPDATE_PRODUCT_ISREMOVED = "UPDATE `km_product` SET `isRemoved`=1, `wdate`=NOW(), `thumb1`=null, `thumb2`=null, `thumb3`=null, `detail`=null WHERE `prodNo`=?";

	//-----------------------------km_product_cart-----------------------

	public static final String INSERT_CART = "INSERT INTO `km_product_cart` SET uid = ?, prodNo =?, count=?, price =?, discount =?, point =?, delivery =?, total = ?, rdate=?;";
	public static final String DELETE_CART = "DELETE FROM `km_product_cart` WHERE cartNo =?;";
	public static final String DELETE_CART_UID = "DELETE FROM `km_product_cart` WHERE uid =?;";
	public static final String SELECT_CARTS = "SELECT a.*, kp.prodName as prodName,kp.descript as descript ,kp.thumb1 as thumb1 FROM `km_product_cart` as a join Kmarket.km_product kp on kp.prodNo = a.prodNo WHERE a.uid=?;";
	public static final String SELECT_CART_COUNT_PROD = "SELECT COUNT(a.prodNo) as count FROM km_product_cart as a WHERE prodNo = ? ;";
	public static final String UPDATE_CART_COUNT = "UPDATE `km_product_cart` SET count = count+?, total = (price*(100-discount))/100*count + delivery WHERE prodNo =?;";

	public static final String DELETE_CARTS = "DELETE FROM `km_product_cart` WHERE uid=?;";

	//--------------------------km_product_cate1------------------------------

	public final static String SELECT_PRODUCT_CATE1_NAME = "SELECT c1Name from km_product_cate1 where cate1 = ?;";
	public final static String SELECT_PRODUCT_CATE1S = "select * FROM Kmarket.km_product_cate1";
	//--------------------------km_product_cate2------------------------------

	public final static String SELECT_PRODUCT_CATE12_NAME = "SELECT c1Name, c2Name FROM km_product_cate2 as c2 LEFT JOIN Kmarket.km_product_cate1 c1 on c1.cate1 = c2.cate1 where c2.cate1 = ? and c2.cate2 =?;";
	public final static String SELECT_PRODUCT_CATE12 = "select c1.*, c2.cate2, c2Name from km_product_cate1 as c1 join km_product_cate2 as c2 on c1.cate1 = c2.cate1;";
	public final static String SELECT_PRODUCT_CATE2S = "select * FROM Kmarket.km_product_cate2";


	//--------------------------km_product_order------------------------------

	public static final String SELECT_LAST_ORDERNO = "SELECT MAX(ordNo) AS ordNo FROM `km_product_order`;";

	public static final String INSERT_ORDER = "INSERT INTO Kmarket.km_product_order ( ordUid, ordCount, ordPrice, ordDiscount, ordDelivery, savePoint, usedPoint,\n" +
			"                                      ordTotPrice, recipName, recipHp, recipZip, recipAddr1, recipAddr2, ordPayment,\n" +
			"                                      ordComplete, ordDate)\n" +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,NOW());";
	//--------------------------km_product_order_item------------------------------
	public static final String INSERT_ORDER_ITEM = "INSERT INTO Kmarket.km_product_order_item (ordNo, prodNo, count, price, discount, point, delivery, total)\n" +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

	//--------------------------km_product_review------------------------------
	public static final String SELECT_PRODUCT_REVIEWS_L5 = "SELECT * FROM km_product_review where prodNo=? ORDER BY revNo DESC LIMIT ?,5;";
	public static final String SELECT_COUNT_REVIEWS_PNO = "SELECT review FROM Kmarket.km_product WHERE prodNo = ?;";


	//--------------------------km_cs_qna------------------------------


	// km_cs_cate
	public final static String SELECT_CSCATE1S_BY_TYPE1 	= "SELECT * FROM `km_cs_cate1` WHERE `cate1`<20";
	public final static String SELECT_CSCATE1S_BY_TYPE2 	= "SELECT * FROM `km_cs_cate1` WHERE `cate1`>=20";
	public final static String SELECT_CSCATE1S_ALL	= "SELECT * FROM `km_cs_cate1`";
	public final static String SELECT_CSCATE2S_BY_CATE1 	= "SELECT * FROM `km_cs_cate2` WHERE `cate1`=?";
	public static final String SELECT_CSCATE1_C1NAME = "SELECT `c1Name` FROM `km_cs_cate1` WHERE `cate1`=?";
	public static final String SELECT_CSCATE2_C2NAMES = "SELECT `c2Name` FROM `km_cs_cate2` WHERE `cate1`=?";
	public static final String SELECT_CSCATE_C2NAME = "SELECT `c2Name` FROM `km_cs_cate2` WHERE `cate1`=? AND `cate2`=?";

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
			+ "`content` = ?, "
			+ "`writer` = ?, "
			+ "`parent` = ?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";

	public static String getSelectCsQnasL10(String cate1, String cate2, int start){
		String SELECT_CSNOTICES_L10 = """
				SELECT 
					 a.*, b.`name`, c.`c1Name`, d.`c2Name` 
					 FROM `km_cs_qna` AS a 
					 JOIN `km_member` AS b ON a.writer=b.uid 
					 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
					 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1  AND a.cate2=d.cate2 
						""";


		if(cate1 != null&& !cate1.equals("0")) {
			if( cate2 != null&& !cate2.equals("0")) {
				// cate2 있고 cate1 있음
				SELECT_CSNOTICES_L10 += " WHERE a.`cate1`=" + cate1 ;
				SELECT_CSNOTICES_L10 += " AND a.`cate2`=" + cate2 ;
			} else {
				//cate2 없고 cate1 있음
				SELECT_CSNOTICES_L10 += " WHERE a.`cate1`=" + cate1 ;
			}
			SELECT_CSNOTICES_L10 += " AND `parent`=0" ;
		}else {
			//cate1 없음
			SELECT_CSNOTICES_L10 += " WHERE `parent`=0" ;
		}
		SELECT_CSNOTICES_L10 += " ORDER BY `qnaNo` DESC  LIMIT " + start + ", 10 ";
		return SELECT_CSNOTICES_L10;
	}

	public static String getSelectCsQnaCount(String cate1, String cate2){
		String SELECT_CSNOTICES_COUNT = """
										SELECT 
											 COUNT(*)
											 FROM `km_cs_qna` 
												""";

		if(cate1 != null&& !cate1.equals("0")) {
			if( cate2 != null&& !cate2.equals("0")) {
				// cate2 있고 cate1 있음
				SELECT_CSNOTICES_COUNT += " WHERE `cate1`=" + cate1 ;
				SELECT_CSNOTICES_COUNT += " AND `cate2`=" + cate2;
			} else {
				//cate2 없고 cate1 있음
				SELECT_CSNOTICES_COUNT += " WHERE `cate1`=" + cate1 ;
			}
			SELECT_CSNOTICES_COUNT += " AND `parent`=0" ;
		}else {
			// cate1 없음
			SELECT_CSNOTICES_COUNT += " WHERE `parent`=0" ;
		}
		return SELECT_CSNOTICES_COUNT;
	}

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
	public final static String SELECT_CSQNA_MAX_NO = "SELECT MAX(`qnaNo`) FROM `km_cs_qna` WHERE `parent` = 0";
	public final static String SELECT_CSQNA_COUNT = "SELECT count(*) FROM `km_cs_qna` WHERE `parent` = 0";
	public final static String SELECT_CSQNA_COUNT_BY_CATE1 = "SELECT count(*) FROM `km_cs_qna` WHERE `cate1`=?  AND `parent` = 0";
	public final static String SELECT_CSQNA_LATESTS = """
															SELECT 
																 a.*, b.`name`, c.`c1Name`, d.`c2Name` 
																 FROM `km_cs_qna` AS a 
																 JOIN `km_member` AS b ON a.writer=b.uid 
																 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
																 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1  AND a.cate2=d.cate2 
																 WHERE `parent`=0 
																 ORDER BY `qnaNo` DESC 
																 LIMIT ?
													""";

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
	public final static String UPDATE_CSQNA_ANSWER		= "UPDATE `km_cs_qna` SET "
			+ "`content` = ? "
			+ "WHERE `parent` = ?";
	public final static String UPDATE_CSQNA_ANSWERCOMPLETE	= "UPDATE `km_cs_qna` SET `answerComplete` = ? WHERE `qnaNo` = ?";

	public static final String DELETE_CSQNA = "DELETE FROM `km_cs_qna` WHERE `qnaNo`=?";

	// km_cs_notice


	/*
	 * 조회 쿼리 통합
	 *
	 * 검색 및 카테고리 입력시 - 키워드에 맞는 게시글을 DB에서 가져옴.
	 * */

	public static String changeSelectCsNoticesL10(String cate1, String keyword, int start){
		String SELECT_CSNOTICES_L10 = """
				SELECT 
					 a.*, b.`name`, c.`c1Name`, d.`c2Name` 
					 FROM `km_cs_notice` AS a 
					 JOIN `km_member` AS b ON a.writer=b.uid 
					 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
					 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1  AND a.cate2=d.cate2 
						""";

		if(cate1 != null&& cate1!="0") {
			if(keyword!=null) {
				// keyword 있고 cate1 있음
				SELECT_CSNOTICES_L10 += " WHERE a.`cate1`=" + cate1 ;
				SELECT_CSNOTICES_L10 += " AND (a.`title` LIKE '%" + keyword +"%' OR a.`content` LIKE '%"+keyword+"%')";
			} else {
				//keyword 없고 cate1 있음
				SELECT_CSNOTICES_L10 += " WHERE a.`cate1`=" + cate1 ;
			}
		}else {
			if( keyword!=null) {
				// keyword 있고 cate1 없음
				SELECT_CSNOTICES_L10 += " WHERE a.`title` LIKE '%" + keyword +"%' OR a.`content` LIKE '%"+keyword+"%' ";
			} else {
				//keyword 없고 cate1 없음
			}
		}
		SELECT_CSNOTICES_L10 += " ORDER BY `noticeNo` DESC  LIMIT " + start + ", 10 ";
		return SELECT_CSNOTICES_L10;
	}

	public static String changeSelectCsNoticesCountL10(String cate1, String keyword){
		String SELECT_CSNOTICES_COUNT = """
				SELECT 
					COUNT(*)
					 FROM `km_cs_notice` 
						""";

		if(cate1 != null&& cate1!="0") {
			if( keyword!=null) {
				// keyword 있고 cate1 있음
				SELECT_CSNOTICES_COUNT += " WHERE `cate1`=" + cate1 ;
				SELECT_CSNOTICES_COUNT += " AND (`title` LIKE '%" + keyword +"%' OR `content` LIKE '%"+keyword+"%' ) ";
			} else {
				//keyword 없고 cate1 있음
				SELECT_CSNOTICES_COUNT += " WHERE `cate1`=" + cate1 ;
			}
		}else {
			if(keyword!=null) {
				// keyword 있고 cate1 없음
				SELECT_CSNOTICES_COUNT += " WHERE `title` LIKE '%" + keyword +"%' OR `content` LIKE '%"+keyword+"%' ";
			} else {
				//keyword 없고 cate1 없음
			}
		}
		return SELECT_CSNOTICES_COUNT;
	}

	public final static String INSERT_CSNOTICE 		= "INSERT INTO `km_cs_notice` SET "
			+ "`cate1` = ?, "
			+ "`cate2` = ?, "
			+ "`title` = ?, "
			+ "`content` = ?, "
			+ "`writer` = ?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";
	public static final String SELECT_CSNOTICE = """
												SELECT 
												 a.*, c.`c1Name`, d.`c2Name` 
												 FROM `km_cs_notice` AS a 
												 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
												 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1 AND a.cate2=d.cate2 
												 WHERE `noticeNo`=?
												""";

	public static final String SELECT_CSNOTICES = """
																SELECT 
																 a.*, c.`c1Name`, d.`c2Name` 
																 FROM `km_cs_notice` AS a 
																 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
																 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1  AND a.cate2=d.cate2 
																 ORDER BY `noticeNo` DESC 
																 LIMIT ?, 10
																 """;
	public static final String SELECT_CSNOTICES_BY_CATE1 = "SELECT "
			+ "a.*, c.`c1Name`, d.`c2Name`"
			+ "FROM `km_cs_notice` AS a "
			+ "JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 "
			+ "JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1 AND a.cate2=d.cate2 "
			+ "WHERE a.`cate1`=? "
			+ "ORDER BY `noticeNo` DESC "
			+ "LIMIT ?, 10";
	public final static String SELECT_CSNOTICE_MAX_NO = "SELECT MAX(`noticeNo`) FROM `km_cs_notice`";
	public final static String SELECT_CSNOTICE_COUNT = "SELECT count(*) FROM `km_cs_notice`";
	public final static String SELECT_CSNOTICE_COUNT_BY_CATE1 = "SELECT count(*) FROM `km_cs_notice` WHERE `cate1`=?";
	public static final String SELECT_CSNOTICE_LATEST = """
																SELECT 
																 a.*, c.`c1Name`, d.`c2Name` 
																 FROM `km_cs_notice` AS a 
																 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
																 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1  AND a.cate2=d.cate2 
																 ORDER BY `noticeNo` DESC 
																 LIMIT ?
																 """;

	public final static String UPDATE_CSNOTICE		= """
													UPDATE `km_cs_notice` SET 
																 `cate1` = ?, 
																 `cate2` = ?, 
																 `title` = ?, 
																 `content` = ?
																 WHERE `noticeNo` = ?
															""";
	public final static String UPDATE_CSNOTICE_HIT		= """
											UPDATE `km_cs_notice` SET 
														 `hit` = `hit`+1 
														 WHERE `noticeNo` = ?
													""";

	public static final String DELETE_CSNOTICE = "DELETE FROM `km_cs_notice` WHERE `noticeNo`=?";
	// km_cs_notice
	public final static String INSERT_CSFAQ 		= "INSERT INTO `km_cs_faq` SET "
			+ "`cate1` = ?, "
			+ "`cate2` = ?, "
			+ "`title` = ?, "
			+ "`content` = ?, "
			+ "`relatedFaq` = ?, "
			+ "`writer` = ?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";
	public final static String INSERT_CSFAQ_NOTRELATED 		= "INSERT INTO `km_cs_faq` SET "
			+ "`cate1` = ?, "
			+ "`cate2` = ?, "
			+ "`title` = ?, "
			+ "`content` = ?, "
			+ "`writer` = ?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";
	public static final String SELECT_CSFAQ = """
												SELECT 
												 a.*, c.`c1Name`, d.`c2Name` 
												 FROM `km_cs_faq` AS a 
												 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
												 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1 AND a.cate2=d.cate2 
												 WHERE `faqNo`=?
												""";

	public static final String SELECT_CSFAQS = """
																SELECT 
																 a.*, c.`c1Name`, d.`c2Name` 
																 FROM `km_cs_faq` AS a 
																 JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 
																 JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1  AND a.cate2=d.cate2 
																 ORDER BY `faqNo` DESC 
																 LIMIT ?, 10
																 """;
	public static final String SELECT_CSFAQS_BY_CATE2 = "SELECT "
			+ "a.*, c.`c1Name`, d.`c2Name`"
			+ "FROM `km_cs_faq` AS a "
			+ "JOIN `km_cs_cate1` AS c ON a.cate1=c.cate1 "
			+ "JOIN `km_cs_cate2` AS d ON a.cate1=d.cate1 AND a.cate2=d.cate2 "
			+ "WHERE a.`cate1`=? AND a.`cate2`=? "
			+ "ORDER BY `faqNo` ASC ";
	public final static String SELECT_CSFAQ_MAX_NO = "SELECT MAX(`faqNo`) FROM `km_cs_faq`";


	public final static String UPDATE_CSFAQ		= """
													UPDATE `km_cs_faq` SET 
																 `cate1` = ?, 
																 `cate2` = ?, 
																 `title` = ?, 
																 `content` = ?,
																 `relatedFaq`=? 
																 WHERE `faqNo` = ?
															""";

	public final static String UPDATE_CSFAQ_HIT		= """
											UPDATE `km_cs_faq` SET 
														 `hit` = `hit`+1 
														 WHERE `faqNo` = ?
													""";
	public final static String UPDATE_CSFAQ_NOTRELATED		= """
			UPDATE `km_cs_faq` SET 
						 `cate1` = ?, 
						 `cate2` = ?, 
						 `title` = ?, 
						 `content` = ?
						 WHERE `faqNo` = ?
					""";
	public final static String UPDATE_CSQNA_REMOVE_RELATEDFAQ = """
			UPDATE `km_cs_faq` SET 
						 `relatedFaq` = null
						 WHERE `relatedFaq` = ?
			""";
	public static final String DELETE_CSFAQ = "DELETE FROM `km_cs_faq` WHERE `faqNo`=?";

	// km_cs_faq_rate
	public final static String INSERT_CSFAQRATE 		= "INSERT INTO `km_cs_faq_rate` SET "
			+ "`faqNo` = ?, "
			+ "`uid` = ?, "
			+ "`rate` = ?";
	public static final String SELECT_CSFAQRATE = """
													SELECT 
													 * 
													 FROM `km_cs_faq_rate`
													 WHERE `faqNo`=? AND `uid`=?
													""";

	public static final String SELECT_CSFAQRATES_BY_NO = """
							SELECT
							 COUNT(CASE WHEN `rate`=0 THEN 1 END) AS 'Y',
							 COUNT(CASE WHEN `rate`=1 THEN 1 END) AS 'N'
							 FROM `km_cs_faq_rate`
							 WHERE `faqNo`=?;
							 """;

	public final static String UPDATE_CSFAQRATE		= """
												UPDATE `km_cs_faq_rate` SET 
															 `rate` = ?
															 WHERE `faqNo` = ? AND `uid`=?
														""";

	public final static String DELETE_CSFAQRATE		= """
												DELETE FROM `km_cs_faq_rate` 
															 WHERE `faqNo` = ? AND `uid`=?
														""";
}