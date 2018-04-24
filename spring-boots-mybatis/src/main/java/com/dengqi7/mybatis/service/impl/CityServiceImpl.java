package com.dengqi7.mybatis.service.impl;

import com.dengqi7.mybatis.dao.CityMapper;
import com.dengqi7.mybatis.domain.City;
import com.dengqi7.mybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dengqi
 * @date 2018-04-24
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityMapper cityMapper;

    @Override
    public City findCityByName(String cityName) {
        return cityMapper.findCityByName(cityName);
    }
}
