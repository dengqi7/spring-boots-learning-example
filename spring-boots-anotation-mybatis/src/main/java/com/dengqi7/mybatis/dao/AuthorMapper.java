package com.dengqi7.mybatis.dao;

import com.dengqi7.mybatis.domain.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 作者实体类
 * @author dengqi
 * @date 2018-04-28
 */
public interface AuthorMapper extends Mapper {

    Author selectAuthorByInId(@Param("id") long id);

    Author selectAuthorAllInfoById(@Param("id") long id);

    void insert(Author author);

    void update(Author author);

    void delete(@Param("id") long id);
}
