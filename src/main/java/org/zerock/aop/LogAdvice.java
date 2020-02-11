package org.zerock.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LogAdvice {
    @Before("execution(* org.zerock.service.AopService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("=============");
        log.info("str1: " + str1);
        log.info("str2: " + str2);
    }

    @AfterThrowing(pointcut = "execution(* org.zerock.service.AopService*.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        log.info("Exception....!!!");
        log.info("exception: " + exception);
    }

    @Around("execution(* org.zerock.service.AopService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();

        log.info("Target : " + pjp.getTarget());
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        //invoke method
        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("TIME: " + (end - start));
        return result;
    }

}
