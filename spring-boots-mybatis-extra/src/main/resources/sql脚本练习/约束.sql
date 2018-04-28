create table usercash(
userid int not null,
cash int unsigned not null
);

insert into usercash select 1,1000;

update usercash set cash = cash - (-20) where userid = 1;


create table usercash_err_log (
userid int not null,
old_cash int unsigned not null,
new_cash int unsigned not null,
user varchar(30),
time datetime
);


delimiter $$

create trigger tgr_usercash_update before update
on usercash
for each row
begin
if new.cash-old.cash > 0 then
insert into usercash_err_log
select old.userid,old.cash,new.cash,user(),now();
set new.cash = old.cash;
end if;
end;
$$




delete from usercash;

insert into usercash
select 1,1000;

update usercash 
set cash = cash - (-20)
where userid =1;


drop trigger tgr_usercash_update;












