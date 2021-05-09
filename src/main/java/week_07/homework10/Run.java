package week_07.homework10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(value = "week_07.homework10")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class);
    }
}
