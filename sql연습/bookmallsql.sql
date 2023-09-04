use bookmall;

--
-- CATEGORY
--
SELECT * FROM category;
ALTER TABLE category AUTO_INCREMENT = 1;
DELETE FROM category WHERE no <=15;

insert into category values(null,'소설');
insert into category values(null,'수필');
insert into category values(null,'컴퓨터/IT');
insert into category values(null,'인문');
insert into category values(null,'경제');
insert into category values(null,'예술');
SELECT * FROM category;


--
-- BOOK
--
SELECT * FROM book;

ALTER TABLE book AUTO_INCREMENT = 1;
DELETE FROM book WHERE no <=10;

insert into book values(null,'성냥팔이 소녀',9900,1);
insert into book values(null,'수필은 이렇게 쓴다',13500,2);
insert into book values(null,'코딩 자율학습 스프링 부트 3 자바 백엔드 개발 입문',29700,3);
insert into book values(null,'어반 정글',21600,4);
insert into book values(null,'마흔에 읽는 쇼펜하우어',15300,4);
insert into book values(null,'더 플로',18900,5);
insert into book values(null,'위기의 역사',9900,5);
insert into book values(null,'파수꾼',25200,6);
insert into book values(null,'장화,홍련',18000,6);
insert into book values(null,'아주 희미한 빛으로도',15120,1);
insert into book values(null,'리그 오브 레전드 플레이어 중심주의',18000,3);
insert into book values(null,'스필버그의 말',22500,2);


--
-- MEMBER
--
ALTER TABLE member AUTO_INCREMENT = 1;
DELETE FROM member WHERE no <=10;

select * from member;
insert into member value(null,'홍길동','홍길동11@naver.com','1234','010-1111-2222');
insert into member value(null,'성춘향','성춘향22@gmail.com','4323','010-5475-4444');
select * from member;
-- 