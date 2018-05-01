package com.dengqi7.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dengqi
 * @date 2018-04-24
 */
@SpringBootApplication
@MapperScan("com.dengqi7.mybatis.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
