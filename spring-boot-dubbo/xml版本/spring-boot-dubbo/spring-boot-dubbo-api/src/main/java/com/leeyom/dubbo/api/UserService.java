package com.leeyom.dubbo.api;

import com.leeyom.dubbo.pojo.UserEntity;

/**
 * rpc接口
 * @author Leeyom Wang
 * @date 2018年02月07日 11:42
 */
public interface UserService {

    /**
     * 获取指定的用户信息
     * @param userId 用户id
     * @return
     */
    UserEntity getUserById(Integer userId);

}
