package com.example.metrics.controller;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.SocketTimeoutException;

@RestController
@RequestMapping("/hello")
public class PushGateRestoreController {

    @Retryable(
            value = {SocketTimeoutException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 1000, multiplier = 2.0)
    )
    @GetMapping("a")
    public String hello() throws SocketTimeoutException {
        System.out.println("Attempting to perform task...");

        // 模拟任务失败
        if (Math.random() > 0.7) {
            throw new SocketTimeoutException("Socket timeout exception occurred!");
        }

        System.out.println("Task completed successfully!");
        return "Hello World!";
    }

}
