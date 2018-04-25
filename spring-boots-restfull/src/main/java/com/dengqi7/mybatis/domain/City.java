package com.dengqi7.mybatis.domain;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dengqi
 * @date 2018-04-24
 */
@Setter
@Getter
public class City {

    private long id;
    private long provinceId;
    private String cityName;
    private String description;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
