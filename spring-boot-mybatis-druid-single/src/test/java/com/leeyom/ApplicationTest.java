package com.leeyom;

import com.leeyom.mapper.UserMapper;
import com.leeyom.param.UserParam;
import com.leeyom.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testUser() {
        //新增
        userMapper.insert(new User("张三", "zhangsan123", "man", "ZhangSan"));
        userMapper.insert(new User("李四", "lisi123", "man", "LiSi"));
        userMapper.insert(new User("王二", "wanger123", "man", "WangEr"));
        userMapper.insert(new User("小花", "xiaohua123", "women", "XiaoHua"));
        userMapper.insert(new User("小米", "xiaomi123", "man", "XiaoMi"));

        //删除
        userMapper.deleteByPrimaryKey(1);

        //修改
        User user = userMapper.selectByPrimaryKey(29);
        user.setUserName("李四233");
        userMapper.updateByPrimaryKey(user);
        System.out.println("更新成功！！！");

        //查询
        List<User> userList = userMapper.selectAll();
        System.out.println("所有的用户记录：" + userList);

        //分页查询
        UserParam userParam = new UserParam(10, 1, "", "");
        List<User> userListByPage = userMapper.getUserListByPage(userParam);
        System.out.println("分页查询结果：" + userListByPage);

        //获取总的记录数
        Long total = userMapper.getCount(userParam);
        System.out.println("总的记录数为：" + total);

    }

}
