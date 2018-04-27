package com.dengqi7.mybatis.dao;

import com.dengqi7.mybatis.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    City selectCityByName(@Param("cityName") String cityName);

    /**
     * 根据城市编号查询城市信息
     * @param id
     * @return
     */
    City selectCitybyId(@Param("id") long id);

    /**
     * 得到所有城市信息
     * @return
     */
    List<City> selectAllCities();


    /**
     * 添加城市信息
     * @param city
     */
    void insert(City city);

    /**
     * 更新城市信息
     * @param city
     */
    void updateCity(City city);

    /**
     * 删除指定城市
     * @param id
     */
    void deleteById(@Param("id") long id);
}
