package week_05.homework1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {

    public void before(){
        System.out.println("before saveLog");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("    ====>around begin saveLog");
        joinPoint.proceed();
        System.out.println("    ====>around finish saveLog");
    }

    public void after(){
        System.out.println("after saveLog");
    }
}
