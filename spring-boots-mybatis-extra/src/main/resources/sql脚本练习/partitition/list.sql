
create table l(
a int,
b int
)engine=innodb
partition by list(b)(
partition p0 values in (1,3,5,7,9),
partition p1 values in (0,2,4,6,8)
);

insert into l select 1,1;
insert into l select 1,2;
insert into l select 1,3;
insert into l select 1,4;
insert into l select 1,5;
insert into l select 1,6;


select table_name,partition_name,table_rows
from information_schema.partitions
where table_name = 'l' and table_schema=database();