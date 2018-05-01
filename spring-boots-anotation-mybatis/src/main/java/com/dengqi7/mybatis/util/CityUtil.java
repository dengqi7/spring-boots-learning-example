package com.dengqi7.mybatis.util;

import com.dengqi7.mybatis.domain.City;

/**
 * @author dengqi
 * @date 2018-04-24
 */
public class CityUtil {

    public static String creteCityJson(long id ,long privinceId,String cityName,String description ){
        City city = new City();
        city.setId(id);
        city.setProvinceId(privinceId);
        city.setCityName(cityName);
        city.setDescription(description);
        return city.toString();
    }


    public static void main(String[] args) {
        String s = creteCityJson(3,3,"荆州","故乡");
        System.out.println(s);
    }
}
