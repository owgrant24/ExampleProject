package com.github.owgrant24.springbootone.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// https://github.com/RameshMF/spring-mvc-tutorial/tree/master/springmvc5-slf4j-logback-example
@Slf4j
@Component
@Aspect
public class LogAspect {
    private Object logMethodInvocation(ProceedingJoinPoint jp) throws Throwable {
        StringBuilder info = new StringBuilder();
        long start = System.currentTimeMillis();
        Object result = jp.proceed();
        info.append(jp.getTarget().getClass().getSimpleName()).append(".")
                .append(jp.getSignature().getName())
                .append("(").append(Arrays.toString(jp.getArgs())).append(")")
                .append(" : ").append(result)
                .append(" in ").append(System.currentTimeMillis() - start).append(" msec.");
        log.info(info.toString());
        return result;
    }

    @Around("execution(* com.github.owgrant24.springbootone.repository.*.*(..))")
    public Object logDAO(ProceedingJoinPoint jp) throws Throwable {
        return logMethodInvocation(jp);
    }

    @Around("execution(* com.github.owgrant24.springbootone.service.*.*(..))")
    public Object logService(ProceedingJoinPoint jp) throws Throwable {
        return logMethodInvocation(jp);
    }
}
