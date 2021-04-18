package week_05.homework1.jdkproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PeopleCglib {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Man.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before method run...");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("after method run...");
                return result;
            }
        });

        Man man = (Man) enhancer.create();
        man.eat("food");


    }
}
