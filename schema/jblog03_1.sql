use jblog;


-- user 데이터베이스.
desc user;

insert into user values ('admin','관리자',password('1234'),now(),'ADMIN');

update user set password = password('1234') where name='관리자';

DELETE FROM user WHERE (id = 'admin@jblog.com');

select * from user;

-- blog 데이터베이스
select * from blog;
insert into blog values ('Spring 이야기','test','admin');
insert into blog values ('Spring 이야기','test','ehdwns6745');
insert into blog values ('Spring 이야기','test','michol');

SELECT blog_id,title,image FROM blog where blog_id = 'admin';

-- category 데이터베이스
select * from category;
SELECT no,name,description,blog_id FROM category where blog_id = 'admin';

insert into category values (null,'카테고리명3','카테고리를 설명3','admin');
delete from category where no = 4;

-- post 데이터 베이스
select * from post;
insert into post values (null,'포스트제목','포스트내용',2);
insert into post values (null,'포스트제목3','포스트내용3',3);
insert into post values (null,'포스트제목3-1','포스트내용3-1',3);
delete from post where no = 1;

SELECT 
	p.no,
    p.title,
    p.contents,
    p.category_no,
    c.blog_id
FROM category c, post p
where c.no = p.category_no
AND blog_id = 'admin'
AND category_no like '%'
;

SELECT 
	p.no,
	p.title,
	p.contents,
	p.category_no,
	c.blog_id
FROM category c, post p
where c.no = p.category_no
AND c.blog_id = 'admin';

-- 
SELECT 
	count(*) as count,
    c.no,
    c.name,
    c.description,
    c.blog_id
FROM category c left join post p on c.no = p.category_no
AND c.blog_id = 'admin'
group by c.no;