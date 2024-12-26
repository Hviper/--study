package org.example.controller.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.example.controller.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("aaa")
public class HelloController {

    @Autowired
    private MeterRegistry meterRegistry;

    public HelloController(MeterRegistry meterRegistry) {
        // 使用 MeterRegistry 注册一个 Counter
        // 定义一个 Counter 类型的指标
        Counter counter = meterRegistry.counter("http_requests_total", "method", "GET", "status", "200");
    }

    @GetMapping("/aaa")
    @Log
    public Object search(@RequestParam String method, @RequestParam String status) {
// 每次请求都会增加计数器的数值

        meterRegistry.counter("http_requests_total", "method", method, "status", status).increment();

        return "Request simulated with method " + method + " and status " + status;
    }
}
