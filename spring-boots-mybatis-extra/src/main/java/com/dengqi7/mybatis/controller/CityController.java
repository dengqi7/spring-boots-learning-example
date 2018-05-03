package com.dengqi7.mybatis.controller;

import com.dengqi7.mybatis.domain.City;
import com.dengqi7.mybatis.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dengqi
 * @date 2018-04-24
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityService cityService;

    Logger logger = LoggerFactory.getLogger(CityController.class);

    @PostMapping("/getCityInfoByName")
    public City findCityByName(@RequestParam(value="name",required = true) String name){
        logger.info("入参：" + name);
        return cityService.findCityByName(name);
    }


    @RequestMapping("/id/{id}")
    public City findCity(@PathVariable("id") Long id){
        logger.info("/id入参："+ id);
        return cityService.findCitybyId(id);
    }

    @RequestMapping("/all")
    public List<City> getAllCities(){
        return cityService.findAllCities();
    }

    @RequestMapping("/save")
    public String saveCity(@RequestBody City city){
        System.out.println("入仓:" + city.toString());
        cityService.addCityInfo(city);
        return "保存成功";
    }

    @RequestMapping("/update")
    public String updateCity(@RequestBody City city){
        cityService.updateCity(city);
        return "更新成功";
    }

    @RequestMapping("/del/{id}")
    public String deleteCity(@PathVariable("id") Long id){
        cityService.delCityInfo(id);
        return "删除城市信息成功，id= " + id;
    }
}
