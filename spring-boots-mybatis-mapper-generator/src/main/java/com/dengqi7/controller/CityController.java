package com.dengqi7.controller;

import com.dengqi7.domain.City;
import com.dengqi7.service.CityService;
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

    @GetMapping("/name")
    public City findCityByName(@RequestParam(value="name",required = true) String name){
        logger.info("cotroller-入参：{}" ,name);
        return cityService.findCityByName(name);
    }


    @RequestMapping("/id/{id}")
    public City findCity(@PathVariable("id") Integer id){
        logger.info("cotroller-入参：{}" ,id);
        return cityService.findCitybyId(id);
    }

    @RequestMapping("/all")
    public List<City> getAllCities(){
        return cityService.findAllCities();
    }

    @RequestMapping("/save")
    public String saveCity(@RequestBody City city){
        logger.info("cotroller-入参：{}" ,city);
        cityService.addCityInfo(city);
        return "保存成功";
    }

    @RequestMapping("/update")
    public String updateCity(@RequestBody City city){
        logger.info("cotroller-入参：{}" ,city);
        cityService.updateCity(city);
        return "更新成功";
    }

    @RequestMapping("/del/{id}")
    public String deleteCity(@PathVariable("id") Integer id){
        logger.info("cotroller-入参：{}" ,id);
        cityService.delCityInfo(id);
        return "删除城市信息成功，id= " + id;
    }
}
