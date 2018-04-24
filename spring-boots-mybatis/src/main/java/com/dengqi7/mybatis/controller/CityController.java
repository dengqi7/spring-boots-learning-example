package com.dengqi7.mybatis.controller;

import com.dengqi7.mybatis.domain.City;
import com.dengqi7.mybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author dengqi
 * @date 2018-04-24
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityService cityService;

    @PostMapping("/getCityInfoByName")
    public City findCityByName(@RequestParam(value="name",required = true) String name){
        System.out.println("入参：" + name);
        return cityService.findCityByName(name);
    }

}
