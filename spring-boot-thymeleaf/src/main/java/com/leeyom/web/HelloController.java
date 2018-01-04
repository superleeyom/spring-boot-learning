package com.leeyom.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制层
 * @author Leeyom Wang
 * @date 2018年01月04日 11:59
 */
@Controller
public class HelloController {

    /**
     * 简单测试thymeleaf模板引擎
     * @param map
     * @return
     */
    @RequestMapping("/")
    public String helloWorld(ModelMap map) {
        map.addAttribute("message", "http:www.leeyom.top");
        return "index";
    }

}
