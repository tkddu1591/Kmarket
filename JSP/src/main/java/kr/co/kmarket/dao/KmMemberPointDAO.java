package kr.co.kmarket.dao;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;

import java.sql.SQLException;

public class KmMemberPointDAO extends DBHelper {
    private static KmMemberPointDAO instance = new KmMemberPointDAO();
    private KmMemberPointDAO() {}
    public static KmMemberPointDAO getInstance() {
        return instance;
    }


    public void insertKmMemberPoint(int point, int lastOrderNo, String ordUid) {
        try {
            conn= getConnection();
            psmt = conn.prepareStatement(SQL.INSERT_POINT);
            psmt.setString(1, ordUid);
            psmt.setInt(2, lastOrderNo);
            psmt.setInt(3, point);
            psmt.executeUpdate();
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
