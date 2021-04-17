package week_05.hoursework_01;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PeopleJdkProxy implements InvocationHandler {

    private Object target;

    public PeopleJdkProxy(){
        super();
    }

    public PeopleJdkProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before proxy");
        Object result = null;
        try {

            result = method.invoke(target,args);
        } catch (Exception e) {
            System.out.println("ex:"+e.getMessage());
            throw e;
        } finally {
            System.out.println("after proxy");
        }
        return result;
    }

    public static void main(String[] args) {
        People man = new Man();
        InvocationHandler handler = new PeopleJdkProxy(man);
        // 用哪个类加载器去加载代理对象
        People people = (People) Proxy.newProxyInstance(Man.class.getClassLoader(),new Class[]{People.class},handler);
        people.eat("food");

        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", People.class.getInterfaces());
//        String path = "D:/javacode/javase/Test/bin/proxy/StuProxy.class";
        String path = "D:\\code\\geek_training_camp\\src\\main\\java\\week_05\\hoursework_01\\PeopleJdkProxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}
