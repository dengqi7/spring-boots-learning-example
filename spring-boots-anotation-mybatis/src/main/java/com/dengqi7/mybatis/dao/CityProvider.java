package com.dengqi7.mybatis.dao;

import com.dengqi7.mybatis.domain.City;
import org.apache.commons.lang.StringUtils;

public class CityProvider {

    public String updateCity(City city){

        StringBuilder ss = new StringBuilder("update city set");
        if(city.getProvinceId()!=0){
            ss.append(" province_id = ").append(city.getProvinceId()).append(",");
        }

        if (StringUtils.isNotBlank(city.getCityName())){
            ss.append(" city_name = '").append(city.getCityName()).append("',");
        }

        if(StringUtils.isNotBlank(city.getDescription())){
            ss.append(" description = '").append(city.getDescription()).append("'");
        }

        String result = ss.toString();
        if(result.endsWith(",")){
            result = result.substring(0,result.length()-1);
        }
            result += "where id = " + city.getId();
        System.out.println("更新语句：" + result);
        return result;
    }

}
