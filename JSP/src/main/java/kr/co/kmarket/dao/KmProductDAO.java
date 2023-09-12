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

    /*condition
     * 10의자리= 조건(1: 판매건수, 2:상품가격, 3:상품평점, 4:상품리뷰, 5:등록날짜
     * 1의자리 = 높은순(0), 낮은순(1)
     * */
    public List<KmProductDTO> selectKmProductsCateL10(KmProductCate2DTO kmProductCate2DTO, int start, String condition) {
        List<KmProductDTO> kmProducts = new ArrayList<KmProductDTO>();
        try {

            conn = getConnection();
            logger.info(condition);
            SQL.changeSelectProductCateL10Condition(condition);
            logger.info(SQL.SELECT_PRODUCTS_CATE_L10.get(0));
            psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_CATE_L10.get(0));

            psmt.setString(1, kmProductCate2DTO.getCate1());
            psmt.setString(2, kmProductCate2DTO.getCate2());
            psmt.setInt(3, start);

            rs = psmt.executeQuery();
            while (rs.next()) {
                KmProductDTO kmProduct = new KmProductDTO();

                kmProduct = getInstance().SelectProductData();

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
        kmProduct.setTotal((kmProduct.getPrice()*(100-kmProduct.getDiscount()))/100);
        return kmProduct;
    }

}
