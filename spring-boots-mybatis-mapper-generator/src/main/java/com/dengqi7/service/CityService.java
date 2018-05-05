package com.dengqi7.service;


import com.dengqi7.domain.City;

import java.util.List;

/**
 * @author dengqi
 * @date 2018-04-24
 */
public interface CityService {

    /**根据城市名称查询城市信息
     * @param cityName
     * @return
     */
    City findCityByName(String cityName);

    /**
     * 根据城市编号查询城市信息
     * @param id
     * @return
     */
    City findCitybyId(Integer id);

    /**
     * 得到所有城市信息
     * @return
     */
    List<City> findAllCities();


    /**
     * 添加城市信息
     * @param city
     */
    void addCityInfo(City city);

    /**
     * 更新城市信息
     * @param city
     */
    void updateCity(City city);
    /**
     * 删除指定城市
     * @param id
     */
    void delCityInfo(Integer id);

    void insertCities(List<City> list);
}
