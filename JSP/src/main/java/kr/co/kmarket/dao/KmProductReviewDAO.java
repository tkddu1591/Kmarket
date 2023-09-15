package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductReviewDTO;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KmProductReviewDAO extends DBHelper {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(KmProductReviewDAO.class);

    private static KmProductReviewDAO INSTANCE = new KmProductReviewDAO();

    private KmProductReviewDAO() {
    }

    public static KmProductReviewDAO getInstance() {
        return INSTANCE;
    }


    public List<KmProductReviewDTO> selectProductReviews(String prodNo, int start) {
        List<KmProductReviewDTO> kmProductReviewDTOS = new ArrayList<>();
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_REVIEWS_L5);
            psmt.setString(1, prodNo);
            psmt.setInt(2, start);
            rs= psmt.executeQuery();
            while (rs.next()){
                KmProductReviewDTO kmProductReviewDTO = new KmProductReviewDTO();
                kmProductReviewDTO = selectProductReviewData();
                kmProductReviewDTOS.add(kmProductReviewDTO);
            }
            close();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return kmProductReviewDTOS;

    }
    private KmProductReviewDTO selectProductReviewData() throws SQLException {
        KmProductReviewDTO kmProductReviewDTO = new KmProductReviewDTO();
        kmProductReviewDTO.setRevNo(rs.getInt("revNo"));
        kmProductReviewDTO.setProdNo(rs.getInt("prodNo"));
        kmProductReviewDTO.setContent(rs.getString("content"));
        kmProductReviewDTO.setUid(rs.getString("uid"));
        kmProductReviewDTO.setRating(rs.getInt("rating"));
        kmProductReviewDTO.setRegIp(rs.getString("regIp"));
        kmProductReviewDTO.setrDate(rs.getString("rDate"));
        return kmProductReviewDTO;
    }

    public int selectProductReviewsCount(int prodNo) {
        int count = 0;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(SQL.SELECT_COUNT_REVIEWS_PNO);
            psmt.setInt(1, prodNo);
            rs= psmt.executeQuery();
            while (rs.next()){
                count = rs.getInt(1);
            }
            close();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return count;
    }
}
