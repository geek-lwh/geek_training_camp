package week_07.homework10.config;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.algorithm.masterslave.RoundRobinMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DatasourceConfig {

    public static final String MASTER_DB_NAME = "ds_master";

    public static final String SLAVE_DB_NAME = "ds_slave";

    public static final String MASTER_SLAVE_RULE_CONFIGURATION_NAME = "ds_master_slave";

    @Bean
    public DataSource dataSource() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置主库
        HikariDataSource masterDataSource = new HikariDataSource();
        masterDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        masterDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/E-commerce?useSSL=false");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("");

        // 配置第一个从库
        HikariDataSource slaveDataSource = new HikariDataSource();
        slaveDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        slaveDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/E-commerce2?useSSL=false");
        slaveDataSource.setUsername("root");
        slaveDataSource.setPassword("");


        // 配置读写分离规则
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration();
        masterSlaveRuleConfig.setMasterDataSourceName(MASTER_DB_NAME);
        masterSlaveRuleConfig.setSlaveDataSourceNames(Collections.singletonList(SLAVE_DB_NAME));
        masterSlaveRuleConfig.setLoadBalanceAlgorithm(new RoundRobinMasterSlaveLoadBalanceAlgorithm());
        masterSlaveRuleConfig.setName(MASTER_SLAVE_RULE_CONFIGURATION_NAME);

        // 获取数据源对象
        dataSourceMap.put(MASTER_DB_NAME, masterDataSource);
        dataSourceMap.put(SLAVE_DB_NAME, slaveDataSource);

        // properties
        Properties properties = new Properties();
        properties.put("sql.show", "true");

        return MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig, new HashMap<>(), properties);
    }


    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }
}
