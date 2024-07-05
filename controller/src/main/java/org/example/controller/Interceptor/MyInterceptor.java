package org.example.controller.Interceptor;

import org.example.controller.annotation.InterceptorAnnotation;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {  //当请求经过控制器的时候就会是HandlerMethod，否则不是
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 获取方法名
            String methodName = handlerMethod.getMethod().getName();
            // 获取控制器类名
            String controllerName = handlerMethod.getBeanType().getName();

            System.out.println("Pre-handle method is called");
            System.out.println("Controller: " + controllerName + ", Method: " + methodName);

            // 检查方法上的注解
            InterceptorAnnotation myAnnotation = handlerMethod.getMethodAnnotation(InterceptorAnnotation.class);
            if (myAnnotation != null) {
                // 执行相关逻辑
                System.out.println("MyAnnotation is present");
            }
        }

        return true; // 返回 true 继续执行，返回 false 中断请求
    }
}
