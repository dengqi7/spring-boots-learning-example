package com.dengqi7.mapper;

import com.dengqi7.domain.City;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author dengqi
 * @date 2018-05-04
 */
public interface BaseMapper<T>extends Mapper<City>,MySqlMapper<City>{
}
