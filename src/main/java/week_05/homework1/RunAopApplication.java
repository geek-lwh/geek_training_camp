package week_05.homework1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import week_05.homework1.aop.ILog;
import week_05.homework1.log.impl.ApplicationLog;

public class RunAopApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        proxy-target-class="true"，为false时会报转换错误
        ApplicationLog applicationLog = (ApplicationLog) context.getBean("applicationLog");
        applicationLog.saveLog();

        ILog log = context.getBean(ILog.class);
        System.out.println(log);
        System.out.println("ILog："+log.getClass());

        ILog log1 = context.getBean(ILog.class);
        System.out.println(log1);
        System.out.println("ILog接口的对象AOP代理后的实际类型："+log1.getClass());
        log1.saveLog();
    }
}
