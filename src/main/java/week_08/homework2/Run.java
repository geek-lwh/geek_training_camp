package week_08.homework2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@ComponentScan(value = "week_08.homework2")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class);
    }
}

