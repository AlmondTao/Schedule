package com.taoqy.service;


import com.taoqy.config2.quartz.SXZDJob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/14
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Component
public class CronJob implements SXZDJob {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        System.out.println("=========================定时任务每5秒执行一次===============================");
//        System.out.println("jobName=====:"+context.getJobDetail().getKey().getName());
//        System.out.println("jobGroup=====:"+context.getJobDetail().getKey().getGroup());
//        System.out.println("taskData=====:"+context.getJobDetail().getJobDataMap().get("taskData"));
        System.out.println("CronJob执行定时任务,我需要执行10s，线程id ==> {"+Thread.currentThread().getId()+ "} "+ LocalDateTime.now().toLocalTime());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CronJob结束定时任务，线程id ==> {"+Thread.currentThread().getId()+ "} "+ LocalDateTime.now().toLocalTime());

    }

    @Override
    public String getCron() {

            return "*/20 * * * * ?";
    }

    @Override
    public String getJobName() {
        return "test1";
    }

    @Override
    public String getJobGroup(){
        return "group1";
    }

    @Override
    public boolean disabled() {
        return true;
    }
}
