package com.leeyom.fastweb.web;

import com.leeyom.fastweb.domain.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制层
 * @author Leeyom Wang
 * @date 2017年12月29日 15:46
 */
@RestController
public class WebController {

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setName("leeyom");
        user.setAge(24);
        user.setPass("123456");
        return user;
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        User user1 = new User();
        user1.setName("leeyom");
        user1.setAge(24);
        user1.setPass("leeyom1993");
        users.add(user1);
        User user2 = new User();
        user2.setName("小明");
        user2.setAge(12);
        user2.setPass("123456");
        users.add(user2);
        return users;
    }

    @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
    public User get(@PathVariable String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    @RequestMapping("/saveUser")
    public void saveUser(@Valid User user, BindingResult result) {
        System.out.println("user:" + user);
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }
    }
}
