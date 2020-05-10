package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        Object[] objects = { id };
        StatementStrategy statementStrategy = connection -> {
            String sql = "select * from userinfo where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Integer insert(String name, String password) throws SQLException {
        Object[] objects = { name, password };
        StatementStrategy statementStrategy = connection -> {
            String sql = "insert into userinfo (name, password) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForInsert(statementStrategy);
    }

    public void update(User user) throws SQLException {
        Object[] objects = { user.getName(), user.getPassword(), user.getId() };
        StatementStrategy statementStrategy = connection -> {
            String sql = "update userinfo set name = ?, password = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        Object[] objects = { id };
        StatementStrategy statementStrategy = connection -> {
            String sql = "delete from userinfo where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
