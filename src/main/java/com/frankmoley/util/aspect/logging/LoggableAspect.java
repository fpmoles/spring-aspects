package com.frankmoley.util.aspect.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggableAspect {

    private static final Logger LOG = Logger.getLogger(LoggableAspect.class.getName());
    private static final String PIPE = " | ";

    @Around("@annotation(Loggable)")
    public Object executeLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnValue = joinPoint.proceed();
        stopWatch.stop();
        String message = "call to method: " + joinPoint.getSignature().getName() +
                PIPE + "time: " + stopWatch.getTotalTimeMillis() + "ms" + PIPE;
        LOG.info(message);
        return returnValue;
    }
}
