package com.leeyom.controller;

import com.leeyom.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

笔记：

1. @ApiOperation：给API增加说明
2. @ApiImplicitParam：给单个参数添加说明
3. @ApiImplicitParams：给多个参数添加说明

 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
    // 创建一个线程安全的map，模拟数据库，存放用户信息
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
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
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
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        User oldUser = users.get(id);
        oldUser.setAge(user.getAge());
        oldUser.setName(user.getName());
        users.put(id, oldUser);
        return "success";
    }

    /**
     * 删除用户
     * @param id
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
