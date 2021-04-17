package week_05.hoursework_01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Women implements People{
    @Override
    public void eat(String food) {
        System.out.println("women eating !");
    }

    public static void main(String[] args) {
        People man = new Women();
        InvocationHandler handler = new PeopleJdkProxy(man);
        People people = (People) Proxy.newProxyInstance(Women.class.getClassLoader(),new Class[]{People.class},handler);
        people.eat("food");

    }
}
