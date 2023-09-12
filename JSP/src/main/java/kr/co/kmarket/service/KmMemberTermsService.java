package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmMemberTermsDAO;
import kr.co.kmarket.dto.KmMemberTermsDTO;

public enum KmMemberTermsService {
	instance;
	
	private KmMemberTermsDAO dao = new KmMemberTermsDAO();
	
	public KmMemberTermsDTO selectTerms() {
		return dao.selectTerms();
	}
}
