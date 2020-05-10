package kr.ac.jejunu.userdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserDao {
    private final JdbcTemplate jdbcContext;

    UserDao(JdbcTemplate jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) {
        Object[] params = { id };
        String sql = "select * from userinfo where id = ?";
        return jdbcContext.query(sql, params, rs -> {
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        });
    }

    public Integer insert(String name, String password) {
        Object[] params = { name, password };
        String sql = "insert into userinfo (name, password) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcContext.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(User user) {
        Object[] params = { user.getName(), user.getPassword(), user.getId() };
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        jdbcContext.update(sql, params);
    }

    public void delete(Integer id) {
        Object[] params = { id };
        String sql = "delete from userinfo where id = ?";
        jdbcContext.update(sql, params);
    }
}
