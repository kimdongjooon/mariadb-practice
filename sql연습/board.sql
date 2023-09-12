use webdb;
SELECT * FROM board;


-- insert
insert 
into board 
values (null, '제목2','내용2',0,'2023-09-12 15:06:20', 2,1,2,3)
;

-- g_no 가져오기
select max(g_no) from board where user_no= 3;

-- findAll() 게시글 모두 가져오기 
select * from board;
select a.no, 
	   a.title,
       a.contents,
       a.hit, a.reg_date,
       a.g_no,
       a.o_no,
       a.depth,
       a.user_no,
       b.name 
from board a, user b
where a.user_no = b.no
order by g_no DESC, o_no ASC
;