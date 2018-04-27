package com.dengqi7.mybatis.controller;

import com.dengqi7.mybatis.domain.City;
import com.dengqi7.mybatis.service.CityService;
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

    @PostMapping("/getCityInfoByName")
    public City findCityByName(@RequestParam(value="name",required = true) String name){
        System.out.println("入参：" + name);
        return cityService.findCityByName(name);
    }


    @RequestMapping("/id/{id}")
    public City findCity(@PathVariable("id") Long id){
        System.out.println("/id入参："+ id);
        return cityService.findCitybyId(id);
    }

    @RequestMapping("/all")
    public List<City> getAllCities(){
        return cityService.findAllCities();
    }

    @RequestMapping("/save")
    public String saveCity(@RequestBody City city){
        System.out.println("入仓:" + city.toString());
        city.setCityName(null);
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
