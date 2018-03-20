package com.leeyom.quartz.cron;

import com.leeyom.quartz.cron.scheduler.CronScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动时触发定时任务，方式1，推荐
 */
//@Component
public class MyStartupRunner implements CommandLineRunner {

    @Autowired
    private CronScheduler cronScheduler;

    @Override
    public void run(String... args) throws Exception {
        cronScheduler.scheduleJobs();
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}
