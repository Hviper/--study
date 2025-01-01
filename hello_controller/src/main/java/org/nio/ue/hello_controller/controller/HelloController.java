package org.nio.ue.hello_controller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {


    @GetMapping("/demo")
    public Object search() {
    // 每次请求都会增加计数器的数值

        return "Request simulated with method ";
    }


    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("test")
    public void test() throws JsonProcessingException {
        log.info("color:{}", objectMapper.writeValueAsString(Color.BLUE));
    }

    enum Color {
        RED, BLUE
    }
}
