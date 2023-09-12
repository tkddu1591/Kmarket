package kr.co.kmarket.db;

public class SQL {

    //km_member

    //km_member_point

    //km_member_terms

    //km_product

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
	public final static String SELECT_PRODUCTS_CATE_L10 		= "SELECT * FROM `km_product` WHERE `stock` > 0 AND `cate`=? LIMIT ?,10";
	public final static String SELECT_COUNT_PRODUCTS_ALL 	= "SELECT COUNT(*) `km_product` WHERE `stock` > 0";
	public final static String SELECT_COUNT_PRODUCTS_CATE 	= "SELECT COUNT(*) `km_product` WEHRE `stock` > 0 AND `cate`=?";
	
}
