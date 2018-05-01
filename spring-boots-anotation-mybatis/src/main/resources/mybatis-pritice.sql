drop table if exists city;
create table city (
id int(10) unsigned not null auto_increment comment '城市编号',
province_id int(10) unsigned not null comment '省份编号',
city_name varchar(25) default null comment '城市名称',
description varchar(100) default null comment '描述',
primary key (id)
)engine=innodb auto_increment=1  charset = utf8;

select id , province_id as provinceId, city_name as cityName, description from city where id = #{id}


--插入数据
{"cityName":"荆州","description":"故乡","id":3,"provinceId":3}

{"cityName":"北京","description":"帝国首都","id":1,"provinceId":1}

{"cityName":"成都","description":"天府之国","id":2,"provinceId":2}

{"cityName":null,"description":"故乡","id":5,"provinceId":5}

{"cityName":"武汉","description":"武汉三镇","id":4,"provinceId":3}

{"cityName":"深圳","description":"","id":5}
{"cityName":"北京","id":1}
{"cityName":"成都","id":2}
{"cityName":"荆州","id":3}

update city set city_name = '北京' where id = 1
{"cityName":"成都","description":"","id":2}
--文章表
drop table if exists article;
create table article(
id int(10) unsigned primary key comment '文章编号',
title varchar(200) not null comment '文章标题',
content text default null comment '文章内容',
create_date date default null comment '发表时间',
create_time timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
author_id int(10)  unsigned comment '作者id编号',
constraint fk_article_author foreign key(author_id) references author(id)
)engine=innodb auto_increment=1 charset = utf8;

--插入数据
{"id":1,"title":"蜀中行","content":"蜀道之难难于上青天","authorId":1}
{"id":2,"title":"将进酒","content":"人生得意须尽欢","authorId":1}
{"id":3,"title":"无题","content":"","authorId":1}

alter table article add column author_id int(10)  unsigned  comment '作者id编号'；
alter table article add constraint fk_article_author foreign key(author_id) references author(id);

--作者表
drop table  if EXISTS author;
create table author(
id int(10) unsigned primary key comment '作者编号',
name varchar(20) not null comment '姓名',
age int(3) unsigned comment '年龄',
home_city_id int(10) UNSIGNED not null comment '居住城市编号',
constraint fk_author_city foreign key(home_city_id) references city(id)
)engine=innodb auto_increment=1 charset=utf8;

-- 插入数据
{"id":1,"name":"李白","age":20,"homeCityId":1}
{"name":"杜甫","age":20,"homeCityId":2}
{"id":3,"name":"陆游","age":20,"homeCityId":3}


select
a.id as id,
a.name as name,
a.age as age,
b.id as article_id,
b.title as article_title,
b.content as article_content,
b.create_date as article_create_date,
c.city_name as city_name,
c.id as city_id,
c.description as city_description
from author a , article b ,city c where
a.home_city_id = c.id and a.id = b.author_id