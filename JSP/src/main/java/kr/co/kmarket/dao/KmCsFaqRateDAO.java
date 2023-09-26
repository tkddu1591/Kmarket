package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmCsFaqRateDTO;

public class KmCsFaqRateDAO extends DBHelper{
	
	private KmCsFaqRateDAO() {}
	private static KmCsFaqRateDAO instance = new KmCsFaqRateDAO();
	public static KmCsFaqRateDAO getInstance() {
		return instance;
	}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertCsFaqRate(KmCsFaqRateDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			
			psmt = conn.prepareStatement(SQL.INSERT_CSFAQRATE);
			psmt.setInt(1, dto.getFaqNo());
			psmt.setString(2, dto.getUid());
			psmt.setInt(3, dto.getRate());
			no = psmt.executeUpdate();			

			close();
		}catch(Exception e){
			logger.error("insertCsFaqRate() error : " + e.getMessage());
		}
		return no;
	}
	
	public KmCsFaqRateDTO selectCsFaqRate(String no, String uid) {
		KmCsFaqRateDTO dto  = new  KmCsFaqRateDTO();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSFAQRATE);
			psmt.setString(1, no);
			psmt.setString(2, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setFaqNo(rs.getInt("faqNo"));
				dto.setUid(rs.getString("uid"));
				dto.setRate(rs.getInt("rate"));
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsFaqRate() error : " + e.getMessage());
		}
		return dto;
	}
	

	public int[] selectCsFaqRates(String no) {
		int[] rates = new int[2];
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSFAQRATES_BY_NO);
			psmt.setString(1, no);			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				rates[0] = rs.getInt("Y");
				rates[1] = rs.getInt("N");
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsFaqRates() error : " + e.getMessage());
		}
		return rates;
	}
	

	public int updateCsFaqRate(KmCsFaqRateDTO dto) {
		int result = 0;
		try { 
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CSFAQRATE);
			psmt.setInt(1, dto.getRate());
			psmt.setInt(2, dto.getFaqNo());
			psmt.setString(3, dto.getUid());
			result = psmt.executeUpdate();
			
			close();
		}catch(Exception e){
			logger.error("updateCsFaqRate() error : " + e.getMessage());
		}
		return result;
	}
	public int deleteCsFaqRate(KmCsFaqRateDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.DELETE_CSFAQRATE);
			psmt.setInt(1, dto.getFaqNo());
			psmt.setString(2, dto.getUid());
			no = psmt.executeUpdate();			
			
			close();
		}catch(Exception e){
			logger.error("deleteCsFaqRate() error : " + e.getMessage());
		}
		return no;
	}
}
