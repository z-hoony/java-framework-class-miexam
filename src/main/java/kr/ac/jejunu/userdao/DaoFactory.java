package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName("com.mysql.cj.jdbc.Driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl("jdbc:mysql://localhost/jeju?serverTimezone=UTC");
        dataSource.setUsername("jeju");
        dataSource.setPassword("jejupw");
        return dataSource;
    }
}
