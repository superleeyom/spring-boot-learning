package com.leeyom.mongodb;

import com.leeyom.mongodb.dao.UserRepository;
import com.leeyom.mongodb.pojo.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSaveUser() {
//        UserEntity user = new UserEntity();
//        user.setId(2L);
//        user.setUserName("王大锤");
//        user.setPassWord("root");
//        userRepository.save(user);

        //插入100条数据用于测试
        for (long i = 0; i < 100; i++) {
            UserEntity user = new UserEntity();
            user.setId(i);
            user.setUserName("小王" + i);
            user.setPassWord("xiaowang");
            userRepository.save(user);
        }

    }

    @Test
    public void testFindUserByUserName() {
        UserEntity user = userRepository.findUserByUserName("王大锤");
        System.out.println(user.toString());
    }

    @Test
    public void testUpdateUser() {
        UserEntity user = new UserEntity();
        user.setId(2L);
        user.setUserName("王尼玛");
        user.setPassWord("778899");
        userRepository.save(user);
    }

    @Test
    public void testDeleteUserById() {
        userRepository.delete(2L);
    }

    @Test
    public void testFindAll() {
        Pageable pageable = new PageRequest(2, 10, new Sort(Sort.Direction.DESC, "id"));
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        System.out.println("总条数：" + userEntityPage.getTotalPages());
        System.out.println("列表：" + userEntityPage.getContent());
    }

}
