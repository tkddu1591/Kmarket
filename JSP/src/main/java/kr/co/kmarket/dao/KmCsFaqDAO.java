package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.dto.KmCsFaqDTO;
import kr.co.kmarket.db.SQL;

public class KmCsFaqDAO extends DBHelper{
	
	private KmCsFaqDAO() {}
	private static KmCsFaqDAO instance = new KmCsFaqDAO();
	public static KmCsFaqDAO getInstance() {
		return instance;
	}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertCsFaq(KmCsFaqDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작  
			
			stmt = conn.createStatement();
			if(dto.getRelatedFaq() == 0) {
				psmt = conn.prepareStatement(SQL.INSERT_CSFAQ_NOTRELATED);
				psmt.setInt(1, dto.getCate1());
				psmt.setInt(2, dto.getCate2());
				psmt.setString(3, dto.getTitle());
				psmt.setString(4, dto.getContent());
				psmt.setString(5, dto.getWriter());
				psmt.setString(6, dto.getRegip());
			} else {
				psmt = conn.prepareStatement(SQL.INSERT_CSFAQ);
				psmt.setInt(1, dto.getCate1());
				psmt.setInt(2, dto.getCate2());
				psmt.setString(3, dto.getTitle());
				psmt.setString(4, dto.getContent());
				psmt.setInt(5, dto.getRelatedFaq());
				psmt.setString(6, dto.getWriter());
				psmt.setString(7, dto.getRegip());
			}
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_CSFAQ_MAX_NO);
			
			conn.commit();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}

			conn.setAutoCommit(true); 
			close();
		}catch(Exception e){
			logger.error("insertCsFaq() error : " + e.getMessage());
		}
		return no;
	}
	
	public KmCsFaqDTO selectCsFaq(String no) {
		KmCsFaqDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSFAQ);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new KmCsFaqDTO();
				dto.setFaqNo(rs.getInt("faqNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRelatedFaq(rs.getInt("relatedFaq"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				dto.setHit(rs.getInt("hit"));
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsFaqDTO() error : " + e.getMessage());
		}
		return dto;
	}
	

	public List<KmCsFaqDTO> selectCsFaqListByCate2(String cate1, String cate2) {
		List<KmCsFaqDTO> faqList = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSFAQS_BY_CATE2);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				KmCsFaqDTO dto = new KmCsFaqDTO();
				dto.setFaqNo(rs.getInt("faqNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRelatedFaq(rs.getInt("relatedFaq"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				dto.setHit(rs.getInt("hit"));
				faqList.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsFaqList() error : " + e.getMessage());
		}
		return faqList;
	}
	

	public int updateCsFaq(KmCsFaqDTO dto) {
		try { 
			conn = getConnection();
			if(dto.getRelatedFaq() == 0) {
				psmt = conn.prepareStatement(SQL.UPDATE_CSFAQ_NOTRELATED);
				psmt.setInt(1, dto.getCate1());
				psmt.setInt(2, dto.getCate2());
				psmt.setString(3, dto.getTitle());
				psmt.setString(4, dto.getContent());
				psmt.setInt(5, dto.getFaqNo());
			} else {
				psmt = conn.prepareStatement(SQL.UPDATE_CSFAQ);
				psmt.setInt(1, dto.getCate1());
				psmt.setInt(2, dto.getCate2());
				psmt.setString(3, dto.getTitle());
				psmt.setString(4, dto.getContent());
				psmt.setInt(5, dto.getRelatedFaq());
				psmt.setInt(6, dto.getFaqNo());
			}
			psmt.executeUpdate();
			
			close();
		}catch(Exception e){
			logger.error("updateCsFaq() updateCsFaq : " + e.getMessage());
		}
		return dto.getFaqNo();
	}
	public void updateHit(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CSFAQ_HIT);
			psmt.setString(1, no);

			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteCsFaq(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_CSFAQ);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("deleteCsFaq() error : " + e.getMessage() );
		}
	}
	public void removeRelatedFaq(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CSQNA_REMOVE_RELATEDFAQ);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("selectCsQnaCount() error : " + e.getMessage() );
		}
		
	}
}
