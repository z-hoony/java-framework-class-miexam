package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        Object[] objects = { id };
        String sql = "select * from userinfo where id = ?";
        return jdbcContext.get(sql, objects);
    }

    public Integer insert(String name, String password) throws SQLException {
        Object[] objects = { name, password };
        String sql = "insert into userinfo (name, password) values (?, ?)";
        return jdbcContext.insert(sql, objects);
    }

    public void update(User user) throws SQLException {
        Object[] objects = { user.getName(), user.getPassword(), user.getId() };
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        jdbcContext.update(sql, objects);
    }

    public void delete(Integer id) throws SQLException {
        Object[] objects = { id };
        String sql = "delete from userinfo where id = ?";
        jdbcContext.update(sql, objects);
    }
}
