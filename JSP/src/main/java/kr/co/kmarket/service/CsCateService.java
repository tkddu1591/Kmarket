package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.dao.KmCsCateDAO;
import kr.co.kmarket.dto.KmCsCate1DTO;
import kr.co.kmarket.dto.KmCsCate2DTO;

public enum CsCateService {
	
	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsCateDAO dao = KmCsCateDAO.getInstance();

	public List<KmCsCate1DTO> selectCSCate1s(int type){
		return dao.selectCSCate1s(type);
	}
	public List<KmCsCate2DTO> selectCSCate2s(int cate1){
		return dao.selectCSCate2s(cate1);
	}
	
}
