
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
    1）afterPropertiesSet()方法：
        没有显示设置序列器的话，会设置默认序列化器JdkSerializationRedisSerializer
        四个设置序列器的属性：key、value、hashKey、hashValue。
    +2）原始使用，默认使用jdk原生序列化方式，查看redis数据库里存储方式，long，String，Object，List
    +3）配置不同的序列化器，查看存储方式的区别
        JdkSerializationRedisSerializer是原生的jdk的序列化方式；
        StringRedisSerializer是用String.getBytes()的方式完成序列化，存储的还是字符串，但不适用于对象型数据；
        Jackson2JsonRedisSerializer使用JsonMapper将对象转换成json字符串。

    注意：针对数字(Long Float)的incrby 与decrby操作，使用jdk序列化无法完成，因为redis数据无法识别。StringRedisSerializer与
            Jackson2JsonRedisSerializer可以，存储的是数字字符串与数字。redis会自动将数字字符串转为数字类型。

         对于字符串，Jackson2JsonRedisSerializer序列化成json串时会加引号，需要反序列化才能去双引号。

     详见测试方法UserServiceImplTest.redisTest();

    4)自定义序列化配置方法，见CommonConfiguration

3、bodValue和ops的使用
    +ValueOperations针对字符串以及数字进行操作，过了一遍方法以及对应的redis指令;
    +BoundValueOperations内部也是调用ValueOperations，只是多了一个属性key，这样对同一个key进行操作时，不需要反复调get方法，更方便；

4、redsiTemple的api方法
    .......

5、注解使用，缓存方法结果。
    ...需要搭redis服务才能测


第二部分：
    redis设计与实现回顾：

1.数据类型：strings、lists、Hashes、sets、sorted sets

2、数据结构：
    字符串strings  ： 数字型Long Float，字符串型 SDS（简单动态字符串）
                     运用：   incrby、decrby，可作为计数器、奖金池使用

    列表lists     ： 基于linked list，常数时间的插入以及列表首尾删除元素，常数时间长度内得到列表长度。最多2E32-1个元素，40亿
                    指令：lpush、rpush、lrang、llen、ltrim、lpop、blpop
                    场景运用：lpush、ltrim,维护一个元素数量指定的列表，并通过lrange得到前N个后者后N个元素，比如排名表；
                             带阻塞的消息队列

    集合sets     ：无序的字符串合集，最大的特性是元素值不重复，同时还有求交集、并集、差集的指令。
                    指令：sadd、scard、sdiff、sinter、sunion、sismember
                    运用：1、集合元素去重，
                         2、tagging分类系统，用集合来表示单个tag，所有有改tag的对象的id用sadd加入集合中，遇到需要查找同时
                            有3个tag的某个对象时，使用sinter指令即可；
                         3、srangemember随机获取集合元素，spop随机删除元素。
    hashes      ： key-value , 用来表示map或者对象
                    指令：hset、hget、hdel、hincrby、hkeys、hvals、hlen

    ZSet    ：

3、数据结构与数据类型的对应关系
    strings  ：
    列表lists   ：
    集合sets     ：
    hash    ：
    ZSet    ：

4、redis持久化

5、主备通信

6、集群

7、事务

8、消息频道

9、Redis哨兵（Sentinel）和自动 分区（Cluster）
