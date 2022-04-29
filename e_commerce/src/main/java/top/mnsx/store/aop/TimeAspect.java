package top.mnsx.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

//@Aspect
@Component
@Aspect
public class TimeAspect {
    @Around("execution(* top.mnsx.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = pjp.proceed();

        long end = System.currentTimeMillis();

        System.err.println("耗时：" + (end - start) + "ms.");

        return result;
    }
}
