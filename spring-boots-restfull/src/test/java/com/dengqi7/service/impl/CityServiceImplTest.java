package com.dengqi7.service.impl;

import com.dengqi7.mybatis.domain.City;
import com.dengqi7.mybatis.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author dengqi
 * @date 2018-04-24
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CityServiceImplTest {

    @Autowired
    CityService cityService;


    @Test
    public void getCityInfoByName(){
        City city = cityService.findCityByName("北京");
        System.out.println(city);
    }

    @Test
    public void findCityById(){
        City city = cityService.findCitybyId(1);
        System.out.println(city);
    }

}
