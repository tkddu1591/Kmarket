package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.db.SQL;

public class KmCsQnaDAO extends DBHelper{
	
	private KmCsQnaDAO() {}
	private static KmCsQnaDAO instance = new KmCsQnaDAO();
	public static KmCsQnaDAO getInstance() {
		return instance;
	}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertCsQna(KmCsQnaDTO dto) {
		int no = 0;
		try {
			// 트랜잭션을 묶어두니 [ Lock wait timeout exceeded; try restarting transaction ] 오류가 뜬다... 
			conn = getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작  
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_CSQNA_QUESTION);
			psmt.setInt(1, dto.getCate1());
			psmt.setInt(2, dto.getCate2());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getFile1());
			psmt.setString(6, dto.getFile2());
			psmt.setString(7, dto.getFile3());
			psmt.setString(8, dto.getFile4());
			psmt.setString(9, dto.getWriter());
			psmt.setString(10, dto.getOrdNo());
			psmt.setString(11, dto.getProdNo());
			psmt.setInt(12, dto.getParent());
			psmt.setInt(13, dto.getAnswerComplete());
			psmt.setString(14, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_CSQNA_MAX_NO);
			
			conn.commit();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}

			conn.setAutoCommit(true); 
			close();
		}catch(Exception e){
			logger.error("insertQna() error : " + e.getMessage());
		}
		return no;
	}
	public int insertCsQnaAnswer(KmCsQnaDTO dto) {
		logger.debug(dto.toString());
		int result = 0;
		try { 
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_CSQNA_ANSWER);
			psmt.setString(1, dto.getContent());
			psmt.setString(2,dto.getWriter());
			psmt.setInt(3, dto.getParent());
			psmt.setString(4,dto.getRegip());
			result = psmt.executeUpdate();
			
			close();
		}catch(Exception e){
			logger.error("insertCsQnaAnswer() error : " + e.getMessage());
		}
		return result;
	}
	
	public KmCsQnaDTO selectCsQna(String no) {
		KmCsQnaDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSQNA);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new KmCsQnaDTO();
				dto.setQnaNo(rs.getInt("qnaNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile1(rs.getString("file1"));
				dto.setFile2(rs.getString("file2"));
				dto.setFile3(rs.getString("file3"));
				dto.setFile4(rs.getString("file4"));
				dto.setWriter(rs.getString("writer"));
				dto.setOrdNo(rs.getString("ordNo"));
				dto.setProdNo(rs.getString("prodNo"));
				dto.setParent(rs.getString("parent"));
				dto.setAnswerComplete(rs.getString("answerComplete"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setWriterName(rs.getString("name"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsQnaDTO() error : " + e.getMessage());
		}
		return dto;
	}
	public KmCsQnaDTO selectCsQnaAnswer(String parent) {
		KmCsQnaDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSQNA_ANSWER);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new KmCsQnaDTO();
				dto.setQnaNo(rs.getInt("qnaNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile1(rs.getString("file1"));
				dto.setFile2(rs.getString("file2"));
				dto.setFile3(rs.getString("file3"));
				dto.setFile4(rs.getString("file4"));
				dto.setWriter(rs.getString("writer"));
				dto.setOrdNo(rs.getString("ordNo"));
				dto.setProdNo(rs.getString("prodNo"));
				dto.setParent(rs.getString("parent"));
				dto.setAnswerComplete(rs.getString("answerComplete"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setWriterName(rs.getString("name"));
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsQnaAnswer() error : " + e.getMessage());
		}
		return dto;
	}

	public List<KmCsQnaDTO> selectCsQnaList(String cate1, int start) {
		List<KmCsQnaDTO> qnaList = new ArrayList<>();
		try {
			conn = getConnection();
			if(cate1.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_CSQNAS);
				psmt.setInt(1, start);
			} else {
				psmt = conn.prepareStatement(SQL.SELECT_CSQNAS_BY_CATE1);
				psmt.setString(1, cate1);
				psmt.setInt(2, start);
			}
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				KmCsQnaDTO dto = new KmCsQnaDTO();
				dto.setQnaNo(rs.getInt("qnaNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile1(rs.getString("file1"));
				dto.setFile2(rs.getString("file2"));
				dto.setFile3(rs.getString("file3"));
				dto.setFile4(rs.getString("file4"));
				dto.setWriter(rs.getString("writer"));
				dto.setOrdNo(rs.getString("ordNo"));
				dto.setProdNo(rs.getString("prodNo"));
				dto.setParent(rs.getString("parent"));
				dto.setAnswerComplete(rs.getString("answerComplete"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setWriterName(rs.getString("name"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				qnaList.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsQnaDTO() error : " + e.getMessage());
		}
		return qnaList;
	}

	public List<KmCsQnaDTO> selectCsQnaListForAdmin(String cate1, String cate2, int start) {
		List<KmCsQnaDTO> qnaList = new ArrayList<>();
		try {
			conn = getConnection();
			String sqlString = SQL.getSelectCsQnasL10(cate1, cate2, start);
			psmt = conn.prepareStatement(sqlString);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				KmCsQnaDTO dto = new KmCsQnaDTO();
				dto.setQnaNo(rs.getInt("qnaNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile1(rs.getString("file1"));
				dto.setFile2(rs.getString("file2"));
				dto.setFile3(rs.getString("file3"));
				dto.setFile4(rs.getString("file4"));
				dto.setWriter(rs.getString("writer"));
				dto.setOrdNo(rs.getString("ordNo"));
				dto.setProdNo(rs.getString("prodNo"));
				dto.setParent(rs.getString("parent"));
				dto.setAnswerComplete(rs.getString("answerComplete"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setWriterName(rs.getString("name"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				qnaList.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsQnaDTO() error : " + e.getMessage());
		}
		return qnaList;
	}
	public int selectCsQnaCountForAdmin(String cate1, String cate2) {
		int count = 0;
		try {
			conn = getConnection();
			String sqlString = SQL.getSelectCsQnaCount(cate1, cate2);
			psmt = conn.prepareStatement(sqlString);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsQnaCount() error : " + e.getMessage() );
		}
		return count;
	}
	public int selectCsQnaCount(String cate1) {
		int count = 0;
		try {
			conn = getConnection();
			if(cate1.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_CSQNA_COUNT);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_CSQNA_COUNT_BY_CATE1);
				psmt.setString(1, cate1);
			}
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCsQnaCount() error : " + e.getMessage() );
		}
		return count;
	}

	public List<KmCsQnaDTO> selectLatests(int size){
		List<KmCsQnaDTO> latests = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CSQNA_LATESTS);
			psmt.setInt(1, size);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				KmCsQnaDTO dto = new KmCsQnaDTO();
				dto.setQnaNo(rs.getInt("qnaNo"));
				dto.setCate1(rs.getInt("cate1"));
				dto.setCate2(rs.getInt("cate2"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile1(rs.getString("file1"));
				dto.setFile2(rs.getString("file2"));
				dto.setFile3(rs.getString("file3"));
				dto.setFile4(rs.getString("file4"));
				dto.setWriter(rs.getString("writer"));
				dto.setOrdNo(rs.getString("ordNo"));
				dto.setProdNo(rs.getString("prodNo"));
				dto.setParent(rs.getString("parent"));
				dto.setAnswerComplete(rs.getString("answerComplete"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setWriterName(rs.getString("name"));
				dto.setC1Name(rs.getString("c1Name"));
				dto.setC2Name(rs.getString("c2Name"));
				latests.add(dto);
				//logger.debug("QnaDTO : " + dto.toString());
			}
			close();
		}catch (Exception e) {
			logger.error("selectLatest() error : " + e.getMessage());
		}
		
		return latests;
	}

	public int updateCsQna(KmCsQnaDTO dto) {
		try { 
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CSQNA);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getFile1());
			psmt.setString(4, dto.getFile2());
			psmt.setString(5, dto.getFile3());
			psmt.setString(6, dto.getFile4());
			psmt.setString(7, dto.getOrdNo());
			psmt.setString(8, dto.getProdNo());
			psmt.setInt(9, dto.getQnaNo());
			psmt.executeUpdate();
			
			close();
		}catch(Exception e){
			logger.error("insertQna() updateCsQna : " + e.getMessage());
		}
		return dto.getQnaNo();
	}
	public int updateCsQnaAnswer(KmCsQnaDTO dto) {
		int result = 0;
		try { 
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CSQNA_ANSWER);
			psmt.setString(1, dto.getContent());
			psmt.setInt(2, dto.getParent());
			result = psmt.executeUpdate();
			
			close();
		}catch(Exception e){
			logger.error("insertQna() updateCsQna : " + e.getMessage());
		}
		return result;
	}

	public int updateCsQnaAnswerComplete(String no, String status) {
		int result = 0;
		try { 
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CSQNA_ANSWERCOMPLETE);
			psmt.setString(1, status);
			psmt.setString(2, no);
			result = psmt.executeUpdate();
			
			close();
		}catch(Exception e){
			logger.error("insertQna() updateCsQna : " + e.getMessage());
		}
		return result;
	}
	public void deleteCsQna(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_CSQNA);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("selectCsQnaCount() error : " + e.getMessage() );
		}
	}
}
