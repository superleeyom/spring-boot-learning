package com.leeyom.mybatis.service.impl;

import com.leeyom.mybatis.mapper.UserInfoMapper;
import com.leeyom.mybatis.model.UserInfo;
import com.leeyom.mybatis.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements IUserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;


}
