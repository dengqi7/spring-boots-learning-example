create database springboots

drop table if exists city;
create table city (
id int(10) unsigned not null auto_increment comment '城市编号',
province_id int(10) unsigned not null comment '省份编号',
city_name varchar(25) default null comment '城市名称',
description varchar(100) default null comment '描述',
primary key (id)
)engine=innodb auto_increment=1  charset = utf8;

--插入数据
{"cityName":"荆州","description":"故乡","id":3,"provinceId":3}

{"cityName":"北京","description":"帝国首都","id":1,"provinceId":1}

{"cityName":"成都","description":"天府之国","id":2,"provinceId":2}

{"cityName":null,"description":"故乡","id":5,"provinceId":5}

{"cityName":"深圳","description":"","id":5}

create database springboots_slaver;
drop table if exists user;
create table user (
id int(10) unsigned  auto_increment primary key comment '用户编号',
user_name varchar(25) comment '用户名称',
age int(3) unsigned comment '用户年龄',
home_city_id int(10) unsigned comment '居住城市'
)engine=innodb auto_increment=1 charset=utf8;

insert into  user (user_name,age,home_city_id) values('泥瓦匠',20,1);