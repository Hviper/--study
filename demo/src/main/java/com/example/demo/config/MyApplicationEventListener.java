package com.example.demo.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationEventListener implements ApplicationListener <WebServerInitializedEvent>{
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.start();
        System.out.println(event.getWebServer().getPort());
        WebServer webServer = event.getWebServer();
        webServer.stop();
        System.out.println();
        this.end();
    }

    private void end() {
        System.out.println("配置结束");
        System.out.println("配置结束");
        System.out.println("配置结束");
        System.out.println("配置结束");
    }

    private void start() {
        System.out.println("开始自动配置");
        System.out.println("开始自动配置");
        System.out.println("开始自动配置");
        System.out.println("开始自动配置");
    }
}
