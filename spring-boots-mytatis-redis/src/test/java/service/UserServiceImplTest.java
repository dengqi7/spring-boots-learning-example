package service;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.dengqi7.druid.Application;
import com.dengqi7.druid.config.BeanUtil;
import com.dengqi7.druid.domain.master.City;
import com.dengqi7.druid.domain.slaver.User;
import com.dengqi7.druid.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.CharSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.OxmSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dengqi
 * @date 2018-05-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("stringRedisTemplate")
    RedisTemplate stringRedisTemplate;

    @Autowired
    RedisConnectionFactory factory;
    @Test
    public void getUserTest(){
        userService.getUserInfoById(1);
    }

    @Test
    public void test1(){
        stringRedisTemplate.opsForValue().set("key1","1");
        log.info("string-整数-结果：{}",stringRedisTemplate.opsForValue().get("key1"));
    }



    @Test
    public void redisTest(){
        log.info("RedisConnectionFactory实现类：{}",factory.getClass());
        String userName = "李洋";
        String description = "20";
        Long id = 1L;
        Float f = 1.2f;
        Integer age = 20;
        User user = new User(1,userName,age,1);
        City city = new City(1,1,"北京",description);

        List list = new ArrayList();
        list.add(userName);
        list.add(description);

        Map map = new HashMap();
        map.put("name",userName);
        map.put("des",description);

        ValueOperations valueOperations = redisTemplate.opsForValue();
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps("");
        HashOperations hashOperations = redisTemplate.opsForHash();
        /**
         * 不同的序列器的序列化结果展示：
         */

        JdkSerializationRedisSerializer jdk = new JdkSerializationRedisSerializer();
        printBtyeToString("JdkSerialization-汉字:{}",jdk.serialize(userName));
        printBtyeToString("JdkSerialization-字母:{}",jdk.serialize(description));
        printBtyeToString("JdkSerialization-整数:{}",jdk.serialize(age));
        printBtyeToString("JdkSerialization-Long:{}",jdk.serialize(id));
        printBtyeToString("JdkSerialization-小数:{}",jdk.serialize(f));
        printBtyeToString("JdkSerialization-POJO:{}",jdk.serialize(city));
        printBtyeToString("JdkSerialization-POJO有空元素:{}",jdk.serialize(user));
        printBtyeToString("JdkSerialization-LIST:{}",jdk.serialize(list));
        printBtyeToString("JdkSerialization-MAP:{}",jdk.serialize(map));
        System.out.println();

        StringRedisSerializer str = new StringRedisSerializer();
        printBtyeToString("StringRedisSerializer-汉字:{}",str.serialize(userName));
        printBtyeToString("StringRedisSerializer-字母:{}",str.serialize(description));
        printBtyeToString("StringRedisSerializer-数字:{}",str.serialize(age.toString()));
        printBtyeToString("StringRedisSerializer-Long:{}",str.serialize(id.toString()));
        printBtyeToString("StringRedisSerializer-小数:{}",str.serialize(f.toString()));
        //log.info("StringRedisSerializer-POJO:{}",str.serialize(city));
        //log.info("StringRedisSerializer-POJO有空元素:{}",str.serialize(user));
        //log.info("StringRedisSerializer-LIST:{}",str.serialize(list));
        //log.info("StringRedisSerializer-MAP:{}",str.serialize(map));
        //Object o = BeanUtil.getBean("")
        Jackson2JsonRedisSerializer jack = new Jackson2JsonRedisSerializer(Object.class);
        jack.deserialize(jack.serialize(userName)).toString();
        printBtyeToString("Jackson2Json-汉字:{}",jack.serialize(userName));
        log.info("Jackson2Json-汉字反序列化:{}", jack.deserialize(jack.serialize(userName)).toString());
        printBtyeToString("Jackson2Json-字母:{}",jack.serialize(description));
        printBtyeToString("Jackson2Json-数字:{}",jack.serialize(age));
        printBtyeToString("Jackson2Json-数字:{}",jack.serialize(age));
        printBtyeToString("Jackson2Json-long:{}",jack.serialize(id));
        printBtyeToString("Jackson2Json-小数:{}",jack.serialize(f));

        printBtyeToString("Jackson2Json-POJO:{}",jack.serialize(city));
        printBtyeToString("Jackson2Json-POJO有空元素:{}",jack.serialize(user));
        printBtyeToString("Jackson2Json-LIST:{}",jack.serialize(list));
        printBtyeToString("Jackson2Json-MAP:{}",jack.serialize(map));
        System.out.println();

        //OxmSerializer oxm = new OxmSerializer();
        //printBtyeToString("OxmSerializer-汉字:{}",oxm.serialize(userName));
        //printBtyeToString("OxmSerializer-字母:{}",oxm.serialize(description));
        //printBtyeToString("OxmSerializer-数字:{}",oxm.serialize(age));
        //printBtyeToString("OxmSerializer-POJO:{}",oxm.serialize(city));
        //printBtyeToString("OxmSerializer-POJO有空元素:{}",oxm.serialize(user));
        //printBtyeToString("OxmSerializer-LIST:{}",oxm.serialize(list));
        //printBtyeToString("OxmSerializer-MAP:{}",oxm.serialize(map));
        //System.out.println();


    }

    public void printByte(String msg,byte[] info){
        String ss = DatatypeConverter.printHexBinary(info);

        log.info(msg,ss);

    }

    public void printBtyeToString(String msg ,byte[] info){
        String s = new String(info);
        log.info(msg,s);
    }


}
