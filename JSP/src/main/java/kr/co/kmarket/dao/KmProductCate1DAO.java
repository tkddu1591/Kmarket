package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductCate1DTO;
import kr.co.kmarket.dto.KmProductCate2DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KmProductCate1DAO extends DBHelper {
    private KmProductCate1DAO() {}
    private static final KmProductCate1DAO INSTANCE = new KmProductCate1DAO();
    public static KmProductCate1DAO getInstance() {
        return INSTANCE;
    }

    public List<KmProductCate1DTO> selectCoates1 (){
        List<KmProductCate1DTO> kmProductcate1DTOS = new ArrayList<>();
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_CATE1S);
            rs = psmt.executeQuery();
            while (rs.next()) {
                KmProductCate1DTO kmProductCate1DTO = new KmProductCate1DTO();
                kmProductCate1DTO.setCate1(rs.getString("cate1"));
                kmProductCate1DTO.setC1Name(rs.getString("c1Name"));
                kmProductcate1DTOS.add(kmProductCate1DTO);
            }
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return kmProductcate1DTOS;
    }
}
