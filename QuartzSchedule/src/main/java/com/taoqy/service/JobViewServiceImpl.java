package com.taoqy.service;

import com.taoqy.dao.QrtzMapper;
import com.taoqy.entity.QrtzJobDetails;
import com.taoqy.entity.ServiceResult;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/12/5
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Service
public class JobViewServiceImpl implements  JobViewService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private QrtzMapper qrtzMapper;

    @Override
    public ServiceResult getJobGroupList(String schedName) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<String> jobGroupNames = null;
        try {
            jobGroupNames = scheduler.getJobGroupNames();

        } catch (SchedulerException e) {

            e.printStackTrace();
        }

        return new ServiceResult(true, "成功",jobGroupNames);
    }

    @Override
    public ServiceResult getJobList(String schedName) {


        return null;
    }
}
