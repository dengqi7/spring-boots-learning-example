package com.dengqi7.mybatis.dao;

import com.dengqi7.mybatis.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 城市DAO接口类
 * @author dengqi
 * @date 2018-04-24
 */
public interface CityMapper {

    /**根据城市名称查询城市信息
     * @param cityName
     * @return
     */
    City findCityByName(@Param("cityName") String cityName);

}
