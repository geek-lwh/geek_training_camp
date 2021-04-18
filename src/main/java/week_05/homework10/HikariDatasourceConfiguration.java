package week_05.homework10;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HikariDatasourceConfiguration {

    @Bean
    public DataSource dataSource(){
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/user?useSSL=false");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return hikariDataSource;
    }
}
