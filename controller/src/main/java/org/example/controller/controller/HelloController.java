package org.example.controller.controller;

import org.example.controller.annotation.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping()
public class HelloController {
    @PostMapping("/")
    @Log
    public Object search(@RequestBody Object map) {
        System.out.println(map);

        return map;
    }
}
