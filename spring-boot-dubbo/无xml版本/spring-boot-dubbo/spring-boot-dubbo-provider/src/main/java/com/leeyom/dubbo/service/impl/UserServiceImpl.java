package com.leeyom.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.leeyom.dubbo.api.UserService;
import com.leeyom.dubbo.pojo.UserEntity;

/**
 * UserService实现
 * @author Leeyom Wang
 * @date 2018年02月07日 11:47
 */
@Service(timeout = 1200000, version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public UserEntity getUserById(Integer userId) {
        //模拟测试
        return new UserEntity(userId, "Leeyom", "123");
    }
}
