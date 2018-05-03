package com.dengqi7.mybatis.service.impl;

import com.dengqi7.mybatis.dao.CityMapper;
import com.dengqi7.mybatis.domain.City;
import com.dengqi7.mybatis.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author dengqi
 * @date 2018-04-24
 */
@Service
@Slf4j
public class CityServiceImpl implements CityService {

    @Autowired
    CityMapper cityMapper;

    @Override
    public City findCityByName(String cityName) {
        return cityMapper.selectCityByName(cityName);
    }

    @Override
    public City findCitybyId(long id) {
        City city = cityMapper.selectCitybyId(id);
        log.info("返回city："+city.toString());
        return city;
    }

    @Override
    public List<City> findAllCities() {
        return cityMapper.selectAllCities();
    }

    @Override
    public void addCityInfo(City city) {
        cityMapper.insert(city);
    }

    @Override
    public void updateCity(City city) {
        cityMapper.updateCity(city);
    }

    @Override
    public void delCityInfo(long id) {
        cityMapper.deleteById(id);
    }
}
