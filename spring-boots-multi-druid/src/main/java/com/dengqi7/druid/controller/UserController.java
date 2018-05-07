package com.dengqi7.druid.controller;

import com.dengqi7.druid.domain.slaver.User;
import com.dengqi7.druid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengqi
 * @date 2018-05-07
 */
@RestController("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/id/{id}")
    public User getUserById(@PathVariable("id") Integer id){

        User user = userService.getUserInfoById(id);

        return user;
    }
}
