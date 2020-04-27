package com.taoqy.config2;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/12/3
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Configuration
public class MyWebMvcAdapter implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("hello2");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

    }
}
