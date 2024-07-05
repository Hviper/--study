package org.example.controller.aop;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.controller.annotation.Log;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    @Before(value = "@annotation(controllerLog)")
    public void logBefore(JoinPoint joinPoint, Log controllerLog) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "anonymous";
        System.out.println("请求的用户为：" + username);

        System.out.println("joinPoint.getKind(): "+joinPoint.getKind());
        System.out.println("joinPoint.getTarget(): "+joinPoint.getTarget());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println(signature.getModifiers());
        Class<?> declaringType = signature.getDeclaringType();
        System.out.println(declaringType);
        System.out.println(signature.getMethod().getName());
    }
}
