SELECT * FROM webdb.guestbook;

-- insert
insert into guestbook values (null,'vo.name', 'vo.password','vo.contents','1999-12-31 12:01:01');
insert into guestbook values (null,'vo.name', '1234','vo.contents','1999-12-31 12:01:01');
select * from guestbook;

-- delete
ALTER TABLE guestbook AUTO_INCREMENT = 0;

delete from guestbook 
where no = 4
and password = '1234';
select * from guestbook;