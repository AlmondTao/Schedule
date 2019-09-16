package com.taoqy.config2.quartz;

import com.taoqy.config2.KeyGreaterUtil2;
import org.quartz.SchedulerException;
import org.quartz.spi.InstanceIdGenerator;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/27
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
public class MyInstancdIdGenerator implements InstanceIdGenerator {
    @Override
    public String generateInstanceId() throws SchedulerException {
        return KeyGreaterUtil2.getUuid();
    }
}
