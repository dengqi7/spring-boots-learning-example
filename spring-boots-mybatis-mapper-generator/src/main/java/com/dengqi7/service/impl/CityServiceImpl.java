package com.dengqi7.service.impl;

import com.dengqi7.domain.City;
import com.dengqi7.mapper.CityMapper;
import com.dengqi7.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dengqi
 * @date 2018-04-24
 */
@Service
public class CityServiceImpl implements CityService {
    private Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    CityMapper cityMapper;

    @Override
    public City findCityByName(String cityName) {
        logger.info("service-入参：{}",cityName);
        City city = new City();
        city.setCityName(cityName);
        List<City> list = cityMapper.select(city);
        logger.info("service-结果：{}",list);
        return list.size()>0?list.get(0):null;
    }

    @Override
    public City findCitybyId(Integer id) {
        logger.info("service-入参：{}",id);
        City city = new City();
        city.setId(id);
        city = cityMapper.selectByPrimaryKey(city);
        logger.info("service-结果：{}",city.toString());
        return city;
    }

    @Override
    public List<City> findAllCities() {
        List<City> list = cityMapper.selectAll();
        logger.info("service-结果：{}",list.toString());
        return list;
    }

    @Override
    public void addCityInfo(City city) {
        logger.info("service-入参：{}",city);
        cityMapper.insert(city);
        logger.info("service-结果：{}",city);
    }

    @Override
    public void updateCity(City city) {
        logger.info("service-入参：{}",city);
        //int i = cityMapper.updateByPrimaryKey(city);
        int i = cityMapper.updateByPrimaryKeySelective(city);
        logger.info("service-结果：{}",i);
    }

    @Override
    public void delCityInfo(Integer id) {
        logger.info("service-入参：{}",id);
        City city = new City();
        city.setId(id);
        int i = cityMapper.deleteByPrimaryKey(city);
        logger.info("service-结果：{}",i);
    }

    @Override
    public void insertCities(List<City> list) {
        cityMapper.insertList(list);
    }
}
