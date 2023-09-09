-- user
delete from user;
select * from user;

-- join
insert 
into user 
values (null,'둘리','dooly@gmail.com',password('1234'),'male', current_date());

-- login
select * 
from user
where email = 'djkim@aicloud.biz'
and password=password('1234');

update user 
set name = '김김김', gender='female',password=password('12345') where no = 4;
select * from user;

-- select
select gender
from user
where no = 3;
