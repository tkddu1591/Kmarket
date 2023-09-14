package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmCsCate1DTO;
import kr.co.kmarket.dto.KmCsCate2DTO;

public class KmCsCateDAO extends DBHelper{

	private static KmCsCateDAO instance = new KmCsCateDAO();
	public static KmCsCateDAO getInstance() {
		return instance;
	}
	private KmCsCateDAO() {}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<KmCsCate1DTO> selectCSCate1s(int type){
		
		List<KmCsCate1DTO> cate1List = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			if(type==1) {
				rs = stmt.executeQuery(SQL.SELECT_CSCATE1S_BY_TYPE1);
			} else if(type==2){
				rs = stmt.executeQuery(SQL.SELECT_CSCATE1S_BY_TYPE2);
			}
			
			while(rs.next()) {
				KmCsCate1DTO dto = new KmCsCate1DTO();
				dto.setCate1(rs.getInt(1));
				dto.setC1Name(rs.getString(2));
				cate1List.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("error selectCSCate1s() :" + e.getMessage());
		}
				
		return cate1List;
	}
	
	public List<KmCsCate2DTO> selectCSCate2s(int cate1){
		
		List<KmCsCate2DTO> cate2List = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSCATE2S_BY_CATE1);
			psmt.setInt(1, cate1);
			rs = psmt.executeQuery();

			while(rs.next()) {
				KmCsCate2DTO dto = new KmCsCate2DTO();
				dto.setCate1(rs.getInt(1));
				dto.setCate2(rs.getInt(2));
				dto.setC2Name(rs.getString(3));
				cate2List.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("error selectCSCate2s() :" + e.getMessage());
		}
				
		return cate2List;
	}
}
