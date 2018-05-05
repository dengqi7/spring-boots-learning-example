package com.dengqi.service;

import com.dengqi7.Application;
import com.dengqi7.domain.City;
import com.dengqi7.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dengqi
 * @date 2018-05-04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CityServcieImplTest {

    @Autowired
    private CityService cityService;

    @Test
    public void getCityById(){
        City city = cityService.findCitybyId(1);
        System.out.println(city);

    }

    @Value("${mapper.mappers}")
    List<String> ss ;

    @Test
    public void props(){
        System.out.println(ss);
    }

    @Test
    public void inserCities(){
        List<City> list = new ArrayList<City>();
        City c1 = new City();
        c1.setCityName("上海");
        c1.setProvinceId(8);
        c1.setDescription("魔都");

        City c2 = new City();
        c2.setCityName("杭州");
        c2.setProvinceId(9);
        c2.setDescription("西湖");
        list.add(c1);
        list.add(c2);
        cityService.insertCities(list);
        System.out.println(list);
    }

}
