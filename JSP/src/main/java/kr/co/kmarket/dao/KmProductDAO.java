package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KmProductDAO extends DBHelper {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(KmProductDAO.class);
	private static KmProductDAO INSTANCE = new KmProductDAO();

	private KmProductDAO() {
	}

	public static KmProductDAO getInstance() {
		return INSTANCE;
	}


	// 0912 상엽님
	/*condition
	 * 10의자리= 조건(0: hit 1: 판매건수, 2:상품가격, 3:상품평점, 4:상품리뷰, 5:등록날짜)
	 * 조건 검색 : 6:상품명, 7: 상품코드, 8: 제조사, 9: 판매자, 10:할인
	 * 1의자리 = 높은순(1), 낮은순(2) 정렬 , 이외 조건검색방식
	 * */
	public List<KmProductDTO> selectKmProductsCateL10(KmProductCate2DTO kmProductCate2DTO, int start, String condition) {
		List<KmProductDTO> kmProducts = new ArrayList<KmProductDTO>();
		try {
			String[] conditionName= new String[2];

			conditionName = changeCondition(condition);
			conn = getConnection();

			if(kmProductCate2DTO.getCate2()!= 0) {
				psmt = conn.prepareStatement("SELECT a.*, avg(b.rating) as rating, c.level FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo JOIN km_member as c on a.seller=c.uid WHERE prodCate1=? and prodCate2 = ? and stock>0 and isRemoved=0 group by a.prodNo ORDER BY "+conditionName[0]+" "+conditionName[1]+", prodNo DESC LIMIT ?, 10;");
				logger.info("1");
			}else if(kmProductCate2DTO.getCate1()!= 0){
				psmt = conn.prepareStatement("SELECT a.*, avg(b.rating) as rating, c.level FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo JOIN km_member as c on a.seller=c.uid WHERE  prodCate1 = ? and stock>0 and isRemoved=0 group by a.prodNo ORDER BY "+conditionName[0]+" "+conditionName[1]+", prodNo DESC LIMIT ?, 10;");
				logger.info("2");
			} else{
				psmt= conn.prepareStatement("SELECT a.*, avg(b.rating) as rating, c.level FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo JOIN km_member as c on a.seller=c.uid WHERE stock>0 and isRemoved=0 group by a.prodNo ORDER BY "+conditionName[0]+" "+conditionName[1]+", prodNo DESC LIMIT ?, 10;");
				logger.info("4");
			}

			if(kmProductCate2DTO.getCate2()!= 0) {
				psmt.setInt(1, kmProductCate2DTO.getCate1());
				psmt.setInt(2, kmProductCate2DTO.getCate2());
				psmt.setInt(3, start);
			}else if(kmProductCate2DTO.getCate1()!= 0){
				psmt.setInt(1, kmProductCate2DTO.getCate1());
				psmt.setInt(2, start);
			}else {
				psmt.setInt(1, start);
			}

			rs = psmt.executeQuery();
			while (rs.next()) {
				KmProductDTO kmProduct = new KmProductDTO();

				kmProduct = getInstance().selectProductData();
				if(!conditionName[1].equals("0")&& !conditionName[1].equals("")) {
					kmProduct.setRating(rs.getInt("rating"));
					kmProduct.setLevel(rs.getInt("level"));
				}

				kmProducts.add(kmProduct);
			}
			close();

		} catch (SQLException e) {
			logger.error("selectKmProductsCate error: %s".formatted(e.getMessage()));
		}
		return kmProducts;
	}

	public int selectCountProductsSearch(String prodName, String condition) {
		int count = 0;
		String conditions[] = new String[2];
		if(!condition.equals("prodName")){
			conditions = changeCondition(condition);
			condition = conditions[0];
		}
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT COUNT(prodNo) as count FROM Kmarket.km_product WHERE "+condition+" LIKE concat('%',?,'%') and stock>0 and isRemoved=0;");
			psmt.setString(1, prodName);
			rs = psmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
			close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return count;
	}

	public List<KmProductDTO> selectProductsSearch(String prodName, int start, String condition){
		List<KmProductDTO> kmProductDTOS = new ArrayList<>();
		String conditions[] = new String[2];
		if(!condition.equals("prodName")){
			conditions = changeCondition(condition);
			condition = conditions[0];
		}
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT a.*, avg(b.rating) as rating, c.level FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo JOIN km_member as c on a.seller=c.uid WHERE a."+condition+" LIKE concat('%',?,'%') and stock>0 and isRemoved=0 group by a.prodNo ORDER BY prodNo DESC Limit ?, 10;");
			psmt.setString(1, prodName);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			while (rs.next()) {
				KmProductDTO kmProductDTO = new KmProductDTO();
				kmProductDTO = selectProductData();
				kmProductDTO.setRating(rs.getInt("rating"));
				kmProductDTO.setLevel(rs.getInt("level"));
				kmProductDTOS.add(kmProductDTO);
			}
			close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return kmProductDTOS;
	}
	public List<KmProductDTO> selectProducts(String condition){
		List<KmProductDTO> kmProductDTOS = new ArrayList<>();
		String[] conditionName= new String[2];

		conditionName = changeCondition(condition);
		try {
			conn = getConnection();
			int count= 8;
			int num=1;
			if(conditionName[0]=="sold"){
				count = 5;
			}
			psmt= conn.prepareStatement("SELECT a.*, avg(b.rating) as rating FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE stock>0 and isRemoved=0 group by a.prodNo ORDER BY "+conditionName[0]+" "+conditionName[1]+", prodNo DESC LIMIT 0, "+count);
			rs = psmt.executeQuery();
			while (rs.next()) {
				KmProductDTO kmProduct = new KmProductDTO();
				kmProduct.setThumb2(rs.getString("thumb2"));
				kmProduct.setProdName(rs.getString("prodName"));
				kmProduct.setProdNo(rs.getInt("prodNo"));
				kmProduct.setDescript(rs.getString("descript"));
				kmProduct.setDiscount(rs.getInt("discount"));
				kmProduct.setPrice(rs.getInt("price"));
				kmProduct.setDelivery(rs.getInt("delivery"));
				if(count==5){
					kmProduct.setEtc1(num);
					num++;
				}
				kmProductDTOS.add(kmProduct);
			}
			close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return kmProductDTOS;
	}
	public int selectKmProductsCountCate(int cate1, int cate2) {
		int count = 0;
		try {
			conn = getConnection();
			if(cate2 !=0) {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_COUNT_CATE12);
				psmt.setInt(1, cate1);
				psmt.setInt(2, cate2);
			}else if(cate1 !=0){
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_COUNT_CATE1);
				psmt.setInt(1, cate1);
			}else{
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_COUNT_ALL);
			}


			rs = psmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			close();
		} catch (SQLException e) {
			logger.error("selectProductsCountCate error: %s".formatted(e.getMessage()));
		}
		return count;
	}

	private KmProductDTO selectProductData() throws SQLException {
		KmProductDTO kmProduct = new KmProductDTO();
		kmProduct.setProdNo(rs.getInt("prodNo"));
		kmProduct.setProdCate1(rs.getInt("prodCate1"));
		kmProduct.setProdCate2(rs.getInt("prodCate2"));
		kmProduct.setProdName(rs.getString("prodName"));
		kmProduct.setDescript(rs.getString("descript"));
		kmProduct.setCompany(rs.getString("company"));
		kmProduct.setSeller(rs.getString("seller"));
		kmProduct.setPrice(rs.getInt("price"));
		kmProduct.setDiscount(rs.getInt("discount"));
		kmProduct.setPoint(rs.getInt("point"));
		kmProduct.setStock(rs.getInt("stock"));
		kmProduct.setSold(rs.getInt("sold"));
		kmProduct.setDelivery(rs.getInt("delivery"));
		kmProduct.setHit(rs.getInt("hit"));
		kmProduct.setScore(rs.getInt("score"));
		kmProduct.setReview(rs.getInt("review"));
		kmProduct.setThumb1(rs.getString("thumb1"));
		kmProduct.setThumb2(rs.getString("thumb2"));
		kmProduct.setThumb3(rs.getString("thumb3"));
		kmProduct.setDetail(rs.getString("detail"));
		kmProduct.setStatus(rs.getString("status"));
		kmProduct.setDuty(rs.getString("duty"));
		kmProduct.setReceipt(rs.getString("receipt"));
		kmProduct.setBizType(rs.getString("bizType"));
		kmProduct.setOrigin(rs.getString("origin"));
		kmProduct.setIp(rs.getString("ip"));
		kmProduct.setrDate(rs.getString("rDate"));
		kmProduct.setwDate(rs.getString("wDate"));
		kmProduct.setEtc1(rs.getInt("etc1"));
		kmProduct.setEtc2(rs.getInt("etc2"));
		kmProduct.setEtc3(rs.getString("etc3"));
		kmProduct.setEtc4(rs.getString("etc4"));
		kmProduct.setEtc5(rs.getString("etc5"));
		kmProduct.setDiscountPrice((kmProduct.getPrice()*(100-kmProduct.getDiscount()))/100);
		kmProduct.setTotal((kmProduct.getDiscountPrice()));
		return kmProduct;
	}

	// 0912 수현님
	public void insertProduct(KmProductDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setInt(1, dto.getProdCate1());
			psmt.setInt(2, dto.getProdCate2());
			psmt.setString(3, dto.getProdName());
			psmt.setString(4, dto.getDescript());
			psmt.setString(5, dto.getCompany());
			psmt.setInt(6, dto.getPrice());
			psmt.setInt(7, dto.getDiscount());
			psmt.setInt(8, dto.getPoint());
			psmt.setInt(9, dto.getStock());
			psmt.setString(10, dto.getSeller());
			psmt.setInt(11, dto.getDelivery());
			psmt.setString(12, dto.getThumb1());
			psmt.setString(13, dto.getThumb2());
			psmt.setString(14, dto.getThumb3());
			psmt.setString(15, dto.getDetail());
			psmt.setString(16, dto.getStatus());
			psmt.setString(17, dto.getDuty());
			psmt.setString(18, dto.getReceipt());
			psmt.setString(19, dto.getBizType());
			psmt.setString(20, dto.getOrigin());
			psmt.setString(21, dto.getIp());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public KmProductDTO selectProduct(String prodNo) {
		KmProductDTO dto = new KmProductDTO();

		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCT);
			psmt.setString(1, prodNo);
			rs = psmt.executeQuery();

			if(rs.next()) {
				dto = getInstance().selectProductData();
				dto.setRating(rs.getInt("rating"));
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;

	}
	public List<KmProductDTO> selectProducts(int start) {

		List<KmProductDTO> products = new ArrayList<>();

		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_ALL_L10);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();

			while(rs.next()) {
				KmProductDTO dto = new KmProductDTO();
				dto.setProdNo(rs.getInt(1));
				dto.setProdCate1(rs.getInt(2));
				dto.setProdCate2(rs.getInt(3));
				dto.setProdName(rs.getString(4));
				dto.setDescript(rs.getString(5));
				dto.setCompany(rs.getString(6));
				dto.setPrice(rs.getInt(7));
				dto.setDiscount(rs.getInt(8));
				dto.setPoint(rs.getInt(9));
				dto.setStock(rs.getInt(10));
				dto.setDelivery(rs.getInt(11));
				dto.setThumb1(rs.getString(12));
				dto.setThumb2(rs.getString(13));
				dto.setThumb3(rs.getString(14));
				dto.setDetail(rs.getString(15));
				dto.setStatus(rs.getString(16));
				dto.setDuty(rs.getString(17));
				dto.setReceipt(rs.getString(18));
				dto.setBizType(rs.getString(19));
				dto.setOrigin(rs.getString(20));

				products.add(dto);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public List<KmProductDTO> selectProducts(String cate, int start) {

		List<KmProductDTO> products = new ArrayList<>();

		try {
			conn = getConnection();

			if(cate.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_ALL_L10);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_CATE_L10_ADMIN);
				psmt.setString(1, cate);
				psmt.setInt(2, start);
			}
			rs = psmt.executeQuery();

			while(rs.next()) {
				KmProductDTO dto = new KmProductDTO();
				dto.setProdNo(rs.getInt(1));
				dto.setProdCate1(rs.getInt(2));
				dto.setProdCate2(rs.getInt(3));
				dto.setProdName(rs.getString(4));
				dto.setDescript(rs.getString(5));
				dto.setCompany(rs.getString(6));
				dto.setPrice(rs.getInt(7));
				dto.setDiscount(rs.getInt(8));
				dto.setPoint(rs.getInt(9));
				dto.setStock(rs.getInt(10));
				dto.setDelivery(rs.getInt(11));
				dto.setThumb1(rs.getString(12));
				dto.setThumb2(rs.getString(13));
				dto.setThumb3(rs.getString(14));
				dto.setDetail(rs.getString(15));
				dto.setStatus(rs.getString(16));
				dto.setDuty(rs.getString(17));
				dto.setReceipt(rs.getString(18));
				dto.setBizType(rs.getString(19));
				dto.setOrigin(rs.getString(20));

			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	// public void updateProduct(KmProductDTO dto) {}
	public int deleteProduct(int prodNo) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_PRODUCT);
			psmt.setInt(1, prodNo);
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("deleteFile - " + e.getMessage());
		}
		return result;

	}
	// public void updateProduct(KmProductDTO dto) {}
	public void admin_deleteProduct(String prodNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_PRODUCT);
			psmt.setString(1, prodNo);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("deleteFile - " + e.getMessage());
		}

	}

	public int selectCountProductsTotal() {
		int total = 0;

		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_ALL);
			rs = psmt.executeQuery();

			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public int selectCountProductsTotal(String cate) {
		int total = 0;

		try {
			conn = getConnection();
			if(cate.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_ALL);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_CATE);
				psmt.setString(1, cate);
			}
			rs = psmt.executeQuery();

			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int selectKmProductsCountAll() {

		int count = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_COUNT_ALL);

			rs = psmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			close();
		} catch (SQLException e) {
			logger.error("selectProductsCountCate error: %s".formatted(e.getMessage()));
		}
		return count;
	}


	public String[] changeCondition(String condition){
		int conditionData1 = Integer.parseInt(condition)/10;
		int conditionData2 = Integer.parseInt(condition)%10;
		String st1 = null;
		String st2 = null;
		if(conditionData1==1){
			st1 = "sold";
		}else if(conditionData1==2){
			st1 = "price";
		}else if(conditionData1==3){
			st1 = "rating";
		}else if(conditionData1==4){
			st1 = "review";
		}
		else if(conditionData1==5){
			st1 = "rDate";
		}
		else if(conditionData1==6){
			st1 = "prodName";
		}
		else if(conditionData1==7){
			st1 = "prodNo";
		}
		else if(conditionData1==8){
			st1 = "company";
		}
		else if(conditionData1==9){
			st1 = "seller";
		}
		else if(conditionData1==10){
			st1 = "discount";
		}
		else{
			st1="hit";
		}
		if(conditionData2==1){
			st2 = "desc";
		}else if(conditionData2==2){
			st2 = "asc";
		}else {
			st2="";
		}
		/*condition
		 * st1= 조건(0: hit, 1: 판매건수, 2:상품가격, 3:상품평점, 4:상품리뷰, 5:등록날짜)
		 * 조건 검색 : 6:상품명, 7: 상품코드,8: 제조사, 9: 판매자
		 * st2 = 높은순(1), 낮은순(2) 정렬 , 이외 조건검색방식
		 * */
		return new String[]{st1, st2};
	}

	public void updateProduct(KmProductDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_PRODUCT_ADMIN);
			psmt.setInt(1, dto.getProdCate1());
			psmt.setInt(2, dto.getProdCate2());
			psmt.setString(3, dto.getProdName());
			psmt.setString(4, dto.getDescript());
			psmt.setString(5, dto.getCompany());
			psmt.setInt(6, dto.getPrice());
			psmt.setInt(7, dto.getDiscount());
			psmt.setInt(8, dto.getPoint());
			psmt.setInt(9, dto.getStock());
			psmt.setString(10, dto.getSeller());
			psmt.setInt(11, dto.getDelivery());
			psmt.setString(12, dto.getThumb1());
			psmt.setString(13, dto.getThumb2());
			psmt.setString(14, dto.getThumb3());
			psmt.setString(15, dto.getDetail());
			psmt.setString(16, dto.getStatus());
			psmt.setString(17, dto.getDuty());
			psmt.setString(18, dto.getReceipt());
			psmt.setString(19, dto.getBizType());
			psmt.setString(20, dto.getOrigin());
			psmt.setString(21, dto.getIp());
			psmt.setInt(22, dto.getProdNo());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int removeProduct(String prodNo) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_PRODUCT_ISREMOVED);
			psmt.setString(1, prodNo);
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("deleteFile - " + e.getMessage());
		}
		return result;

	}
	public void updateProduct(int count, int prodNo) {
		conn = getConnection();
		try {
			psmt = conn.prepareStatement(SQL.UPDATE_PRODUCT);
			psmt.setInt(1, count);
			psmt.setInt(2, count);
			psmt.setInt(3, prodNo);
			psmt.executeUpdate();
			close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void updateProduct(int count, String prodNo) {
		conn = getConnection();
		try {
			psmt = conn.prepareStatement(SQL.UPDATE_PRODUCT);
			psmt.setInt(1, count);
			psmt.setInt(2, count);
			psmt.setString(3, prodNo);
			psmt.executeUpdate();
			close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateProductHit( String prodNo) {
		conn = getConnection();
		try {
			psmt = conn.prepareStatement(SQL.UPDATE_PRODUCT_HIT);
			psmt.setString(1, prodNo);
			psmt.executeUpdate();
			close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}