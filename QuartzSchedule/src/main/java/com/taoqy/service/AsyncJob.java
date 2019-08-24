package com.taoqy.service;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

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
public class AsyncJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("========================立即执行的任务，只执行一次===============================");
        System.out.println("jobName=====:"+context.getJobDetail().getKey().getName());
        System.out.println("jobGroup=====:"+context.getJobDetail().getKey().getGroup());
        System.out.println("taskData=====:"+context.getJobDetail().getJobDataMap().get("asyncData"));
    }


    public String getCron() {
        return null;
    }
}
