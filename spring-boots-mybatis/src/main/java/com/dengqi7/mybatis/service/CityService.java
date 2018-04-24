package com.dengqi7.mybatis.service;

import com.dengqi7.mybatis.domain.City;

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

}
