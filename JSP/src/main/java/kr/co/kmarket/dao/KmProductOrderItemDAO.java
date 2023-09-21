package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductOrderItemDTO;

import java.sql.SQLException;

public class KmProductOrderItemDAO extends DBHelper {
    private KmProductOrderItemDAO() {}
    private static KmProductOrderItemDAO INSTANCE = new KmProductOrderItemDAO();
    public static KmProductOrderItemDAO getInstance() {
        return INSTANCE;
    }

    public void insertKmProductOrderItem(KmProductOrderItemDTO kmProductOrderItemDTO) {
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(SQL.INSERT_ORDER_ITEM);
            psmt.setInt(1, kmProductOrderItemDTO.getOrdNo());
            psmt.setInt(2, kmProductOrderItemDTO.getProdNo());
            psmt.setInt(3, kmProductOrderItemDTO.getCount());
            psmt.setInt(4, kmProductOrderItemDTO.getPrice());
            psmt.setInt(5, kmProductOrderItemDTO.getDiscount());
            psmt.setInt(6, kmProductOrderItemDTO.getPoint());
            psmt.setInt(7, kmProductOrderItemDTO.getDelivery());
            psmt.setInt(8, kmProductOrderItemDTO.getTotal());
            psmt.executeUpdate();

            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
