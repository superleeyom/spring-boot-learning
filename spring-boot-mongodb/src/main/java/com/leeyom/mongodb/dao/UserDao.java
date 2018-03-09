package com.leeyom.mongodb.dao;

import com.leeyom.mongodb.pojo.UserEntity;

public interface UserDao {

    void saveUser(UserEntity user);

    UserEntity findUserByUserName(String userName);

    void updateUser(UserEntity user);

    void deleteUserById(Long id);

}
