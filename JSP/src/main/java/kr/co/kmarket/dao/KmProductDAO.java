package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;
import org.slf4j.Logger;

import java.lang.reflect.Method;
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
     * 10의자리= 조건(1: 판매건수, 2:상품가격, 3:상품평점, 4:상품리뷰, 5:등록날짜) 
     * 조건 검색 : 6:상품명, 7: 상품코드,8: 제조사, 9: 판매자
     * 1의자리 = 높은순(0), 낮은순(1) 정렬 , 이외 조건검색방식
     * */
    public List<KmProductDTO> selectKmProductsCateL10(KmProductCate2DTO kmProductCate2DTO, int start, String condition) {
        List<KmProductDTO> kmProducts = new ArrayList<KmProductDTO>();
        try {

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
            if(conditionData2==1){
                st2 = "desc";
            }else if(conditionData2==2){
                st2 = "asc";
            }else if(conditionData2>2) {
            	st2="";
            }

            conn = getConnection();

            if(kmProductCate2DTO.getCate2()!= null&& !kmProductCate2DTO.getCate2().isEmpty()) {
                SQL.changeSelectProductCateL10(st1, st2, kmProductCate2DTO.getCate2());
            }else {
                SQL.changeSelectProductCateL10(st1, st2);
            }
            psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_CATE_L10.get(0));

            if(kmProductCate2DTO.getCate2()!= null&& !kmProductCate2DTO.getCate2().isEmpty()) {
                psmt.setString(1, kmProductCate2DTO.getCate1());
                psmt.setString(2, kmProductCate2DTO.getCate2());
                psmt.setInt(3, start);
            }else {
                psmt.setString(1, kmProductCate2DTO.getCate1());
                psmt.setInt(2, start);
            }

            rs = psmt.executeQuery();
            while (rs.next()) {
                KmProductDTO kmProduct = new KmProductDTO();

                kmProduct = getInstance().SelectProductData();
                kmProduct.setRating(rs.getInt("rating"));

                kmProducts.add(kmProduct);
            }
            close();

        } catch (SQLException e) {
            logger.error("selectKmProductsCate error: %s".formatted(e.getMessage()));
        }
        return kmProducts;
    }


    public int selectKmProductsCountCate(String cate1, String cate2) {
        int count = 0;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_COUNT_CATE);
            psmt.setString(1, cate1);
            psmt.setString(2, cate2);
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

    private KmProductDTO SelectProductData() throws SQLException {
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
        kmProduct.setTotal((kmProduct.getDiscountPrice()+kmProduct.getDelivery()));
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
			 psmt.setInt(10, dto.getDelivery());
			 psmt.setString(11, dto.getThumb1());
			 psmt.setString(11, dto.getThumb2());
			 psmt.setString(11, dto.getThumb3());
			 psmt.setString(12, dto.getDetail());
			 psmt.setString(13, dto.getStatus());
			 psmt.setString(14, dto.getDuty());
			 psmt.setString(15, dto.getReceipt());
			 psmt.setString(16, dto.getBizType());
			 psmt.setString(17, dto.getOrigin());
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
				dto = getInstance().SelectProductData();
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public void updateProduct(KmProductDTO dto) {} // update 페이지 만들고 추가할게요
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
  
}
