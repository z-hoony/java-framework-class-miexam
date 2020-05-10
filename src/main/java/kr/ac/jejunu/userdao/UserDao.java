package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        Object[] objects = { id };
        StatementStrategy statementStrategy = new GetStatementStrategy(objects);
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Integer insert(String name, String password) throws SQLException {
        Object[] objects = { name, password };
        StatementStrategy statementStrategy = new InsertStatementStrategy(objects);
        return jdbcContext.jdbcContextForInsert(statementStrategy);
    }

    public void update(User user) throws SQLException {
        Object[] objects = { user.getName(), user.getPassword(), user.getId() };
        StatementStrategy statementStrategy = new UpdateStatementStrategy(objects);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        Object[] objects = { id };
        StatementStrategy statementStrategy = new DeleteStatementStrategy(objects);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
