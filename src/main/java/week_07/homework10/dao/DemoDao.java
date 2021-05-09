package week_07.homework10.dao;

import io.shardingsphere.api.HintManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class DemoDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void select() {
        String sql = "SELECT * FROM order_info";
        jdbcTemplate.execute(sql);
    }


    public void save() {
        String sql = "UPDATE order_info SET buyer_id = 999 where order_id = 30980055";
        jdbcTemplate.execute(sql);
    }

    public void forceSelect() {
        // 强制使用主库
        try (HintManager hintManager = HintManager.getInstance()) {
            // 设置强制访问主库
            hintManager.setMasterRouteOnly();
            // use jdbc
            String sql = "SELECT * FROM order_info WHERE order_id = 30980055";
            jdbcTemplate.execute(sql);
        }
    }
}
