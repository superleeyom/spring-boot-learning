package com.leeyom.springboottestoperation;

import com.leeyom.springboottestoperation.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {
        System.out.println(userService.getUser());
    }

}
