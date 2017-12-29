package com.leeyom.helloworld.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 * @author Leeyom Wang
 * @date 2017年12月29日 11:24
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello world, " + name + "。";
    }

}
