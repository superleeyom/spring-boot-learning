package com.leeyom.web;

import com.leeyom.Exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://leeyom.top");
        return "index";
    }

    /**
     * 测试统一异常处理类
     * @return
     * @throws Exception
     */
    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    /**
     * 测试异常处理类，返回 json 格式数据
     * @return
     * @throws MyException
     */
    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}
