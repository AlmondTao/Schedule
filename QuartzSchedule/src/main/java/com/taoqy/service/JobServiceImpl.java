package com.taoqy.service;

import com.taoqy.config2.quartz.SXZDJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/14
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Service
public class JobServiceImpl implements  JobService {
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 创建一个定时任务
     *
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void addCronJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                System.out.println("job:" + jobName + " 已存在");
            } else {

                //构建job信息
                jobDetail = JobBuilder.newJob(SXZDJob.class).withIdentity(jobName, jobGroup).build();
                //用JopDataMap来传递数据
                jobDetail.getJobDataMap().put("taskData", "hzb-cron-001");

                //表达式调度构建器(即任务执行的时间,每2秒执行一次)
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/2 * * * * ?");

                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName + "_trigger", jobGroup + "_trigger")
                        .withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAsyncJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                System.out.println("job:" + jobName + " 已存在");
            }
            else {
                //构建job信息,在用JobBuilder创建JobDetail的时候，有一个storeDurably()方法，可以在没有触发器指向任务的时候，将任务保存在队列中了。然后就能手动触发了
                jobDetail = JobBuilder.newJob(AsyncJob.class).withIdentity(jobName, jobGroup).storeDurably().build();
                jobDetail.getJobDataMap().put("asyncData","this is a async task");
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName + "_trigger", jobGroup + "_trigger") //定义name/group
                        .startNow()//一旦加入scheduler，立即生效
                        .withSchedule(simpleSchedule())//使用SimpleTrigger
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void pauseJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");

            scheduler.pauseTrigger(triggerKey);
            System.out.println("=========================pause job:" + jobName + " success========================");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复任务
     *
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void resumeJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");
            scheduler.resumeTrigger(triggerKey);
            System.out.println("=========================resume job:" + jobName + " success========================");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName,jobGroup);
            scheduler.deleteJob(jobKey);
            System.out.println("=========================delete job:" + jobName + " success========================");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void reSetCronJob(String jobName, String jobGroup, String cron) {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("healer", "hehehe2");
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(
                    jobName + "_trigger", jobGroup + "_trigger")
                    .withSchedule(scheduleBuilder).usingJobData(jobDataMap).build();
            scheduler.rescheduleJob(triggerKey, trigger);
//            scheduler.resumeTrigger(triggerKey);
            System.out.println("=========================restart job:" + jobName+":"+cron + " success========================");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrigger(String triggerName, String triggerGroup) {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("healer", "hehehe2");
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup)
//                    .withSchedule(scheduleBuilder).usingJobData(jobDataMap).build();
            scheduler.unscheduleJob(triggerKey);
//            scheduler.resumeTrigger(triggerKey);
            System.out.println("=========================delete trigger:" + triggerName+":"+triggerGroup + " success========================");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
