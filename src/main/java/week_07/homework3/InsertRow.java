package week_07.homework3;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StopWatch;

import static week_07.homework2.SqlUtils.getSql;

/**
 * cost 174.706秒
 */
public class InsertRow {


    public static HikariDataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/E-commerce");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("");
        return hikariDataSource;
    }

    public static void main(String[] args) {
        HikariDataSource hikariDataSource = dataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 1000_0000; i++) {
            jdbcTemplate.update(getSql(i));
        }

        stopWatch.stop();

        System.out.println("cost " + stopWatch.getTotalTimeSeconds() + "秒");
    }
}
