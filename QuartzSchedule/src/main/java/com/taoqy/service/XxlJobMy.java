package com.taoqy.service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2020/5/6
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Component
public class XxlJobMy {

    private static Logger logger = LoggerFactory.getLogger(XxlJobMy.class);

    @XxlJob("myXxlJob")
    public ReturnT<String> myXxlJob(String param){
        logger.warn("xxl调用成功！"+new Date().toString());
        return ReturnT.SUCCESS;
    }
}
