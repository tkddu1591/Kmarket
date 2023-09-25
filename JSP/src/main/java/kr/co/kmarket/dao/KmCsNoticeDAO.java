package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.dto.KmCsNoticeDTO;
import kr.co.kmarket.db.SQL;

public class KmCsNoticeDAO extends DBHelper{
	
	private KmCsNoticeDAO() {}
	private static KmCsNoticeDAO instance = new KmCsNoticeDAO();
	public static KmCsNoticeDAO getInstance() {
		return instance;
	}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertCsNotice(KmCsNoticeDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작  
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_CSNOTICE);
			psmt.setInt(1, dto.getCate1());
			psmt.setInt(2, dto.getCate2());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_CSNOTICE_MAX_NO);
			
			conn.commit();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}

			conn.setAutoCommit(true); 
			close();
		}catch(Exception e){
			logger.error("insertCsNotice() error : " + e.getMessage());
		}
		return no;
	}
	
	public KmCsNoticeDTO selectCsNotice(String no) {
		KmCsNoticeDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSNOTICE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new KmCsNoticeDTO();
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				dto.setHit(rs.getInt("hit"));
				logger.debug(dto.toString());
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsNoticeDTO() error : " + e.getMessage());
		}
		return dto;
	}
	

	public List<KmCsNoticeDTO> selectCsNoticeList(String cate1, int start) {
		List<KmCsNoticeDTO> noticeList = new ArrayList<>();
		try {
			conn = getConnection();
			if(cate1.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_CSNOTICES);
				psmt.setInt(1, start);
			} else {
				psmt = conn.prepareStatement(SQL.SELECT_CSNOTICES_BY_CATE1);
				psmt.setString(1, cate1);
				psmt.setInt(2, start);
			}
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				KmCsNoticeDTO dto = new KmCsNoticeDTO();
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				dto.setHit(rs.getInt("hit"));
				noticeList.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsNoticeList() error : " + e.getMessage());
		}
		return noticeList;
	}
	public List<KmCsNoticeDTO> selectCsNoticeListByAjax(String cate1, String keyword, int start){
		List<KmCsNoticeDTO> noticeList = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String stmtSQL = SQL.changeSelectCsNoticesL10(cate1, keyword, start);
			rs = stmt.executeQuery(stmtSQL);

			//logger.debug("SQL : " + stmtSQL);
			while(rs.next()) {
				KmCsNoticeDTO dto = new KmCsNoticeDTO();
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				dto.setHit(rs.getInt("hit"));
				noticeList.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsNoticeList() error : " + e.getMessage());
		}
		return noticeList;
	}
	public int selectCsNoticeCount(String cate1) {
		int count = 0;
		try {
			conn = getConnection();
			if(cate1.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_CSNOTICE_COUNT);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_CSNOTICE_COUNT_BY_CATE1);
				psmt.setString(1, cate1);
			}
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsNoticeCount() error : " + e.getMessage() );
		}
		return count;
	}
	public int selectCsNoticeCountByAjax(String cate1, String keyword) {
		int count = 0;
		try {

			conn = getConnection();
			stmt = conn.createStatement();
			String stmtSQL = SQL.changeSelectCsNoticesCountL10(cate1, keyword);
			rs = stmt.executeQuery(stmtSQL);

			//logger.debug("SQL : " + stmtSQL);
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsNoticeCount() error : " + e.getMessage() );
		}
		return count;
	}
	public List<KmCsNoticeDTO> selectLatests(int size){
		List<KmCsNoticeDTO> latests = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSNOTICE_LATEST);
			psmt.setInt(1, size);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				KmCsNoticeDTO dto = new KmCsNoticeDTO();
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				dto.setHit(rs.getInt("hit"));
				latests.add(dto);
				//logger.debug("noticeDTO : " + dto.toString());
			}
			close();
		}catch (Exception e) {
			logger.error("selectLatest() error : " + e.getMessage());
		}
		return latests;
	}

	public int updateCsNotice(KmCsNoticeDTO dto) {
		try { 
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CSNOTICE);
			psmt.setInt(1, dto.getCate1());
			psmt.setInt(2, dto.getCate2());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setInt(5, dto.getNoticeNo());
			psmt.executeUpdate();
			
			close();
		}catch(Exception e){
			logger.error("updateCsNotice() updateCsNotice : " + e.getMessage());
		}
		return dto.getNoticeNo();
	}

	public void updateHit(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CSNOTICE_HIT);
			psmt.setString(1, no);

			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCsNotice(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_CSNOTICE);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("deleteCsNotice() error : " + e.getMessage() );
		}
	}
}
