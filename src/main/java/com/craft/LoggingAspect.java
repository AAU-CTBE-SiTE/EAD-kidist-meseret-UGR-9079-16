package com.craft;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

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

    @Around("execution(* com.craft.StudentService.displayStudents(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Around: Before method execution...");

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        System.out.println("Around: After method execution...");

        System.out.println("Execution time: " +
                (endTime - startTime) + " milliseconds");

        return result;
    }
}