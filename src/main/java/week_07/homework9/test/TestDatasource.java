package week_07.homework9.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import week_07.homework9.dao.DemoDao;

import javax.annotation.Resource;

@Component
public class TestDatasource implements CommandLineRunner {

    @Resource
    private DemoDao demoDao;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("开始测试数据源");
        demoDao.select();
        demoDao.save();
        demoDao.select();
    }
}
