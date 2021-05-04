package week_07.homework9.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemArchitecture {


    @Pointcut("execution(* week_07.homework9.dao.*.*(..))")
    public void anyNeedSwitchDatasourceMethod() {
    }

}
