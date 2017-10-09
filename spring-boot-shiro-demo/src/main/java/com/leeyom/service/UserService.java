package com.leeyom.service;

import com.github.pagehelper.PageInfo;
import com.leeyom.model.User;

public interface UserService extends IService<User>{
    PageInfo<User> selectByPage(User user, int start, int length);

    User selectByUsername(String username);

    void delUser(Integer userid);

}
