
1、redis与springboots集成，配置参数设置:
    SpringBoots默认注入的RedisConnectionFactory的实现类是JedisConnectionFactory;
    1）设置基础属性：host、port、password、timeout、database=0，默认usePool = true，useSsl = false;；
    2）afterPropertiesSet()方法：
        a.new默认的JedisShardInfo对象，host，port，password，timeout
        b.usePool为true(默认)，clusterConfig为空时，创建redis连接池，赋值pool；如果sentinelConfig非空，创建JedisSentinelPool，
            否则创建JedisPool实例，。
        c.clusterConfig非空时，创建JedisCluster，赋值cluster,还有ClusterCommandExecutor属性。
    3）sentinel参数会注入RedisSentinelConfiguration中，属性有：spring.redis.sentinel.master，spring.redis.sentinel.nodes
    4）线程池参数注入GenericObjectPoolConfig中，前缀spring.redis.pool，max-idle、min-idle、max-wait等。

2、spring-data-redis包下的redisTemple使用：
    1）原始使用，默认使用jdk原生序列化方式，查看redis数据库里存储方式，long，String，Object，List
    2）配置不同的序列化器，查看存储方式的区别

    SpringBoots默认注入的RedisConnectionFactory的实现类是JedisConnectionFactory

3、bondValue和ops的使用

4、redsiTemple的api方法

5、注解使用，缓存方法结果。