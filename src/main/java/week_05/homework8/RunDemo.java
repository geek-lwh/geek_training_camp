package week_05.homework8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import week_05.homework8.entity.Klass;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RunDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(RunDemo.class, args);
        Klass klass = (Klass) context.getBean("Klass");
        System.out.println(klass.getKlassName());
    }
}
