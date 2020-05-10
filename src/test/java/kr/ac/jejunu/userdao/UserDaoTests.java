package kr.ac.jejunu.userdao;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    ConnectionMaker jejuConnectionMaker = new JejuConnectionMaker();
    ConnectionMaker hallaConnectionMaker = new HallaConnectionMaker();

    @Test
    public void testJejuGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new UserDao(jejuConnectionMaker);
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testJejuInsert() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao(jejuConnectionMaker);
        String name = "jade";
        String password = "1234";
        Integer id = userDao.insert(name, password);

        assertThat(id, greaterThan(0));

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testHallaGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new UserDao(hallaConnectionMaker);
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testHallaInsert() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao(hallaConnectionMaker);
        String name = "jade";
        String password = "1234";
        Integer id = userDao.insert(name, password);

        assertThat(id, greaterThan(0));

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
