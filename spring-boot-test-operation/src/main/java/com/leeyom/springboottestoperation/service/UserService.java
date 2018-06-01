package com.leeyom.springboottestoperation.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUser(){
        String user = "I am leeyom";
        return user;
    }

}
