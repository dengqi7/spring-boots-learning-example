package com.dengqi7.druid.service;

import com.dengqi7.druid.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dengqi
 * @date 2018-05-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceImpl {

    @Autowired
    UserService userService;

    @Test
    public void getUserTest(){
        userService.getUserInfoById(2);
    }

}
