package kr.ac.jejunu.userdao;

import java.sql.*;

public class HallaUserDao extends UserDao {
    ConnectionMaker connectionMaker = new HallaConnectionMaker();

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return connectionMaker.getConnection();
    }
}
