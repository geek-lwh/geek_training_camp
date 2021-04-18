package week_05.homework3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Dog dog = (Dog) context.getBean("dog");
        Dog dog1 = (Dog) context.getBean("dog1");
        Cat cat = (Cat) context.getBean("cat");

        Zoo zoo = (Zoo) context.getBean("zoo");

        System.out.println(dog.toString());
        System.out.println(dog1.toString());
        System.out.println(cat.toString());
        System.out.println(zoo.toString());
    }
}
