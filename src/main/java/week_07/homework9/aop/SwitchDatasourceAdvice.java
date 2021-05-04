package week_07.homework9.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Objects;

@Aspect
@Component
public class SwitchDatasourceAdvice {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DataSource dataSource;

    @Resource
    private DataSource dataSource2;

    @Around("week_07.homework9.aop.SystemArchitecture.anyNeedSwitchDatasourceMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            before();
            process(joinPoint);
            result = joinPoint.proceed(joinPoint.getArgs());
            after();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            revivification();
        }

        return result;
    }

    /**
     * 前置处理
     *
     * @throws SQLException
     */
    private void before() throws SQLException {
        String url = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL();
        System.out.println("before change url " + url);
    }

    /**
     * 处理
     *
     * @param joinPoint
     * @throws SQLException
     */
    private void process(ProceedingJoinPoint joinPoint) throws SQLException {
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        Method method = joinPointObject.getMethod();
        System.out.println("method name is " + method.getName());
        SwitchDatasource classAnnotation = AnnotationUtils.findAnnotation(method.getDeclaringClass(), SwitchDatasource.class);
        if (classAnnotation == null) {
            System.out.println("没有annotation注解");
            return;
        }

        String methodName = method.getName();
        if (methodName.equals("select")) {
            jdbcTemplate.setDataSource(dataSource2);
            String url = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL();
            System.out.println("select 使用 数据源 " + url);
        }
    }

    /**
     * 后置处理
     *
     * @throws SQLException
     */
    private void after() throws SQLException {
        System.out.println("after change datasource");
        revivification();
        String url = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL();
        System.out.println("after change 还原可能更改的数据源 " + url);
    }

    /**
     * 还原数据源
     */
    private void revivification() {
        jdbcTemplate.setDataSource(dataSource);
    }
}
