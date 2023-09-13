package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmProductReviewDAO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.dto.KmProductReviewDTO;

import java.util.List;

public enum KmProductReviewService {
    INSTANCE;

    private static KmProductReviewDAO dao = KmProductReviewDAO.getInstance();

    public static List<KmProductReviewDTO> selectKmProductReviews(String prodNo) {
        return dao.selectProductReviews(prodNo);
    }

    public int selectKmProductReviewsCount(String prodNo) {
        return dao.selectProductReviewsCount(prodNo);
    }
}
