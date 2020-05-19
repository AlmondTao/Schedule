package com.taoqy.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2020/5/8
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
public class SpringContextUtil {



    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public static void setApplicationContext(ApplicationContext applicationContext)throws BeansException  {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     *  根据beanId返回Spring中的实例
     * @Date 2019-08-07 17:36
     * @param
     * @return
     **/

    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }
    public static Object getBean(Class beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }

}
