use webdb;
set sql_safe_updates=0;



select version(), current_date, now() from dual;
select sin(pi()/4), 1+5 from dual;
select version();

create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
    );

    
-- schema 확인
show tables;
describe pet;
desc pet;

-- table 삭제 : DDL
drop table pet;

show tables;

-- insert : DML
insert into pet values(
	'둘리','세지','dragon','m','1990-10-10',null);
    
select * from pet;

-- update : DML
update pet set name = '또치' where name = '둘리';

select * from pet;

-- delete: DML
delete from pet where name = '성타니';

-- load data

load data local infile '/Users/kdj/Desktop/poscodx-academy/pet.txt' into table pet;
update pet set death = null where name != 'Bowser';

-- select 연습
-- 문제1 ) 이름이 Bowser 의 주인은?
select owner from pet where name='Bowser';

-- 문제2 ) 1998이후에 태어난 애들은?
select name,birth from pet where birth > '1998-01-01';

-- 문제3 ) 종이 뱀이나 새인 애들은?
select * from pet where species = 'snake' or species = 'bird';

-- 예제4 ) order by 오름, 내림 차순.
select name, birth from pet order by birth asc;
select name, birth from pet order by birth desc;

-- 예제 6) where절에 null다루기
select name,birth, death from pet where death =0 ;
select name,birth, death from pet where death is null ;
select name,birth, death from pet where death is not null ;

-- 예제 7) like 검색 (패턴 검색)
select name from pet where name like 'b%'; 
select name from pet where name like '%fy'; 
select name from pet where name like '%w%';
select name from pet where name like '____';
select name from pet where name like 'b____';

-- 예제 8) 집계 함수: count, avg, sum, max, min,...
select * from pet;
select count(*) from pet;
select max(bitrh) from pet;