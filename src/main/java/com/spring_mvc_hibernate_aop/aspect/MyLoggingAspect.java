package com.spring_mvc_hibernate_aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLoggingAspect {

    /*метод будет работать с методами с любым уровнем доступа, с любым возвращаемым значением
     * указываем пакет, все классы этого пакета, все методы этих классов, с любым количеством параметров*/
    @Around("execution(* com.spring_mvc_hibernate_aop.dao.*.*(..))")
    public Object aroundAllRepositoryMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();
        //чтобы узнать имя метода
        System.out.println("Begin of: " + methodName);
        Object targetMethodResult = proceedingJoinPoint.proceed();
        System.out.println("End of: " + methodName);
        return targetMethodResult;
    }
}
