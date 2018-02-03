package com.leeyom.controller;

import com.leeyom.mapper.one.UserOneMapper;
import com.leeyom.mapper.two.UserTwoMapper;
import com.leeyom.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserOneMapper userOneMapper;
    @Autowired
    private UserTwoMapper userTwoMapper;

    @RequestMapping("/getUsers")
    public List<Object> getUsers() {
        List<User> oneUserList = userOneMapper.selectAll();
        List<User> twoUserList = userTwoMapper.selectAll();
        List<Object> users = new ArrayList<>();
        users.add(oneUserList);
        users.add(twoUserList);
        return users;
    }


}