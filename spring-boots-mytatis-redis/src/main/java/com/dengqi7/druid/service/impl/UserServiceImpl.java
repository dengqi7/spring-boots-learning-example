package com.dengqi7.druid.service.impl;

import com.dengqi7.druid.domain.master.City;
import com.dengqi7.druid.domain.slaver.User;
import com.dengqi7.druid.mapper.master.CityMapper;
import com.dengqi7.druid.mapper.slaver.UserMapper;
import com.dengqi7.druid.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dengqi
 * @date 2018-05-07
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CityMapper cityMapper;

    @Override
    public User getUserInfoById(Integer id) {
        log.info("入參：{}",id);
        User user = userMapper.selectByPrimaryKey(id);
        log.info("user:{}",user);
        if(user!=null&&user.getHomeCityId()!=null){
            City city = cityMapper.selectByPrimaryKey(user.getHomeCityId());
            log.info("city:{}",city);
            user.setCity(city);
        }
        return user;
    }
}
