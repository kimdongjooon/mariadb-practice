use bookmall;
ALTER TABLE book AUTO_INCREMENT = 1;
ALTER TABLE cart AUTO_INCREMENT = 1;
ALTER TABLE category AUTO_INCREMENT = 1;
ALTER TABLE member AUTO_INCREMENT = 1;
ALTER TABLE orders AUTO_INCREMENT = 1;
ALTER TABLE order_book AUTO_INCREMENT = 1;
SELECT * FROM category;
SELECT * FROM book;
SELECT * FROM cart;
SELECT * FROM member;
SELECT * FROM orders;
SELECT * FROM order_book;

--
-- CATEGORY
--
SELECT * FROM category;
ALTER TABLE category AUTO_INCREMENT = 1;
DELETE FROM category WHERE no <=30;

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

ALTER TABLE book AUTO_INCREMENT = 12;
DELETE FROM book WHERE no =13;

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

select a.no, a.title, a.price, b.category
from book a, category b
where a.category_no = b.no
and b.category like ('%')
order by a.no;


--
-- MEMBER
--
ALTER TABLE member AUTO_INCREMENT = 0;
DELETE FROM member WHERE no <=10;

select * from member;
insert into member value(null,'홍길동','홍길동11@naver.com','1234','010-1111-2222');
insert into member value(null,'성춘향','성춘향22@gmail.com','4323','010-5475-4444');
select * from member;
-- 


-- 
-- cart
-- 
ALTER TABLE cart AUTO_INCREMENT = 0;
DELETE FROM cart WHERE no <=10;
select * from cart;
-- insert into cart values(null,vo.quntity,vo.no,vo.member_no)
insert into cart values(null,3,1,1);
-- findall : 책 고유번호, 종류, 책이름, 가격, 수량, 총가격.
select b.no, c.category, a.title, a.price, b.quntity, (a.price*b.quntity)
from book a, cart b, category c
where a.no = b.book_no
and a.category_no = c.no
;
-- select 
select no
from member
where email = 'hong@google.com';


-- 
-- orders
-- 
ALTER TABLE orders AUTO_INCREMENT = 0;
DELETE FROM orders WHERE no <=10;

select * from orders;
insert into orders values(null);

-- member 테이블에서 name, email 가져와서 변수 저장.
select name, email
from member
where no = 0;

;

-- cart 테이블에서 member_no에 해당하는 행마다의 quntity와 price 가져와서 변수 저장.
select b.quntity, c.price
from orders a, cart b, book c
where a.member_no = b.member_no
and b.book_no = c.no;

select a.quntity, b.price
from cart a, book b
where a.book_no = b.no
and a.member_no = 1;

select no,name,total_price,email,address 
from orders;


-- 최종 findall sql문 
select a.no, b.name, a.total_price, b.email, a.address, c.book_no
from orders a, member b, cart c, book d
where a.member_no = b.no
and b.no = c.member_no
and c.book_no = d.no

;

select * from orders;

--
-- order book
-- 
-- insert 용 데이터 select 
-- order_no, book_no, quntity, price를 가져오기
select * from order_book;
select a.no, d.no, c.quntity, d.price
from orders a, member b, cart c, book d
where a.member_no = b.no
and b.no = c.member_no
and c.book_no = d.no
and a.member_no = 1
;

-- orderbookfindall
-- (주문 번호, 책고유 번호(책이름), 가격 , 수량, 총가격)
select * 
from orders a, cart b, book c
where a.member_no = b.member_no
and b.book_no = c.no
;

-- 주문 번호, 주문자 이름, 책 제목, 개당가격, 수량, 각합.
select b.no, b.name, c.title, a.price, a.quntity,  (a.price*a.quntity) as "총 가격"
from order_book a, orders b, book c
where a.order_no = b.no
and a.book_no = c.no
and b.member_no = 1;
;