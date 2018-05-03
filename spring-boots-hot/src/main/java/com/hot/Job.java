package com.hot;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author dengqi
 * @date 2018-05-03
 */
@Configuration
@EnableScheduling
public class Job {

    @Scheduled(cron = "0/5 * * * * ?")
    public void say(){
        int i = 0;
        int b = 2;
        System.out.println("定时正常"+i + b);
    }
}
