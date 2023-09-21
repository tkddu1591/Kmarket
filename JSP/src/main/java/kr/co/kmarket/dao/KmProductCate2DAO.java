package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductCate2DTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            if ((kmProductCate2DTO.getCate2()) == 0) {
                psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_CATE1_NAME);
            }else {
                psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_CATE12_NAME);
                psmt.setInt(2, kmProductCate2DTO.getCate2());
            }
            psmt.setInt(1, kmProductCate2DTO.getCate1());
            rs = psmt.executeQuery();
            while (rs.next()) {
                dto.setC1Name(rs.getString("c1Name"));
                if (kmProductCate2DTO.getCate2() != 0) {
                    dto.setC2Name(rs.getString("c2Name"));
                }
            }
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public List<KmProductCate2DTO> selectCoates2(){
        List<KmProductCate2DTO> kmProductCate2DTOS = new ArrayList<>();
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_CATE2S);
            rs = psmt.executeQuery();
            while (rs.next()) {
                KmProductCate2DTO kmProductCate2DTO = new KmProductCate2DTO();
                kmProductCate2DTO.setCate1(rs.getString("cate1"));
                kmProductCate2DTO.setCate2(rs.getString("cate2"));
                kmProductCate2DTO.setC2Name(rs.getString("c2Name"));
                kmProductCate2DTOS.add(kmProductCate2DTO);
            }
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return kmProductCate2DTOS;
    }

}
