阶段学习总结
    github账号申请使用，springboots环境搭建，mybatis集成springboots配置、基本使用、动态sql语法，restfull风格注解使用，
springboots日志、springboots热部署,springboots定时任务,通用mapper，mybatis-generator的配置使用，通用mapper自动代码生成的。

    一个脚步一个脚印，将一个springboots的基础环境搭成！

    这个项目会使用上面学到的知识，进行整体的运用


利用通用mapper组件完成数据层操作，发现的槽点：
1、mapper3.x中，mapper-..-starter会使mybatis的stater配置失效，所以MapperScan必须指定tk的，不能用mybatis原生的。
2、MapperScan不使用，直接加Mapper注解也可行。
3、使用Mapper以外的扩展街扩，必须配置mappers参数，否则无法使用。

测试过了，还是因为mappers参数问题导致MysqlMapper无法使用。
如果不能解决，还是不要用这个了。