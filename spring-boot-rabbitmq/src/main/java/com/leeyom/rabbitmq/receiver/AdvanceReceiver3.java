package com.leeyom.rabbitmq.receiver;

import com.leeyom.rabbitmq.pojo.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "object")
public class AdvanceReceiver3 {

    @RabbitHandler
    public void process(User user) {
        System.out.println("AdvanceReceive3: " + user.toString());
    }

}