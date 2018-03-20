package com.leeyom.quartz.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用spring boot 自带的注解 @Schedule 实现简单的定时任务
 */
@Component
public class Scheduler2Task {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每隔6秒打印一次时间
     */
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("date：" + dateFormat.format(new Date()));
    }
}
