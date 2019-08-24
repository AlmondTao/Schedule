package com.taoqy.config2.quartz;


import com.alibaba.druid.pool.DruidDataSource;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/14
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Configuration
public class ScheduleConfiguration {

    @Autowired
    private MyJobFactory myJobFactory;

    @Autowired(required = false)
    private List<SXZDJob> jobList;

    //直接注入报错
//    @Autowired
//    private DruidDataSource druidDataSource;

    private static Logger logger = LoggerFactory.getLogger(ScheduleConfiguration.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    //在方法里作为参数注入可以
    @Autowired
//    @Bean("mySchedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(DruidDataSource druidDataSource) throws Exception {
        //获取配置属性
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        //创建SchedulerFactoryBean
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        Properties pro = propertiesFactoryBean.getObject();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setJobFactory(myJobFactory);
        schedulerFactoryBean.setDataSource(druidDataSource);
        schedulerFactoryBean.setQuartzProperties(pro);
        schedulerFactoryBean.afterPropertiesSet();


//        addCronJob(schedulerFactoryBean);

        return schedulerFactoryBean;
    }

    @Autowired
//    @Qualifier("mySchedulerFactoryBean")
    private void addCronJob(SchedulerFactoryBean schedulerFactoryBean) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if (jobList == null || jobList.size() == 0){
                logger.warn("无定时任务");
            }else {
                for (SXZDJob job :jobList){
                    String jobName = job.getJobName();
                    if (StringUtils.isEmpty(jobName)){
                        logger.error(job.getClass()+" 定时任务的jobName不能为空", new NullPointerException());
                    }
                    String jobGroup = job.getJobGroup();
                    if (StringUtils.isEmpty(jobGroup)){
                        logger.error(job.getClass()+" 定时任务的jobGroup不能为空", new NullPointerException());
                    }
                    JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
                    TriggerKey triggerKey = TriggerKey.triggerKey(getTriggerName(jobName), getTriggerGroup(jobGroup));
                    //用JopDataMap来传递数据
                    JobDataMap jobDataMap = new JobDataMap();
                    jobDataMap.put("healer", "hehehe2");
                    if (job.disabled()){

                        JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                        if (jobDetail != null) {
                            logger.warn("jobGroup:"+jobGroup+" jobName:"+jobName+ " 已存在");
                        } else {

                            //构建job信息
                            jobDetail = JobBuilder.newJob(job.getClass()).usingJobData(jobDataMap).withIdentity(jobName, jobGroup).build();

                        }

                        Trigger trigger = scheduler.getTrigger(triggerKey);
                        if (trigger != null){
                            logger.warn("jobGroup:"+getTriggerGroup(jobGroup)+" jobName:"+getTriggerName(jobName)+ " 已存在");
                            scheduler.resumeTrigger(triggerKey);
                        }else{
                            String cron = job.getCron();
                            if (StringUtils.isEmpty(cron)){
                                logger.error("定时任务的cron不能为空", new NullPointerException());
                            }
                            //表达式调度构建器(即任务执行的时间,每2秒执行一次)
                            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

                            //按新的cronExpression表达式构建一个新的trigger
                            trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerName(jobName), getTriggerGroup(jobGroup))
                                    .withSchedule(scheduleBuilder).usingJobData(jobDataMap).build();
                            scheduler.scheduleJob(jobDetail, trigger);
                        }
                        logger.warn("jobGroup:"+jobGroup+" jobName:"+jobName+" 已开启");

                    }else {

                        scheduler.pauseTrigger(triggerKey);
                        logger.warn("jobGroup:"+jobGroup+" jobName:"+jobName+" 已停止");
                    }

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTriggerName(String jobName){
        return jobName + "_trigger";
    }

    private String getTriggerGroup(String jobGroup){
        return jobGroup + "_trigger";
    }

}
