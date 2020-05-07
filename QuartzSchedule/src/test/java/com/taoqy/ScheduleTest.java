package com.taoqy;


import com.taoqy.config2.quartz.SXZDJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/15
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleTest {

//    @Autowired
//    private List<Job> jobList;

    @Autowired(required = false)
    private Map<String, SXZDJob> jobHashMap;

    @Test
    public void getJobList(){
//        System.out.println("======list======");
//        for (Job job : jobList){
//            System.out.println(job.toString()+":"+job.getClass());
//
//        }
        System.out.println("======map======");
        Set<String> strings = jobHashMap.keySet();
        for (String key : strings){
            Job job = jobHashMap.get(key);
            System.out.println("key:"+key+":"+job.toString()+":"+job.getClass());
        }
    }


    @Test
    public void getClassByClassName() throws ClassNotFoundException {

        Class<?> aClass = Class.forName("com.taoqy.service.CronJob");
        Class<? extends Class> aClass1 = aClass.getClass();
        System.out.println(aClass);
        System.out.println(aClass1);

    }

}
