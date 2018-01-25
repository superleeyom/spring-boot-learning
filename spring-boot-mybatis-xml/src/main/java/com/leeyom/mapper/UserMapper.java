package com.leeyom.mapper;

import com.leeyom.param.UserParam;
import com.leeyom.pojo.User;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> getUserListByPage(UserParam userParam);
}