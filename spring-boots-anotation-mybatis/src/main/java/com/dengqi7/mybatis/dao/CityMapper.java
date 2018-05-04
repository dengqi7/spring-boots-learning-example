package com.dengqi7.mybatis.dao;

import com.dengqi7.mybatis.domain.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 城市DAO接口类
 * @author dengqi
 * @date 2018-04-24
 */
public interface CityMapper{

    /**根据城市名称查询城市信息
     * @param cityName
     * @return
     */


    @Select("select id , province_id as provinceId, city_name as cityName, description from city where city_name = #{cityName}")
    City selectCityByName(@Param("cityName") String cityName);

    /**
     * 根据城市编号查询城市信息
     * @param id
     * @return
     */

    @Select("select id , province_id as provinceId, city_name as cityName, description from city where id = #{id}")
    City selectCitybyId(@Param("id") long id);

    /**
     * 得到所有城市信息
     * @return
     */
    @Select("select id , province_id as provinceId, city_name as cityName, description from city")
    List<City> selectAllCities();


    /**
     * 添加城市信息
     * @param city
     */

    @Insert("insert into city (province_id,city_name,description) values (#{provinceId},#{cityName},#{description})")
    @Options(useGeneratedKeys = true)
    void insert(City city);

    /**
     * 更新城市信息
     * @param city
     */
    @UpdateProvider(type = CityProvider.class ,method="updateCity")
    void updateCity(City city);

    /**
     * 删除指定城市
     * @param id
     */
    @Delete("delete from city where id = #{id}")
    void deleteById(@Param("id") long id);
}
