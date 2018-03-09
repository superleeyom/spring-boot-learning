package com.leeyom.mongodb;

import com.leeyom.mongodb.dao.UserDao;
import com.leeyom.mongodb.pojo.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName("Leeyom");
        user.setPassWord("123");
        userDao.saveUser(user);
    }

    @Test
    public void testFindUserByUserName() {
        UserEntity user = userDao.findUserByUserName("Leeyom");
        System.out.println(user.toString());
    }

    @Test
    public void testUpdateUser() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName("LeeyomWang");
        user.setPassWord("123456");
        userDao.updateUser(user);
        UserEntity newUser = userDao.findUserByUserName("LeeyomWang");
        System.out.println(newUser.toString());
    }

    @Test
    public void testDeleteUserById() {
        userDao.deleteUserById(1L);
    }

}
