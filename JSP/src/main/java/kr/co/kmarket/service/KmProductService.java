package kr.co.kmarket.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.KmProductDAO;
import kr.co.kmarket.dto.KmProductDTO;

public enum KmProductService {
	
	INSTATNCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private KmProductDAO dao = new KmProductDAO();
	
	public void insertProduct(KmProductDTO dto) {
			dao.insertProduct(dto);
	}
	public KmProductDTO selectProduct(String prodNo) {
			return dao.selectProduct(pNo);
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
	
	
	public int selectCountProductsTotal() {}
	public int selectCountProductsTotal(String cate) {}
	
	
	// 파일명수정
	// 파일업로드
	
}
