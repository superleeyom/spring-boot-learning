package com.leeyom.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 发送者
 * @author leeyom
 * @date 2018年02月26日 下午10:10
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender: " + context);

        //将消息发送给routingKey为"hello"的队列
        rabbitmqTemplate.convertAndSend("hello", context);
    }

}
