package by.dulik.eureka.users.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Slf4j
@Aspect
public class LoggingAspect {

    @Pointcut("execution(public * by.dulik.eureka.users.service.*.*(..))")
    public void isServiceLayer() {
    }

    @Around("isServiceLayer()")
    public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String nameMethod = joinPoint.getSignature().getName();
        String argsMethodArray = Arrays.toString(joinPoint.getArgs());
        log.info(String.format("Class - %s, Method - %s, args: %s = begin", className, nameMethod, argsMethodArray));
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            log.info(String.format("Class - %s, Method - %s, args: %s = Exception", className, nameMethod, argsMethodArray));
            throw ex;
        }
        log.info(String.format("Class - %s, Method - %s, args: %s = done", className, nameMethod, argsMethodArray));
        return result;
    }
}
