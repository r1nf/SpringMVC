package com.epam.mentorship.spring.mvc.aop;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingHandler {

    private static final Logger LOG = Logger.getLogger(LoggingHandler.class);

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }

    @Pointcut("execution(* com.epam..*Service.*(..))")
    protected void service() {
    }

    @AfterReturning("service()")
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("Completed: " + joinPoint);
        LOG.info("------ Completed: " + joinPoint);
    }

    @Before("service()")
    public void logBefore(JoinPoint joinPoint) {

        LOG.info("Entering in Method :  " + joinPoint.getSignature().getName());
        LOG.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        LOG.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        LOG.info("Target class : " + joinPoint.getTarget().getClass().getName());
    }

    @AfterReturning(pointcut = "service()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        LOG.info("Method Return value : " + returnValue);
    }

    @AfterThrowing(pointcut = "service()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        LOG.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        LOG.error("Cause : " + exception.getCause());
    }

    @Around("service()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            LOG.info("Method " + className + "." + methodName + " ()" + " execution time : "
                    + elapsedTime + " ms");

            return result;
        } catch (IllegalArgumentException e) {
            LOG.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }
    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}

