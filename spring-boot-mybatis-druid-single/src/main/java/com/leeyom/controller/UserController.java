package com.leeyom.controller;

import com.leeyom.mapper.UserMapper;
import com.leeyom.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @RequestMapping("/getUser")
    public User getUser(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(User user) {
        userMapper.insert(user);
    }

    @RequestMapping(value = "update")
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }


}