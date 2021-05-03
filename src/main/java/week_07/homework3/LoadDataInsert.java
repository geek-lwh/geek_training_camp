package week_07.homework3;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * cost 122.236秒
 */
public class LoadDataInsert {

    private static final String FILE_PATH = "/Users/monkey/Downloads/mysql_infile.sql";

    public static HikariDataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/E-commerce?useSSL=false");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("");
        return hikariDataSource;
    }

    /**
     * (20990004, '0', 1, 1, 1,
     * NULL, 0.00, 0.00, 0.00, '2021-05-02 00:00:00',
     * NULL, NULL, NULL, NULL, NULL,
     * '2021-05-02 00:00:00', NULL, 1);
     *
     * @param i
     * @return
     */
    public static String getFileSql(int i) {
        // `order_number`, `buyer_id`, `trade_status`, `pay_status`
        return i + "," + i + "," + 1 + "," + 2 + "," +
                // `best_time`, `order_amount`, `pay_amount`, `total_amount`, `pay_time`
                "'2021-05-02 20:51:09'" + "," + i + "," + i + "," + i + "," + "'2021-05-02 20:51:09'" + "," +
                // `card_owner`, `card_code`, `card_name`, `card_numer`, `outer_trade_no`
                "NULL, NULL, NULL, NULL, NULL" + "," +
                // `create_time`, `remark`, `delivery_type`
                "'2021-05-02 20:51:09'" + "," + "NULL" + "," + 1 + ";";
    }

    public static void writeFile() throws IOException {
        StringBuilder sql = new StringBuilder();
        for (int i = 1; i <= 1000_0000; i++) {
            sql.append(getFileSql(i));
            if (i % 10_0000 == 0) {
                outFile(sql.toString());
                sql.setLength(0);
            }
        }

        outFile(sql.toString());
    }

    public static void outFile(String content) {
        FileWriter writer = null;
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(FILE_PATH, true);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // load data infile "/data/mysql/e.sql" into table e fields terminated by ',';
    // load data infile "/data/mysql/e.sql" into table e fields enclosed by '"';
    // load data infile '/tmp/t0.txt' into table t0 character set gbk fields
    // terminated by ',' enclosed by '"' lines
    // terminated by '\n' (`name`,`age`,`description`)
    //set update_time=current_timestamp;
    public static void load() throws SQLException {
        Connection conn = dataSource().getConnection();
        String sql = "LOAD DATA LOCAL INFILE '" + FILE_PATH + "' INTO TABLE order_info FIELDS TERMINATED BY ',' LINES TERMINATED BY ';'";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        statement.close();
        conn.close();
    }

    public static void main(String[] args) throws IOException, SQLException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        writeFile();
        load();
        stopWatch.stop();
        System.out.println("cost " + stopWatch.getTotalTimeSeconds() + "秒");

    }
}
