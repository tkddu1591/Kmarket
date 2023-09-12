package kr.co.kmarket.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmMemberTermsDTO;

public class KmMemberTermsDAO extends DBHelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public KmMemberTermsDTO selectTerms() {
		
		KmMemberTermsDTO dto = new KmMemberTermsDTO();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			
			if(rs.next()) {
				 dto.setTerms(rs.getString(1));
				 dto.setPrivacy(rs.getString(2));
				 dto.setLocation(rs.getString(3));
				 dto.setFinance(rs.getString(4));
				 dto.setTax(rs.getString(5));
			}
			close();
		}catch (Exception e) {
			logger.error("selectTerms() : " + e.getMessage());
		}
		
		return dto;
	}
}
