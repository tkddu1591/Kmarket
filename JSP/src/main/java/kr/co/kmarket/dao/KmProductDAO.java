package kr.co.kmarket.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductDTO;

public class KmProductDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertProduct(KmProductDTO dto) {
		try {
			 conn = getConnection();
			 psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			 psmt.setInt(1, dto.getProdCate1());
			 psmt.setInt(2, dto.getProdCate2());
			 psmt.setString(3, dto.getProdName());
			 psmt.setString(4, dto.getDescript());
			 psmt.setString(5, dto.getCompany());
			 psmt.setInt(6, dto.getPrice());
			 psmt.setInt(7, dto.getDiscount());
			 psmt.setInt(8, dto.getPoint());
			 psmt.setInt(9, dto.getStock());
			 psmt.setInt(10, dto.getDelivery());
			 psmt.setString(11, dto.getThumb1());
			 psmt.setString(11, dto.getThumb2());
			 psmt.setString(11, dto.getThumb3());
			 psmt.setString(12, dto.getDetail());
			 psmt.setString(13, dto.getStatus());
			 psmt.setString(14, dto.getDuty());
			 psmt.setString(15, dto.getReceipt());
			 psmt.setString(16, dto.getBizType());
			 psmt.setString(17, dto.getOrigin());
			 psmt.executeUpdate();
			 close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public KmProductDTO selectProduct(String prodNo) {
		KmProductDTO dto = new KmProductDTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCT);
			psmt.setString(1, prodNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
		
	}
	public List<KmProductDTO> selectProducts(int start) {
		
	}
	
	public List<KmProductDTO> selectProducts(String cate, int start) {
		
	}
	
	public void updateProduct(KmProductDTO dto) {}
	public void deleteProdut(int prodNo) {}
	
	public int selectCountProductsTotal() {}
	public int selectCountProductsTotal(String cate) {}
	
}
