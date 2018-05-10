package service;

import com.dengqi7.druid.Application;
import com.dengqi7.druid.config.BeanUtil;
import com.dengqi7.druid.domain.master.City;
import com.dengqi7.druid.domain.slaver.User;
import com.dengqi7.druid.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dengqi
 * @date 2018-05-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserServiceImpl {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisConnectionFactory factory;

    @Test
    public void getUserTest(){
        userService.getUserInfoById(1);
    }


    @Test
    public void redisTest(){
        log.info("RedisConnectionFactory实现类：{}",factory.getClass());
        String userName = "李洋";
        String description = "aaaa";
        Long id = 1L;
        Integer age = 20;
        User user = new User(1,userName,age,1);
        City city = new City(1,1,"北京",description);


        //Object o = BeanUtil.getBean("")

        //
        //redisTemplate.opsForValue().set("user_1",user);
        //redisTemplate.opsForValue().set("city_1",city);


    }
}
