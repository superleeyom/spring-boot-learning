package com.leeyom.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class AdvanceReceiver2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("AdvanceReceive2: " + hello);
    }

}