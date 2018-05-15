
资料来源：http://redis.cn/

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
    2）原始使用，默认使用jdk原生序列化方式，查看redis数据库里存储方式，long，String，Object，List
    3）配置不同的序列化器，查看存储方式的区别
        JdkSerializationRedisSerializer是原生的jdk的序列化方式；
        StringRedisSerializer是用String.getBytes()的方式完成序列化，存储的还是字符串，但不适用于对象型数据；
        Jackson2JsonRedisSerializer使用JsonMapper将对象转换成json字符串。

      注意：针对数字(Long Double)的incrby 与decrby操作，使用jdk序列化无法完成，因为redis数据无法识别。StringRedisSerializer与
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

1.数据类型：

    字符串string  ： 数字型Long Float，字符串型 SDS（简单动态字符串）
                     运用：   incrby、decrby，可作为计数器、奖金池使用

    列表list     ： 基于linked list，常数时间的插入以及列表首尾删除元素，常数时间长度内得到列表长度。最多2E32-1个元素，40亿
                    指令：lpush、rpush、lrang、llen、ltrim、lpop、blpop
                    场景运用：lpush、ltrim,维护一个元素数量指定的列表，并通过lrange得到前N个后者后N个元素，基于时间维度的列表；
                             带阻塞的消息队列

    集合set     ：无序的字符串合集，最大的特性是元素值不重复，同时还有求交集、并集、差集的指令。
                    指令：sadd、scard、sdiff、sinter、sunion、sismember
                    运用：1、集合元素去重，
                         2、tagging分类系统，用集合来表示单个tag，所有有改tag的对象的id用sadd加入集合中，遇到需要查找同时
                            有3个tag的某个对象时，使用sinter指令即可；
                         3、srangemember随机获取集合元素，spop随机删除元素。
    哈希hash      ： key-value , 用来表示map或者对象
                    指令：hset、hget、hdel、hincrby、hkeys、hvals、hlen

    有序集合zset ：有序的集合，每个成员都关联一个评分，根据评分将有序集合的成员按低分到高分排列。
                            快速的添加、删除、查找元素，时间复杂度（log(n)），元素添加时已经排好序。
                            获取一定范围内的元素的速度也很快。
                         用途：排名表
                         指令；zadd、zcard、zincrby、zrange、zrank、zrem、zscore

2、数据结构：

    简单动态字符串SDS  ：有长度、free空间属性。优点：1、常数维度的字符串长度获取；2、避免缓冲区溢出；
                        3、减少字符串修改带来的内存重分配次数；4、惰性释放空间

    链表linkedlist  ： 结构与双向linked链表类似，快速增删节点，顺序访问；有header、tail属性，访问头尾节点的时间复杂度是1；

    字典dict  ： 键值对，key和value的对应关系，redis数据库的底层实现。
                字典使用哈希表作为底层实现dictht,每个哈希表中保存着具体的key和value值，dictEnry；
                哈希冲突：使用链接地址法解决哈希冲突
                扩容/缩容：ht[0]存储着数据，ht[1]为空，扩容时
                    1）ht[1]大小为第一个大于后者等于ht[0].used*2的2的幂次方的数，收缩操作时，
                        则是第一个大于或者等于ht[0].used的2的幂次方的数；
                    2）将保存ht[0]中的key-value，rehash到]ht[1]，重新计算哈希索引
                    3）所有的值都从ht[0]迁移到ht[1]上后，将ht[0]释放，然后ht[1]设置为ht[0]，并在ht[1]上创建
                        一个空白哈希表，为下一次rehash做准备。
                    rehash操作不是一次性、集中式完成，而是分批次、渐进式完成，过程中同时使用ht[0]和ht[1]两个哈希表，
                    rehash过程中，添加操作都会保存在ht[1]上，而查找、更新、删除等会先在ht[0]上查找，没有再去ht[1]
                    查找

    跳跃表zskiplist ： 平均log(n)，最快n的复杂度查找节点，可以用来代替平衡树。

    整数集合inset   ： redis保存整数值的集合

    压缩列表ziplist ： 连续的内存块数据结构，长度、首尾节点位置。


3、数据结构与数据类型的对应关系

    string  ： 整数集合、SDS、embstr
    list    ： ziplist、linkedlist
    set     ： intset、dict
    hash    ： ziplist、dict
    ZSet    ： 1、ziplist，2、skiplist与dict的结合，一个方便快速添加、删除、查找元素，一个保持集合有序

4、redis持久化

    RDB：对整个redis数据库的备份，数据完整但速度慢
    AOF：多redis指令操作的记录，可增量备份。AOF还有重写功能，合并多条指令

5、集群

    数据分片：redis集群一共有16384个哈希槽，根据集群节点数量，每个节点负责一部分槽位。
    主从复制：每一个节点配置主节点，以及从节点，在主节点宕机后，可选举从节点作为新的主节点。
    一致性：redis集群不能保证数据的强一致性，性能和一致性之间的权衡，只能保证最终一致性。
            主节点收到写指定并回复客户端后才会将写操作复制给从节点。

6、事务

    MULTI指令开启一个事务
    EXEC指令出发并执行事务中的所有指令
    DISCARD指令清空事务队列，并放弃执行事务

    因为断线没有exec指令，事务中所有的指令不执行；
    EXEC指令执行之前发生的错误，会导致事务取消
    EXEC指令执行之后，即使一条指令发生的错误，不会影响其它指令的执行，所以 REDIS事务不支持回滚，


7、Redis哨兵（Sentinel）和集群分区（Cluster）

    作用： 1）监控主服务器和从服务器是否运行正常；
          2）被监控redis服务器出现故障时的发出提醒通知；
          3）自动故障迁移，当一个主服务器不能正常工作时，sentinel会从失效主服务器的从服务器中选择升级为主服务器，并将失效主
            服务器的其它从服务器改为复制新的主服务器。

    流言协议：通过流言协议接收主服务器的客观下线通知；
    投票协议：sentinel标记一个主服务器为客观下线后，会将当前纪元自增，然后向其它sentinel发送信息，尝试当选；
             其它sentinel如果还没发起投票，接收到投票消息后，会标记第一个接收的sentinel为当选人；
             同一个纪元内，sentinel只能把票投给一个sentinel，如果有更新的纪元信息，会覆盖当前的；
             同一个纪元内，得票数达到指定的数量（配置比例）的sentinel才能当选成功，进行自动故障迁移；
             一个纪元内没有选取出，则一定时间后，纪元自增，进行下一轮选举。
    应对网络分区造成的分裂，可以设置数量比例，如0.5等，避免投票协议选出2个以上的当选人。
    同样的道理，没有sentinel时，从服务器选举新主服务器类似。

    主观下线：sentinel会以每10秒一次的频率向所有的主从redis服务器发送info，如果在指定时间内没有返回有效回复，判定为主观下线；
    客观下线：一个主服务器被标记为主观下线后，如果有足够数量的sentinel在指定时间范围内同意这一判断，就会被标记为客观下线。
    客观下线仅仅针对主服务器，从服务器没有被判定客观下线的场景。
    自动故障迁移：1）一个主服务器被标记为客观下线后，通过投票协议选举出一个sentinel负责自动故障迁移；
                2）选出一个从服务器作为新主服务器（是否主观下线，是否断开超过一定时间，然后选出复制偏移量最大的）；
                3）通过发布订阅功能，领头sentinel将更新后的配置传播给其它sentinel，进行更新；
                4）给已下线的其它从服务器发送slave of命令，让他们复制新主服务器；
                5）所有从服务器都已经开始复制新主服务器时，领头sentinel终止这次故障迁移操作。

    sentinel之间可以建立联系并互相检查对方的可用性，
    sentinel通过订阅和发布功能可以发现对同一主服务器监控的其它sentinel实例，通过订阅主服务器的sentinel：hello频道，并发送
    消息实现。
    sentinel也可以自动发现从服务器，通过询问主服务器来得到所有从服务器信息。

    其它资料：
    https://blog.csdn.net/donggang1992/article/details/50981341
