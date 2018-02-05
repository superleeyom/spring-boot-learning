package com.leeyom;

import com.leeyom.redis.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 测试类
 * @author Leeyom Wang
 * @date 2018年02月05日 11:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存储基本数据类型
     */
    @Test
    public void testString() {
        redisTemplate.opsForValue().set("myname", "Leeyom Wang");
        System.out.println("存储到redis中的内容为：" + redisTemplate.opsForValue().get("myname"));
    }

    /**
     * 存储对象，对象需要序列化
     */
    @Test
    public void testObject() {
        User user = new User(1, "leeyom", "1234", "Man", "老王");
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("www.leeyom.top", user);
        System.out.println("存储到redis中的内容为：" + valueOperations.get("www.leeyom.top"));
    }

    /**
     * 设置超时无效
     */
    @Test
    public void testTimeout() throws InterruptedException {
        User user = new User(1, "leeyom", "1234", "Man", "老王");
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("expire", user, 100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        if (redisTemplate.hasKey("expire")) {
            System.out.println("存储到redis中的内容为：" + valueOperations.get("expire"));
        } else {
            System.out.println("该键值对已经失效~");
        }
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        if (redisTemplate.hasKey("www.leeyom.top")) {
            redisTemplate.delete("www.leeyom.top");
            System.out.println("删除成功！");
        } else {
            System.out.println("该键已经失效~");
        }
    }

    /**
     * 测试hash
     */
    @Test
    public void testHash() {
        //第一个参数为key，第二个参数为属性，第三个参数为属性值
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        //翻译过来就是：leeyom的性别为男
        hashOperations.put("leeyom", "sex", "man");
        String value = (String) hashOperations.get("leeyom", "sex");
        System.out.println("读取到redis中的内容为 :" + value);
    }

    /**
     * 测试List
     */
    @Test
    public void testList() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        //从左侧插入
        listOperations.leftPush("list", "www");
        listOperations.leftPush("list", "leeyom");
        listOperations.leftPush("list", "top");
        //弹出左侧第一个值
        String value = (String) listOperations.leftPop("list");
        System.out.println("读取到redis中的内容为 :" + value);

        //遍历list
        //第一个参数为key、第二个参数为起始范围、第二个参数为结束范围
        List<String> list = listOperations.range("list", 0, 2);
        System.out.println("读取到list中的内容为 :" + list.toString());
    }

    /**
     * 测试set
     */
    @Test
    public void testSet() {
        //set集合有自动去重的功能，无法自动排序
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.add("set", "www");
        setOperations.add("set", "www");
        setOperations.add("set", "leeyom");
        setOperations.add("set", "leeyom");
        setOperations.add("set", "top");
        Set<String> set = setOperations.members("set");
        System.out.println("读取到set中的内容为 :" + set.toString());
    }

    /**
     * 测试zset，在set的基础上增加了自动排序的功能
     */
    @Test
    public void testZset() {
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        //第一个参数key、第二个参数为值，第三个参数权重值，就是根据这个权重值进行排序
        zset.add("zset", "http", 1);
        zset.add("zset", "www", 3);
        zset.add("zset", "leeyom", 4);
        zset.add("zset", "top", 6);

        Set<String> zsets = zset.range("zset", 0, 3);
        System.out.println("读取到zsets中的内容为 :" + zsets.toString());

        //获取指定权重值范围之类的元素集合
        Set<String> zsetB = zset.rangeByScore("zset", 0, 3);
        System.out.println("读取到zsets中的内容为 :" + zsetB.toString());
    }
}
