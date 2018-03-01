package com.leeyom.rabbitmq.sender;

import com.leeyom.rabbitmq.pojo.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvanceSender3 {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send(User user) {
        System.out.println("AdvanceSender3: " + user.toString());
        //将消息发送给routingKey为"object"的队列
        rabbitmqTemplate.convertAndSend("object", user);
    }

}
