--
-- dml
--

-- insert
-- 1. 
insert into member
	values (null, 'asd@gmail.com', password('1234'),'김동준','개발팀',null);

select * from member;

-- 2.
insert into member(no,email,password,name,dept,self_intro)
	values (null, 'asd123@gmail.com', password('1234'),'김동준1','개발팀1',null);
select * from member;

-- 3.
insert into member(email,password,name,dept)
	values ('asd123333@gmail.com', password('1234'),'김동준21','개발팀21');
select * from member;

-- update
update member 
	set email='djk@gmail.com', name='KDJ'
where no = 2;
select * from member;

-- delete
delete from member
where no = 3;
select * from member;

 -- transaction begin
set autocommit = 0;
select @@autocommit from dual;

insert
  into member(email, name, dept, password)
values ('kickscar4@gmail.com', '안대혁4', '개발팀4', password('1234'));

select no, email, dept from member;

commit;

select no, email, dept from member;
 