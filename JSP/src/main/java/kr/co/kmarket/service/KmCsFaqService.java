package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.KmCsFaqDAO;
import kr.co.kmarket.dto.KmCsFaqDTO;

public enum KmCsFaqService {
	
	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsFaqDAO dao = KmCsFaqDAO.getInstance();
	public int insertCsFaq(KmCsFaqDTO dto) {
		return dao.insertCsFaq(dto);
	}
	
	public KmCsFaqDTO selectCsFaq(String no) {
		return dao.selectCsFaq(no);
	}

	public List<KmCsFaqDTO> selectCsFaqListByCate2(String cate1, String cate2) {
		return dao.selectCsFaqListByCate2(cate1, cate2);
	}
	

	public int updateCsFaq(KmCsFaqDTO dto) {
		return dao.updateCsFaq(dto);
	}
	public void updateHit(String no) {
		dao.updateHit(no);
	}
	public void deleteCsFaq(String no) {
		dao.deleteCsFaq(no);
	}
	public void removeRelatedFaq(String no) {
		dao.removeRelatedFaq(no);
	}
}
