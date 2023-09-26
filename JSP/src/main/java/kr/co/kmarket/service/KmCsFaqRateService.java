package kr.co.kmarket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.KmCsFaqRateDAO;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmCsFaqRateDTO;

public enum KmCsFaqRateService {
	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsFaqRateDAO dao = KmCsFaqRateDAO.getInstance();
	
	public int insertCsFaqRate(KmCsFaqRateDTO dto) {
		return dao.insertCsFaqRate(dto);
	}
	
	public KmCsFaqRateDTO selectCsFaqRate(String no, String uid) {
		return dao.selectCsFaqRate(no, uid);
	}
	

	public int[] selectCsFaqRates(String no) {
		return dao.selectCsFaqRates(no);
	}
	

	public int updateCsFaqRate(KmCsFaqRateDTO dto) {
		return dao.updateCsFaqRate(dto);
	}
	public int deleteCsFaqRate(KmCsFaqRateDTO dto) {
		return dao.deleteCsFaqRate(dto);
	}
}
