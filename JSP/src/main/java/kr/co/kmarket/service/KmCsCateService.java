package kr.co.kmarket.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.KmCsCateDAO;
import kr.co.kmarket.dto.KmCsCate1DTO;
import kr.co.kmarket.dto.KmCsCate2DTO;

public enum KmCsCateService {
	
	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsCateDAO dao = KmCsCateDAO.getInstance();

	public List<KmCsCate1DTO> selectCSCate1s(int type){
		return dao.selectCSCate1s(type);
	}
	public List<KmCsCate2DTO> selectCSCate2s(int cate1){
		return dao.selectCSCate2s(cate1);
	}

	public String selectCsC1Name(String cate1) {
		return dao.selectCsC1Name(cate1);
	}


	public String selectCsC2Name(String cate1, String cate2) {
		return dao.selectCsC2Name(cate1, cate2);
	}

	
	public Map<String, Object> getCsCates(){

		Map<String, Object> map = new HashMap<>();
		

        List<KmCsCate1DTO> cate1List = selectCSCate1s(0);
        
		List<Map> depth1 = new ArrayList<>();
		Map<Integer, List> depth2 = new HashMap<>();
		

        for (KmCsCate1DTO cate1 : cate1List) {
			//1차 카테고리 List에 MapObject로 저장 
			Map<String, String> depth1_temp = new HashMap<>();
			depth1_temp.put("cate1", cate1.getCate1()+"");
			depth1_temp.put("c1Name", cate1.getC1Name());
			depth1.add(depth1_temp);
			
			//2차 카테고리 data
			List<KmCsCate2DTO> cate2item = selectCSCate2s(cate1.getCate1());
			
			//2차 카테고리 Map에 MapObject로 저장 
			List<Map> depth2_2 = new ArrayList<>();
			Map<String,String> depth2_temp = null;
			for(KmCsCate2DTO dto2 : cate2item) {
				depth2_temp = new HashMap<>();
				depth2_temp.put("cate1", dto2.getCate1()+"");
				depth2_temp.put("cate2", dto2.getCate2()+"");
				depth2_temp.put("c2Name", dto2.getC2Name());
				depth2_2.add(depth2_temp);
			}
			depth2.put(cate1.getCate1(), depth2_2);
		}
			
		map.put("depth1", depth1);
		map.put("depth2", depth2);
		
		return map;
	}
	
}
