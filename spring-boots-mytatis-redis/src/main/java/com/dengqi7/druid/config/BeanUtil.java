package com.dengqi7.druid.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author dengqi
 * @date 2018-05-10
 */
@Configuration
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }


    public static Object getBean(Class className){
        Object o = applicationContext.getBean(className);
        return o;
    }

    public static Object getBean(String beanName){
        Object o = applicationContext.getBean(beanName);
        return o;
    }

    public static Object getBean(String beanName,Class className){
        Object o = applicationContext.getBean(beanName,className);
        return o;
    }
}
