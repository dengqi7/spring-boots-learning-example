package com.dengqi7.mybatis.dao;

import com.dengqi7.mybatis.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author dengqi
 * @date 2018-04-26
 */
public interface ArticleMapper {


    Article selectOne(@Param("id") long id);

    void insert(Article article);
}
