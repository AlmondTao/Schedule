package com.taoqy.config;

import org.quartz.SchedulerFactory;
import org.quartz.TriggerListener;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/13
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
//@Configuration
public class QuartzConfig {

//    @Bean
//    public JobFactory jobFactory(ApplicationContext applicationContext){
//
//        SpringBeanJobFactory springBeanJobFactory = new SpringBeanJobFactory();
//        springBeanJobFactory.setSchedulerContext(applicationContext);
//    }

//    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //用于quartz集群,QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //任务调度监听器
        factory.setGlobalTriggerListeners(triggerListenerLogMonitor());

        return factory;

    }

    /**
     * quartz初始化监听器
     * @return
     */
    public QuartzInitializerListener executorListner(){
        return new QuartzInitializerListener();
    }

    private TriggerListener triggerListenerLogMonitor() {
        return null;
    }

}
