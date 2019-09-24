package com.taoqy.service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/14
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
public interface JobService {

    /**
     * 添加一个定时任务
     * @param jobName
     * @param jobGroup
     */
    void addCronJob(String jobName, String jobGroup);

    /**
     * 添加异步任务
     * @param jobName
     * @param jobGroup
     */
    void addAsyncJob(String jobName, String jobGroup);

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复任务
     * @param triggerName
     * @param triggerGroup
     */
    void resumeJob(String triggerName, String triggerGroup);

    /**
     * 删除job
     * @param jobName
     * @param jobGroup
     */
    void deleteJob(String jobName, String jobGroup);
    /**
     * 重新设置job
     * @param jobName
     * @param jobGroup
     */
    void reSetCronJob(String jobName, String jobGroup, String cron);

    void deleteTrigger(String triggerName, String triggerGroup);
}
