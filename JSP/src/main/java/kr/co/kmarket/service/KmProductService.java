package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmProductDAO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KmProductService {
  
  
    private Logger logger = LoggerFactory.getLogger(this.getClass());
  
    private static KmProductService INSTANCE = new KmProductService();
    private static KmProductDAO dao = KmProductDAO.getInstance();
    private KmProductService(){}
    public static KmProductService getInstance(){
        return INSTANCE;
    }

    // 0912 상엽님
    public List<KmProductDTO> selectKmProductsCateL10(KmProductCate2DTO kmProductCate2DTO, int start, String condition){
        return dao.selectKmProductsCateL10(kmProductCate2DTO, start, condition);
    }
    public int selectKmProductsCountCate(String cate1, String cate2){
        return dao.selectKmProductsCountCate(cate1, cate2);
    }
  
  
    //0912 수현님

    public void insertProduct(KmProductDTO dto) {
        dao.insertProduct(dto);
    }
    public KmProductDTO selectProduct(String prodNo) {
        return dao.selectProduct(prodNo);
    }
    public List<KmProductDTO> selectProducts(int start) {
        return dao.selectProducts(start);
    }
    public List<KmProductDTO> selectProducts(String cate, int start) {
        return dao.selectProducts(cate, start);
    }
    public void updateProduct(KmProductDTO dto) {
        dao.updateProduct(dto);
    }
    public void deleteProduct(int prodNo) {
        dao.deleteProduct(prodNo);
    }


    public int selectCountProductsTotal() {
    	return dao.selectCountProductsTotal();
    }
    public int selectCountProductsTotal(String cate) {
    	return dao.selectCountProductsTotal(cate);
    }


    // 파일명수정
    // 파일업로드
}
