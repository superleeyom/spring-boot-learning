package com.leeyom.dao;

import com.leeyom.model.Role;
import com.leeyom.util.MyMapper;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    public List<Role> queryRoleListWithSelected(Integer id);
}