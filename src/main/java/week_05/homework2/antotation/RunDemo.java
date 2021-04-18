package week_05.homework2.antotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class RunDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        OneService oneService = (OneService) applicationContext.getBean("oneService");
        oneService.say();
    }
}
