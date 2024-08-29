package com.zezanziet.pharmaceutical.vn.ms.product_service.aop.logging;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Environment env;

    public LoggingAspect(Environment env) {
        this.env = env;
    }

    public Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *) " +
            "|| within(@org.springframework.stereotype.Service *)" +
            "|| within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {

    }

    @Pointcut("within(com.zezanziet.pharmaceutical.vn.ms.product_service.repositories..*)" +
            " || within(com.zezanziet.pharmaceutical.vn.ms.product_service.services..*)" +
            " || within(com.zezanziet.pharmaceutical.vn.ms.product_service.controllers..*)")
    public void applicationPackagePointcut() {

    }

    /**
     * Log when exception is thrown
     */
    @AfterThrowing(pointcut = "springBeanPointcut() && applicationPackagePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger(joinPoint)
                .error(
                        "Exception in method {}() is thrown with message \'{}\', caused by \'{}\'",
                        joinPoint.getSignature().getName(),
                        e.getMessage(),
                        e.getCause(),
                        e
                );
    }

    @Around("springBeanPointcut() && applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Logger log = logger(joinPoint);
//        if (log.isDebugEnabled()) {
//            log.debug("Debug is enabled");
        log.debug(
                "Enter method {}() with argument[s] = {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );
//        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug(
                        "Exit method {}() with result {}",
                        joinPoint.getSignature().getName(),
                        result
                );
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error(
                    "Illegal arguments {} in {}()",
                    Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature()
            );
            throw e;
        }

    }


}
