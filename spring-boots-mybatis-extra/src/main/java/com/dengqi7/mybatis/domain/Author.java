package com.dengqi7.mybatis.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dengqi
 * @date 2018-04-28
 */
@Getter
@Setter
public class Author {
    private long id;
    private String name;
    private long age;
    private long homeCityId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
