package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    DataSource dataSource;

    UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Integer id) throws SQLException {
        Object[] objects = { id };
        StatementStrategy statementStrategy = new GetStatementStrategy(objects);

        return jdbcContextForGet(statementStrategy);
    }

    public Integer insert(String name, String password) throws SQLException {
        Object[] objects = { name, password };
        StatementStrategy statementStrategy = new InsertStatementStrategy(objects);
        return jdbcContextForInsert(statementStrategy);
    }

    public void update(User user) throws SQLException {
        Object[] objects = { user.getName(), user.getPassword(), user.getId() };
        StatementStrategy statementStrategy = new UpdateStatementStrategy(objects);

        jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        Object[] objects = { id };
        StatementStrategy statementStrategy = new DeleteStatementStrategy(objects);

        jdbcContextForUpdate(statementStrategy);
    }

    private User jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return user;
    }

    private Integer jdbcContextForInsert(StatementStrategy statementStrategy) throws SQLException {
        int id;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            id = resultSet.getInt(1);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return id;
    }

    private void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.execute();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
