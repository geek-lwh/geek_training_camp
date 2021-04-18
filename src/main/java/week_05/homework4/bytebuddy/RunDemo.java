package week_05.homework4.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

public class RunDemo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Biz service = new ByteBuddy()
                .subclass(Biz.class)
                .method(ElementMatchers.any())
                .intercept(Advice.to(Advisor.class))
                .make()
                .load(Biz.class.getClassLoader())
                .getLoaded()
                .newInstance();
        service.bar(123);
        service.foo(456);
    }
}
