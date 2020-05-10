package kr.ac.jejunu.userdao;

import java.sql.*;

public class HallaUserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();
        //리턴
        return user;
    }

    public Integer insert(String name, String password) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into userinfo (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        Integer id = resultSet.getInt(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=UTC"
                , "jeju", "jejupw");
    }
}
