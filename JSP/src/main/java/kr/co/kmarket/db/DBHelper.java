package kr.co.kmarket.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;


public class DBHelper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Connection conn = null;
    protected PreparedStatement psmt = null;
    protected Statement stmt = null;
    protected ResultSet rs = null;

    public Connection getConnection() {

        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource ds = (DataSource) ctx.lookup("jdbc/Kmarket");
            conn = ds.getConnection();
        }catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }

    public void close() throws SQLException {
        if(rs != null) {
            rs.close();
        }

        if(stmt != null) {
            stmt.close();
        }

        if(psmt != null) {
            psmt.close();
        }

        if(conn != null) {
            conn.close();
        }
    }
}
