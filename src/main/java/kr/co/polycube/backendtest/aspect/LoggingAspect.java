package kr.co.polycube.backendtest.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* kr.co.polycube.backendtest.user.controller.UserController.*(..))")
    public void logClientAgent(JoinPoint joinPoint){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String clientAgent = request.getHeader("User-Agent");

        System.out.println("Agent: " + clientAgent + " | Method: " + joinPoint.getSignature().getName());
    }


}
