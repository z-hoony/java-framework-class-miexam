package kr.ac.jejunu.userdao;


import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    String name = "hulk";
    String password = "1234";

    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void testGet() throws SQLException {
        Integer id = 1;
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testInsert() throws SQLException {
        Integer id = userDao.insert(name, password);

        assertThat(id, greaterThan(0));

        User user = userDao.get(id);

        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testUpdate() throws SQLException {
        String newName = "jade";
        String newPassword = "2345";

        Integer id = userDao.insert(name, password);
        User user = userDao.get(id);

        user.setName(newName);
        user.setPassword(newPassword);

        userDao.update(user);

        User updatedUser = userDao.get(id);

        assertThat(updatedUser.getName(), is(newName));
        assertThat(updatedUser.getPassword(), is(newPassword));
    }

    @Test
    public void testDelete() throws SQLException {
        Integer id = userDao.insert(name, password);
        userDao.delete(id);

        User deletedUser = userDao.get(id);
        assertThat(deletedUser, IsNull.nullValue());
    }
}
