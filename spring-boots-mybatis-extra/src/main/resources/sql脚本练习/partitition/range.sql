CREATE TABLE `sales` (
  `money` int(10) unsigned NOT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
PARTITION BY RANGE (YEAR(date))(
PARTITION p2008 VALUES LESS THAN (2009),
PARTITION p2009 VALUES LESS THAN (2010),
PARTITION p2010 VALUES LESS THAN (2011)
);


insert into sales select 100 , '2008-01-01';
insert into sales select 100 , '2008-02-01';
insert into sales select 100 , '2008-03-01';
insert into sales select 100 , '2009-03-01';
insert into sales select 100 , '2010-05-01';
insert into sales select 100 , '2012-01-01';


explain partitions
select * from sales where date>='2008-01-01' and
date <= '2008-12-31';

explain partitions
select * from sales where date>='2008-01-01' and
date < '2009-01-01';