package com.dengqi7.druid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author dengqi
 * @date 2018-05-08
 */
@Configuration
public class CommonConfiguration {

    //@Bean
    //RedisTemplate redisTemplate(RedisConnectionFactory factory){
    //    RedisTemplate redisTemplate = new RedisTemplate();
    //    redisTemplate.setKeySerializer(new StringRedisSerializer());
    //    new Jackson2JsonRedisSerializer(Object.class);
    //    redisTemplate.afterPropertiesSet();
    //    new StringRedisTemplate();
    //    redisTemplate.boundValueOps("");
    //    return null;
    //}
}
