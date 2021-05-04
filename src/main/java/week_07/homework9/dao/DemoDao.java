package week_07.homework9.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import week_07.homework9.aop.SwitchDatasource;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Objects;

@Component
@SwitchDatasource
public class DemoDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void save() throws SQLException {
        String url = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL();
        System.out.println("current datasource is " + url);
    }

    public void select() throws SQLException {
        String url = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL();
        System.out.println("current datasource is " + url);
    }

}
