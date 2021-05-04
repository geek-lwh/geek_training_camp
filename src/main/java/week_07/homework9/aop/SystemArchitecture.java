package week_07.homework9.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Aspect
@Component
public class SystemArchitecture {

    @Resource
    private DataSource dataSource;

    @Resource
    private DataSource dataSource2;

    @Pointcut("execution(* week_07.homework9.dao.*.*(..))")
    public void anyNeedSwitchDatasourceMethod() {
    }

//    @Around("dataSourcePointCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = signature.getMethod();
//        DataSource dataSource = method.getAnnotation(DataSource.class);
//        if (dataSource == null) {
//            DynamicDataSource.setDataSource("xiaobin-master");
//        } else {
//            DynamicDataSource.setDataSource(dataSource.name());
//        }
//        try {
//            return point.proceed();
//        } finally {
//            DynamicDataSource.clearDataSource();
//        }
}
