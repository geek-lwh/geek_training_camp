package week_05.homework8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import week_05.homework8.entity.Klass;
import week_05.homework8.entity.School;
import week_05.homework8.entity.Student;

@SpringBootApplication()
public class RunDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(RunDemo.class, args);
        Klass klass = (Klass) context.getBean("klass");
        System.out.println(klass.getKlassName());

        Student student = (Student) context.getBean("student");
        System.out.println(student.getStudentName());

        School school = (School) context.getBean("school");
        System.out.println(school.getSchoolName());
    }
}
