create table t(
a int not null,
b varchar(8000),
c int not null,
primary key (a),
key idx_c (c)
)engine=innodb;

alter table t add key idx_a_c (a,c);


insert into t select 1,'1',-1;
insert into t select 2,'2',-2;
insert into t select 3,'3',-3;
insert into t select 4,'4',-4;
insert into t select 5,'5',-5;
insert into t select 6,'6',-6;
insert into t select 7,'7',-7;

--查询主键-聚集索引，具体值:primary
explain
select * from t where a=3;

--查询主键-聚集索引-范围搜索:primary
explain
select * from t where a>4 and a< 7;

--辅助索引:idx_c
explain
select c from t where c=-5;

--辅助索引:使用的是idx_c
explain
select a,c from t where c=-5;

--辅助索引:primary
explain
select a,c from t where c=-5 and a = 5;

--辅助索引，使用的是idx_c
explain
select a,b,c from t where c=-5;

--辅助索引, 使用的是primary
explain
select a,b,c from t where c=-5 and a = 5;

--辅助索引-范围查找：全表
explain
select b,c from t where c>-4;

--辅助索引-范围查找：索引idx_c
explain
select c from t where c>-4;

--辅助索引-范围查找:索引idx_c，相当于特殊的索引覆盖（含主键）
explain
select a,c from t where c>-4 

--辅助索引-范围查找order:索引idx_c，相当于特殊的索引覆盖（含主键）
explain
select a,c from t where c>-4 order by a /order by c;

--辅助索引-范围查找in:索引idx_c，相当于特殊的索引覆盖（含主键）
explain
select a,c from t where c in (-1,-2,-3);

--辅助索引-范围查找or:索引idx_c，相当于特殊的索引覆盖（含主键）
explain
select a,c from t where c >-2 or c < -4 ;


--技术操作，使用聚集索引还是辅助索引呢？辅助索引更好，用的idx_c
explain
select count(*) from t;

--强制索引
explain 
select a,b,c from t force index (idx_c) where c > -5;




