package com.taoqy.schedule;

import com.taoqy.dao.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/12
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */

@Component
@Configuration
//@Async
public class TestSchedule implements SchedulingConfigurer {

    @Autowired
    private ScheduleMapper scheduleMapper;


    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(getAsyncExecutor());
//        Set<ScheduledTask> scheduledTasks = taskRegistrar.getScheduledTasks();
//        for (ScheduledTask scheduledTask:scheduledTasks){
//
//        }
        //当方法的执行时间超过任务调度频率时，调度器会在下个周期执行。
//        taskRegistrar.afterPropertiesSet();
//        taskRegistrar.destroy();
        taskRegistrar.addTriggerTask(
                ()->{
                    System.out.println("TEST1执行定时任务,我需要执行10s，线程id ==> {"+Thread.currentThread().getId()+ "} "+ LocalDateTime.now().toLocalTime());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("TEST1结束定时任务，线程id ==> {"+Thread.currentThread().getId()+ "} "+ LocalDateTime.now().toLocalTime());
                    },
                triggerConText->{
                    Integer id = 1;
                    String cron = scheduleMapper.getCron(id);
                if (StringUtils.isEmpty(cron)){

                }
                return new CronTrigger(cron).nextExecutionTime(triggerConText);

                }
        );

    }

    public Executor getAsyncExecutor(){
        return Executors.newScheduledThreadPool(20);
    }
}
