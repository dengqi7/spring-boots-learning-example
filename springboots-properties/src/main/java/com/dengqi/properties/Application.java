package com.dengqi.properties;

import com.dengqi.properties.domain.HomeProperties;
import com.dengqi.properties.domain.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 * @description
 * @date 2018-04-23
 */
@SpringBootApplication
//@EnableAutoConfiguration(exclude = database)
public class Application implements CommandLineRunner {

    @Autowired
    private HomeProperties homeProperties;

    @Autowired
    private UserProperties userProperties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("\n"+ homeProperties.toString());
        System.out.println();
        System.out.println("\n"+ userProperties.toString());
        System.out.println();
    }
}
