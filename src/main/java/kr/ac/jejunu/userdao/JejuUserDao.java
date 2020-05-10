package kr.ac.jejunu.userdao;

import java.sql.*;

public class JejuUserDao extends UserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=UTC"
                , "jeju", "jejupw");
    }
}
