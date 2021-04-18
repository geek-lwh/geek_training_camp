package week_05.homework2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import week_05.homework2.antotation.OneService;
import week_05.homework2.xml.Student;

public class RunDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("xml 形式注入开始");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println("load spring bean from xml : " + student.toString());
        System.out.println("xml 形式注入结束");

        System.out.println("annotation 形式注入开始");
        OneService oneService = (OneService) applicationContext.getBean("oneService");
        oneService.say();
        System.out.println("annotation 形式注入结束");

    }
}
