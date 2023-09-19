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
and a.title like '%%'
order by g_no DESC, o_no ASC
-- limit 0,5
;

-- boardFindByNo
select 
	title,
    contents,
    hit,
    reg_date 
from board 
where no = 2;

-- TitleContentUpdate
update board
set 
	title = '제목바꾸기',
    contents = '내용바꾸기'
where no = 3;
select * from board where no =3 ;

-- maxO_NoByG_NoAndUserNo
select * from board;
select max(o_no)
from board
where g_no = 2
and user_no = 3;

-- updateO_NoByG_NoAndO_No 
select * 
from board
where g_no=2
and o_no >= 2;

;
set sql_safe_updates=0;
update board
set o_no = o_no+1
where g_no = 6
and o_no >=3
;
set sql_safe_updates=1;

-- deleteBoardByNo 
delete from board
where no =21;

-- updateHitByNo
update board
set hit = hit+1
where no =18;

-- 총 페이지 입력받기
select count(*) from board where title like '%%';