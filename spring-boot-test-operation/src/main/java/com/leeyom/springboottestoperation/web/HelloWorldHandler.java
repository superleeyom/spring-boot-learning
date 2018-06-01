package com.leeyom.springboottestoperation.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldHandler {

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello world:" + name;
    }
}
