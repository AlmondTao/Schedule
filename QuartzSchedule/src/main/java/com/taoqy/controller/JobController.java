package com.taoqy.controller;

import com.taoqy.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/14
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@RestController
@RequestMapping("/quartztest")
public class JobController {
    @Autowired
    private JobService jobService;


    /**
     * 创建cron任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/cron",method = RequestMethod.POST)
    public String startCronJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        jobService.addCronJob(jobName,jobGroup);
        return "create cron task success";
    }
    //重新设置cron
    @RequestMapping(value = "/reset",method = RequestMethod.POST)
    public String reSetCronJob(@RequestParam("jobName") String jobName,
                               @RequestParam("jobGroup") String jobGroup,
                               @RequestParam("cron")String cron){
        jobService.reSetCronJob(jobName,jobGroup,cron);
        return "reset cron task success";
    }

    //删除trigger
    //删除trigger后若有没有被trigger引用的job，则job也会被删除
    @RequestMapping(value = "/deleteTrigger",method = RequestMethod.POST)
    public String deleteTrigger(@RequestParam("triggerName") String triggerName,
                               @RequestParam("triggerGroup") String triggerGroup){
        jobService.deleteTrigger(triggerName,triggerGroup);
        return "delete trigger success";
    }

    /**
     * 创建异步任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/async",method = RequestMethod.POST)
    public String startAsyncJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        jobService.addAsyncJob(jobName,jobGroup);
        return "create async task success";
    }

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/pause",method = RequestMethod.POST)
    public String pauseJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        jobService.pauseJob(jobName,jobGroup);
        return "pause job success";
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/resume",method = RequestMethod.POST)
    public String resumeJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        jobService.resumeJob(jobName,jobGroup);
        return "resume job success";
    }

    /**
     * 删除务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    public String deleteJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        jobService.deleteJob(jobName,jobGroup);
        return "delete job success";
    }
}
