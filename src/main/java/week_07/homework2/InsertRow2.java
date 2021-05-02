package week_07.homework2;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.util.StopWatch;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * cost 91.42秒
 */
public class InsertRow2 {


    public static HikariDataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/E-commerce");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("");
        return hikariDataSource;
    }

    public static void main(String[] args) throws SQLException {
        HikariDataSource hikariDataSource = dataSource();
        Connection conn = hikariDataSource.getConnection();
        String sql = "insert into `order_info` " +
                "(`order_number`,`buyer_id`,`trade_status`,`pay_status`,`order_amount`,`pay_amount`,`total_amount`,`pay_time`,`create_time`,`delivery_type`) " +
                "values(?,?,?,?,?,?,?,?,?,?)";

        StopWatch stopWatch = new StopWatch();
        PreparedStatement prep = conn.prepareStatement(sql);

        stopWatch.start();
        conn.setAutoCommit(false);

        for (int i = 0; i < 100_0000; i++) {
            prep.setInt(1, i);
            prep.setInt(2, 1);
            prep.setInt(3, 1);
            prep.setInt(4, 1);
            prep.setInt(5, i);
            prep.setInt(6, i);
            prep.setInt(7, i);
            prep.setDate(8, new Date(System.currentTimeMillis()));
            prep.setDate(9, new Date(System.currentTimeMillis()));
            prep.setInt(10, 1);
            prep.addBatch();
        }

        prep.executeBatch();
        prep.clearBatch();

        conn.commit();
        stopWatch.stop();

        System.out.println("cost " + stopWatch.getTotalTimeSeconds() + "秒");
    }
}
