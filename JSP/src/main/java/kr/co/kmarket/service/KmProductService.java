package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmProductDAO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;

import java.util.List;

public class KmProductService {
    private static KmProductService INSTANCE = new KmProductService();
    private static KmProductDAO dao = KmProductDAO.getInstance();
    private KmProductService(){}
    public static KmProductService getInstance(){
        return INSTANCE;
    }

    public List<KmProductDTO> selectKmProductsCateL10(KmProductCate2DTO kmProductCate2DTO, int start, String condition){
        return dao.selectKmProductsCateL10(kmProductCate2DTO, start, condition);
    }
    public int selectKmProductsCountCate(String cate1, String cate2){
        return dao.selectKmProductsCountCate(cate1, cate2);
    }
}
