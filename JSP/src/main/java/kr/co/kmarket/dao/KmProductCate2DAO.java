package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductCate2DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KmProductCate2DAO extends DBHelper {

    private static KmProductCate2DAO INSTANCE = new KmProductCate2DAO();

    private void KmCsCate2DAO() {
    }

    public static KmProductCate2DAO getInstance() {
        return INSTANCE;
    }

    public KmProductCate2DTO selectCateName(KmProductCate2DTO kmProductCate2DTO) {
        KmProductCate2DTO dto = new KmProductCate2DTO();
        try {
            conn = getConnection();
            if (kmProductCate2DTO.getCate2() == null || kmProductCate2DTO.getCate2().equals("")) {
                psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_CATE1_NAME);
            }else {
                psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_CATE12_NAME);
                psmt.setString(2, kmProductCate2DTO.getCate2());
            }
            psmt.setString(1, kmProductCate2DTO.getCate1());
            rs = psmt.executeQuery();
            while (rs.next()) {
                dto.setC1Name(rs.getString("c1Name"));
                if (kmProductCate2DTO.getCate2() != null && !kmProductCate2DTO.getCate2().equals("")) {
                    dto.setC2Name(rs.getString("c2Name"));
                }
            }
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }


}
