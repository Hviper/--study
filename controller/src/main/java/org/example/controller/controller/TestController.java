package org.example.controller.controller;

import org.example.controller.annotation.InterceptorAnnotation;
import org.example.controller.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("test")
public class TestController {
    @GetMapping("/search")
    @Log
    public String search(@RequestParam Map<String, String> params) {
        String searchTerm = params.get("query");
        String pageNumber = params.get("page");
        String pageSize = params.get("size");
        // 处理请求

        return "search_results";
    }
    @Log
    @GetMapping("/search2")
    @InterceptorAnnotation   //测试拦截器中的preHandle方法中的Object handler对象
    public String search(String query, int size,int page) {
        // 处理请求
        System.out.println(query);
        System.out.println(size);
        System.out.println(page);

        return "search_results";
    }

}
