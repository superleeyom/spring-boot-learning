package com.leeyom.quartz.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 使用spring boot 自带的注解 @Schedule 实现简单的定时任务
 */
@Component
public class SchedulerTask {

    private int count = 0;

    /**
     * 每隔6秒打印一次
     */
    @Scheduled(cron = "*/6 * * * * ?")
    public void process() {
        System.out.println("this is scheduler task runing  " + (count++));
    }

}
