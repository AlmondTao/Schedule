package com.taoqy.schedule;

import com.taoqy.dao.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/12
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
//
//@Component
//@Configuration
//@EnableAsync
public class TestSchedule3 implements SchedulingConfigurer  {

    @Autowired
    private ScheduleMapper scheduleMapper;

    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                ()->{
                    System.out.println("TEST3执行定时任务,我需要执行4s，线程id ==> {"+Thread.currentThread().getId()+ "} "+LocalDateTime.now().toLocalTime());
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("TEST3结束定时任务，线程id ==> {"+Thread.currentThread().getId()+ "} "+ LocalDateTime.now().toLocalTime());
                },
                triggerConText->{
                    Integer id = 2;
                    String cron = scheduleMapper.getCron(id);
                if (StringUtils.isEmpty(cron)){

                }
                return new CronTrigger(cron).nextExecutionTime(triggerConText);

                }
        );

    }
}
