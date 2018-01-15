package com.leeyom.controller;

import com.leeyom.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 * @author Leeyom Wang
 * @date 2018年01月15日 14:21
 */
@Api(description = "用户管理", tags = "UserController", basePath = "/user")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 创建一个线程安全的map，模拟数据库，存放用户信息
     */
    static Map<Long, User> users = new HashMap<>();

    /**
     * 获取用户列表
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "获取所有用户信息")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> userList = new ArrayList<User>(users.values());
        return userList;
    }

    /**
     * 新增用户
     * @param user 新增用户实体
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addUser(@ApiParam(value = "新增用户实体封装", required = true) @RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    /**
     * 获取用户信息
     * @param id 用户主键id
     * @return
     */
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    /**
     * 更新用户
     * @param id   用户id
     * @param user 更新实体封装
     * @return
     */
    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id, @ApiParam(value = "更新实体封装", required = true) @RequestBody User user) {
        User oldUser = users.get(id);
        oldUser.setAge(user.getAge());
        oldUser.setName(user.getName());
        users.put(id, oldUser);
        return "success";
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
