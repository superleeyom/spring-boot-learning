package com.leeyom.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leeyom.dao.UserRoleMapper;
import com.leeyom.model.User;
import com.leeyom.model.UserRole;
import com.leeyom.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public PageInfo<User> selectByPage(User user, int start, int length) {
        int page = start / length + 1;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(user.getUsername())) {
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        if (user.getEnable() != null) {
            criteria.andEqualTo("enable", user.getEnable());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<User> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    public User selectByUsername(String username) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<User> userList = selectByExample(example);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void delUser(Integer userid) {
        //删除用户表
        mapper.deleteByPrimaryKey(userid);
        //删除用户角色表
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid", userid);
        userRoleMapper.deleteByExample(example);
    }
}
