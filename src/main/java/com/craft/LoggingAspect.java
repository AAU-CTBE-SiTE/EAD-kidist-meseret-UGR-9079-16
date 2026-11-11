package com.craft;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.AfterThrowing;
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.craft.StudentService.*(..))")
    public void logBefore() {
        System.out.println("Before method execution...");
    }

    @After("execution(* com.craft.StudentService.*(..))")
    public void logAfter() {
        System.out.println("After method execution...");
    }

    @AfterReturning(
            pointcut = "execution(String com.craft.StudentService.getStudentName())",
            returning = "result"
    )
    public void logAfterReturning(String result) {
        System.out.println("Method returned successfully. Student name: " + result);
    }
    @AfterThrowing(
            pointcut = "execution(* com.craft.StudentService.testException(..))",
            throwing = "exception"
    )
    public void logAfterThrowing(Exception exception) {
        System.out.println("Exception occurred: " + exception.getMessage());
    }
}