package com.leeyom.rabbitmq.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbitmq配置
 * @author leeyom
 * @date 2018年02月26日 下午10:24
 */
@Configuration
public class RabbitConfig {

    /**
     * 定义一个队列，队列的名字叫"hello"
     * @return
     */
    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }

}
