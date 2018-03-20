package com.leeyom.quartz.cron;

import com.leeyom.quartz.cron.scheduler.CronScheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 特定时间启动定时任务，方式2
 */
@Configuration
@EnableScheduling
@Component
public class SchedulerListener {
    @Autowired
    public CronScheduler scheduleJobs;

    /**
     * 定时任务于 3月20号晚上九点二分启动
     * @throws SchedulerException
     */
    @Scheduled(cron = "0 2 21 20 3 ?")
    public void schedule() throws SchedulerException {
        scheduleJobs.scheduleJobs();
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}