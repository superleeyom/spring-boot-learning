package com.leeyom.rabbitmq;

import com.leeyom.rabbitmq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicRabbitTest {
    @Autowired
    private TopicSender topicSender;

    @Test
    public void topic1() throws Exception {
        topicSender.send1();
        Thread.sleep(1000l);
    }

    @Test
    public void topic2() throws Exception {
        topicSender.send2();
        Thread.sleep(1000l);
    }
}
