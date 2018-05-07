package com.dengqi7.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author dengqi
 * @date 2018-05-07
 */
@Configuration
@MapperScan(basePackages = SlaverDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "slaverSqlSessionFactoryBean")
public class SlaverDataSourceConfig {

    static final String PACKAGE = "com.dengqi7.druid.mapper.slaver";

    static final String MAPPER_LACATION = "classpath:mappers/slaver/*.xml";

    @Value("${slaver.spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${slaver.spring.datasource.username}")
    private String userName;

    @Value("${slaver.spring.datasource.password}")
    private String password;

    @Value("${slaver.spring.datasource.url}")
    private String url;


    @Bean("slaverDateSource")
    public DataSource slaverDateSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(userName);
        return dataSource;
    }


    @Bean("slaverTransactionManager")
    public DataSourceTransactionManager slaverTransactionManager(){
        return new DataSourceTransactionManager(slaverDateSource());
    }

    @Bean("slaverSqlSessionFactoryBean")
    public SqlSessionFactoryBean slaverSqlSessionFactoryBean(@Qualifier("slaverDateSource")DataSource slaverDateSource) throws IOException {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(slaverDateSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(SlaverDataSourceConfig.MAPPER_LACATION));
        return sessionFactoryBean;
    }

}
