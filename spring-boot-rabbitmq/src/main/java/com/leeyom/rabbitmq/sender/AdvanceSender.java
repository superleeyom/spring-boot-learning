package com.leeyom.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvanceSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send(int i) {
        String context = "hello spring boot rabbitmq queue ******" + i;
        System.out.println("AdvanceSender: " + context);

        //将消息发送给routingKey为"hello"的队列
        rabbitmqTemplate.convertAndSend("hello", context);
    }


}
