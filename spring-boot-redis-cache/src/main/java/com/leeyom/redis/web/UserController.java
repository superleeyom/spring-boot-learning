package com.leeyom.redis.web;

import com.leeyom.redis.dao.UserRepository;
import com.leeyom.redis.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理控制器
 * @author Leeyom Wang
 * @date 2018年02月06日 11:07
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * CachePut：在满足条件的前提下，每次调用改方法，
     * 都会更新更新指定的缓存
     * @param nickName
     * @return
     */
    @RequestMapping("/findByNickName")
    @CachePut(value = "usersCache", key = "#nickName")
    public List<Users> findByNickName(String nickName) {
        System.out.println("执行了数据库操作");
        return userRepository.findByNickName(nickName);
    }

    /**
     * 测试cacheable注解，缓存中的 key 就是参数 name，value 就是返回的 String 值。
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    @Cacheable(value = "helloCache")
    public String hello(String name) {
        System.out.println("方法被调用了！！");
        return "hello " + name;
    }

    /**
     * 如果参数的长度小于4，才走缓存
     * @param userName
     * @return
     */
    @RequestMapping("/hello2")
    @Cacheable(value = "helloCache2", key = "#userName", condition = "#userName.length() <= 4")
    public String hello2(String userName) {
        System.out.println("方法被调用了！！");
        return "hello2 " + userName;
    }

    /**
     * allEntries = true 表示清除所有的缓存
     * beforeInvocation = true 表示调用该方法之前清除缓存中的指定元素。
     * @param nickname
     * @return
     */
    @RequestMapping("/allEntries")
    @CacheEvict(value = "usersCache", allEntries = true, beforeInvocation = true)
    public List<Users> allEntries(String nickname) {
        List<Users> users = userRepository.findByNickName(nickname);
        System.out.println("执行了数据库操作");
        return users;
    }

}
