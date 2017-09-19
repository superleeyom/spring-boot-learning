package com.leeyom.controller;

import com.leeyom.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
1.主题：标准的符合RESTful API 风格的controller

2.笔记：
    . @RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置。
    . @ModelAttribute: 绑定请求参数到指定的对象。
    . @PathVariable：获取请求url中的动态参数。
    . @RequestParam: 接受简单类型的属性，也可以接受对象类型。类似@RequestParam("id")等价于request.getParameter("id");

3.测试：采用chrome浏览器的Postman插件测试各接口的调用情况
 */

@RestController
@RequestMapping(value="/users")
public class UserController {
    // 创建一个线程安全的map，模拟数据库，存放用户信息
    static Map<Long, User> users = new HashMap<>();

    /**
     * 获取用户列表
     * @return
     */
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
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    /**
     * 获取用户信息
     * @param id 用户主键id
     * @return
     */
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
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
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
