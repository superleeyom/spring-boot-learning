package com.leeyom.rabbitmq;

import com.leeyom.rabbitmq.pojo.User;
import com.leeyom.rabbitmq.sender.AdvanceSender;
import com.leeyom.rabbitmq.sender.AdvanceSender2;
import com.leeyom.rabbitmq.sender.AdvanceSender3;
import com.leeyom.rabbitmq.sender.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTest {

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private AdvanceSender advanceSender;
    @Autowired
    private AdvanceSender2 advanceSender2;
    @Autowired
    private AdvanceSender3 advanceSender3;

    /**
     * 一对一发送
     * @throws InterruptedException
     */
    @Test
    public void oneToOne() throws InterruptedException {
        helloSender.send();
        Thread.sleep(2000l);
    }

    /**
     * 一对多发送
     * @throws Exception
     */
    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            advanceSender.send(i);
        }
        Thread.sleep(10000l);
    }

    /**
     * 多对多发送
     * @throws Exception
     */
    @Test
    public void manyToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            advanceSender.send(i);
            advanceSender2.send(i);
        }
        Thread.sleep(10000l);
    }

    /**
     * 发送对象
     */
    @Test
    public void testSendObject() throws InterruptedException {
        User user = new User("Leeyom", 24);
        advanceSender3.send(user);
        Thread.sleep(10000l);
    }

}
