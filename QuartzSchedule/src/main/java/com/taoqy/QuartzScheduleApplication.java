package com.taoqy;

import com.taoqy.common.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/13
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@SpringBootApplication
@MapperScan("com.taoqy.dao")
public class QuartzScheduleApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(QuartzScheduleApplication.class, args);
        SpringContextUtil.setApplicationContext(context);


    }
}
