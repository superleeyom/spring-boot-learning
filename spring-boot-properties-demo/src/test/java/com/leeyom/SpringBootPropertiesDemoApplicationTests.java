package com.leeyom;

import com.leeyom.pojo.BlogProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootPropertiesDemoApplicationTests {

    @Autowired
    private BlogProperties blogProperties;

    @Test
    public void contextLoads() {
        Assert.assertEquals(blogProperties.getName(), "leeyom");
        Assert.assertEquals(blogProperties.getTitle(), "leeyom的个人站");
    }
}
