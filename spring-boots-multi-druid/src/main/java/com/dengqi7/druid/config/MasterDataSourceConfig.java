package com.dengqi7.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.annotations.Mapper;
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
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "")
public class MasterDataSourceConfig {

    static final String PACKAGE = "com.dengqi7.druid.mapper.master";

    static final String MAPPER_LACATION = "classpath:mappers/master/*.xml";

    @Value("${master.spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${master.spring.datasource.username}")
    private String userName;

    @Value("${master.spring.datasource.password}")
    private String password;

    @Value("${master.spring.datasource.url}")
    private String url;


    @Bean("masterDateSource")
    @Primary
    public DataSource masterDateSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(userName);
        return dataSource;
    }


    @Bean("masterTransactionMannager")
    @Primary
    public DataSourceTransactionManager masterTransactionMannager(){
        return new DataSourceTransactionManager(masterDateSource());
    }

    @Bean("masterSqlSessionFactoryBean")
    @Primary
    public SqlSessionFactoryBean masterSqlSessionFactoryBean(@Qualifier("masterDateSource")DataSource masterDateSource) throws IOException {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(masterDateSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MasterDataSourceConfig.MAPPER_LACATION));
        return sessionFactoryBean;
    }

}
