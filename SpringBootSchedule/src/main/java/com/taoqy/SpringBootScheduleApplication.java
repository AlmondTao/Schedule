package com.taoqy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/12
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@SpringBootApplication
@MapperScan("com.taoqy.dao")
@EnableScheduling  //开启定时任务
@EnableAsync
public class SpringBootScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootScheduleApplication.class,args);
    }
}
