package com.craft;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
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
}
