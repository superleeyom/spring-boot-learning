package com.leeyom.mapper;

import com.leeyom.param.UserParam;
import com.leeyom.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> getAll();

    List<User> getList(UserParam userParam);

    int getCount(UserParam userParam);

    User getOne(Long id);

    void insert(User user);

    int update(User user);

    int delete(Long id);

}