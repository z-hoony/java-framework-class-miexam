package kr.ac.jejunu.userdao;

public class UserDao {
    private final JejuJdbcTemplate jdbcContext;

    UserDao(JejuJdbcTemplate jdbcContext) {
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
        return jdbcContext.insert(sql, params);
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
