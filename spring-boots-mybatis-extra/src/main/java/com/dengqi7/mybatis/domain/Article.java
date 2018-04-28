package com.dengqi7.mybatis.domain;


import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author dengqi
 * @date 2018-04-26
 */
@Setter
@Getter
public class Article {

    private long id;
    private String title;
    private String content;
    private Date createDate;
    private Date createTime;
    private long authorId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
