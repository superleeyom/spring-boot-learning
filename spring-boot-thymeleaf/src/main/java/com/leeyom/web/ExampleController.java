package com.leeyom.web;

import com.leeyom.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例
 * @author Leeyom Wang
 * @date 2018年01月04日 14:12
 */
@Controller
public class ExampleController {

    @RequestMapping("/example")
    public String example(ModelMap map) {
        map.addAttribute("userName", "Leeyom Wang");
        map.addAttribute("flag", "no");
        List<User> users = new ArrayList();
        users.add(new User("leeyom", 24, "123"));
        users.add(new User("Tom", 30, "5566"));
        users.add(new User("Ben", 30, "7788"));
        map.addAttribute("users", users);
        map.addAttribute("type",1);
        return "example";
    }


}
