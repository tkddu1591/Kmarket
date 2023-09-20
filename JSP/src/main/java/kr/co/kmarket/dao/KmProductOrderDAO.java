package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmProductOrderDTO;

import java.sql.SQLException;

public class KmProductOrderDAO extends DBHelper {
    private static final KmProductOrderDAO INSTANCE = new KmProductOrderDAO();
    private KmProductOrderDAO() {}
    public static KmProductOrderDAO getInstance() {
        return INSTANCE;
    }

    public int selectLastOrderNo() {
        int result = 0;
        conn = getConnection();
        try {
            psmt = conn.prepareStatement(SQL.SELECT_LAST_ORDERNO);
            rs = psmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void insertOrder(KmProductOrderDTO productOrderDTO) {
        conn = getConnection();
        try {
            psmt = conn.prepareStatement(SQL.INSERT_ORDER);
            psmt.setString(1, productOrderDTO.getOrdUid());
            psmt.setInt(2, productOrderDTO.getOrdCount());
            psmt.setInt(3, productOrderDTO.getOrdPrice());
            psmt.setInt(4, productOrderDTO.getOrdDiscount());
            psmt.setInt(5, productOrderDTO.getOrdDelivery());
            psmt.setInt(6, productOrderDTO.getSavePoint());
            psmt.setInt(7, productOrderDTO.getUsedPoint());
            psmt.setInt(8, productOrderDTO.getOrdTotPrice());
            psmt.setString(9, productOrderDTO.getRecipName());
            psmt.setString(10, productOrderDTO.getRecipHp());
            psmt.setString(11, productOrderDTO.getRecipZip());
            psmt.setString(12, productOrderDTO.getRecipAddr1());
            psmt.setString(13, productOrderDTO.getRecipAddr2());
            psmt.setInt(14, productOrderDTO.getOrdPayment());
            psmt.setInt(15, productOrderDTO.getOrdComplete());
            psmt.executeUpdate();
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
