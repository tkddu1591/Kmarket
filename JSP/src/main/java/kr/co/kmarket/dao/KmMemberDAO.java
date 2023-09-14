package kr.co.kmarket.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmMemberDTO;

public class KmMemberDAO extends DBHelper{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertMember(KmMemberDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_MEMBER); // 테이블의 모든 칼럼을 파라미터로 생성하고, SQL에 있는 파라미터는 전부 지정해줘야 한다!  
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName()); // name, gender, hp는 null이면 안되므로 registerSellerController에서 속성 정해줘야함
			psmt.setInt(4, dto.getGender());
			psmt.setString(5, dto.getHp());
			psmt.setString(6, dto.getEmail());
			psmt.setInt(7, dto.getType());
			psmt.setInt(8, dto.getPoint());
			psmt.setInt(9, dto.getLevel());
			psmt.setString(10, dto.getZip());
			psmt.setString(11, dto.getAddr1());
			psmt.setString(12, dto.getAddr2());
			psmt.setString(13, dto.getCompany());
			psmt.setString(14, dto.getCeo());
			psmt.setString(15, dto.getBizRegNum());
			psmt.setString(16, dto.getComRegNum());
			psmt.setString(17, dto.getTel());
			psmt.setString(18, dto.getManager());
			psmt.setString(19, dto.getManagerHp());
			psmt.setString(20, dto.getFax());
			psmt.setString(21, dto.getRegIp ());
			psmt.executeUpdate(); // executequery XX 정신챙기자
			close();
		}catch (Exception e) {
			logger.error("insertMember() : " + e.getMessage());
		}
	}
	
	public KmMemberDTO selectMember(String uid, String pass) {
		KmMemberDTO dto = new KmMemberDTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setGender(rs.getInt(4));
				dto.setHp(rs.getString(5));
				dto.setEmail(rs.getString(6));
				dto.setType(rs.getInt(7));
				dto.setPoint(rs.getInt(8));
				dto.setLevel(rs.getInt(9));
				dto.setZip(rs.getString(10));
				dto.setAddr1(rs.getString(11));
				dto.setAddr2(rs.getString(12));
				dto.setCompany(rs.getString(13));
				dto.setCeo(rs.getString(14));
				dto.setBizRegNum(rs.getString(15));
				dto.setComRegNum(rs.getString(16));
				dto.setTel(rs.getString(17));
				dto.setManager(rs.getString(18));
				dto.setManagerHp(rs.getString(19));
				dto.setFax(rs.getString(20));
				dto.setRegIp(rs.getString(21));
				dto.setwDate(rs.getString(22));
				dto.setrDate(rs.getString(23));
				dto.setEtc1(rs.getInt(24));
				dto.setEtc2(rs.getInt(25));
				dto.setEtc3(rs.getString(26));
				dto.setEtc4(rs.getString(27));
				dto.setEtc5(rs.getString(28));
			}
			close();
			
		}catch (Exception e) {
			logger.error("selectMember() : " + e.getMessage());
		}
		return dto;
	}
	
	public List<KmMemberDTO> selectMembers() {
		return null;
	}
	
	public void updateMember(KmMemberDTO dto) {}
	
	public void deleteMember(String uid) {}
	
	// 추가
	public int selectCountUid(String uid) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		}catch (Exception e) {
			logger.error("selectCountUid() : " + e.getMessage());
		}
		
		return result;
	}
	
}
