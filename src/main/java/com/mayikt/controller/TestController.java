package com.mayikt.controller;

import com.mayikt.event.DemoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DemoClient demoClient;

    @GetMapping("/test")
    public String test(String msg) {
        demoClient.publish(msg);
        return "test";
    }
}
