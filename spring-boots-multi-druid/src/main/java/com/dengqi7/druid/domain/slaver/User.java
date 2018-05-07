package com.dengqi7.druid.domain.slaver;

import com.alibaba.fastjson.JSON;
import com.dengqi7.druid.domain.master.City;

public class User {
    private Integer id;

    private String userName;

    private Integer age;

    private Integer homeCityId;

    private City city;

    public User(Integer id, String userName, Integer age, Integer homeCityId) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.homeCityId = homeCityId;
    }



    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHomeCityId() {
        return homeCityId;
    }

    public void setHomeCityId(Integer homeCityId) {
        this.homeCityId = homeCityId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}