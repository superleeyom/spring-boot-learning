package com.leeyom.dao;

import com.leeyom.model.UserRole;
import com.leeyom.util.MyMapper;

import java.util.List;

public interface UserRoleMapper extends MyMapper<UserRole> {
    public List<Integer> findUserIdByRoleId(Integer roleId);
}