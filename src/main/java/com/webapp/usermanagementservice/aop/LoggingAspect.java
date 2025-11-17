package com.webapp.usermanagementservice.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Pointcut(value = "execution(* com.webapp.usermanagementservice.service.UserService.*(..))")
    public void logForUser(){}

    @Before(value = "logForUser()")
    public void logBefore(JoinPoint joinPoint){
        log.info("Method start successfully: {} ",joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "logForUser()",returning = "result")
    public void logRunning(JoinPoint joinPoint,Object result){
        log.info("Method: {} | with result: {}",joinPoint.getSignature().getName(),result);
    }

    @After(value = "logForUser()")
    public void logAfter(JoinPoint joinPoint){
        log.info("Method finished successfully: {} ",joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "logForUser()",throwing = "ex")
    public void logException(JoinPoint joinPoint,Exception ex){
        log.error("Method: {} | with exception: {}",joinPoint.getSignature().getName(),ex.getLocalizedMessage());
    }
}
