package kr.ac.jejunu.userdao;

import java.sql.*;

public class JejuUserDao extends UserDao {
    ConnectionMaker connectionMaker = new JejuConnectionMaker();

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return connectionMaker.getConnection();
    }
}
