package week_05.homework1.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Man implements People{
    @Override
    public void eat(String food) {
        System.out.println("man eating !");
    }

    public static void main(String[] args) {
        People man = new Man();
        InvocationHandler handler = new PeopleJdkProxy(man);
        People people = (People) Proxy.newProxyInstance(Man.class.getClassLoader(),new Class[]{People.class},handler);
        people.eat("food");

    }
}
