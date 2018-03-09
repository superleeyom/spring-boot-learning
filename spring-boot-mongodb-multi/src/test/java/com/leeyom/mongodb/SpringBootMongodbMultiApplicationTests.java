package com.leeyom.mongodb;

import com.leeyom.mongodb.pojo.UserEntity;
import com.leeyom.mongodb.repository.one.OneRepository;
import com.leeyom.mongodb.repository.two.TwoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMongodbMultiApplicationTests {

    @Autowired
    private OneRepository oneRepository;
    @Autowired
    private TwoRepository twoRepository;

    @Test
    public void testMultMongoDB() {
        oneRepository.save(new UserEntity(1L, "小张", "123456"));
        twoRepository.save(new UserEntity(2L, "小王", "654321"));

        UserEntity one = oneRepository.findUserByUserName("小张");
        System.out.println(one.toString());
        UserEntity two = twoRepository.findUserByUserName("小王");
        System.out.println(two.toString());
    }

}
