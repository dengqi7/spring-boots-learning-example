package com.dengqi7.mybatis.domain;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dengqi
 * @date 2018-04-24
 */
public class City {


    private long id;
    private long provinceId;
    private String cityName;
    private String description;

    public long getId() {
        return id;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
