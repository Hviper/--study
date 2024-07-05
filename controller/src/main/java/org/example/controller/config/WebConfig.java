package org.example.controller.config;

import org.example.controller.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")  // 指定拦截器的URL路径匹配模式
                .excludePathPatterns("/exclude/**");  // 指定不拦截的URL路径匹配模式
    }
}
