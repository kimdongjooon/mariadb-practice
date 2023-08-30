--
-- 날짜 함수
-- 

-- curdate(), current_date
select curdate(), current_date
from dual;

-- now () vs sysdate() : now는 실행시 시간, sysdate는 실행중 시간.
select now(), sysdate()
from dual;

select now(), sleep(2), now()
from dual;

select sysdate(), sleep(2), sysdate()
from dual;

-- DATE_FORMAT(date,format) : 입력된 date를 format 형식으로 반환한다. 
SELECT DATE_FORMAT(CURDATE(),'%W %M %Y'); 
SELECT DATE_FORMAT(now(),'%Y년 %m월 %d일 %h시 %m분 %s초'); 

-- PERIOD_DIFF(p1,p2) : YYMM이나 YYYYMM으로 표기되는 p1과 p2의 차이 개월을 반환 한다.
-- 예제 : 각 직원들에 대해 직원이름과 근무 개월수(2023년 기준)
select first_name, hire_date, 
	period_diff(date_format(curdate(), '%y%m'),date_format(hire_date,'%y%m')) as 근무개월
from employees;

-- DATE_ADD(date,INTERVAL expr type) 
-- DATE_SUB(date,INTERVAL expr type) 
-- ADDDATE(date,INTERVAL expr type) 
-- SUBDATE(date,INTERVAL expr type) 
-- 날짜 date에 type 형식으로 지정한 expr값을 더하거나 뺀다. expr 타입 : year month day
-- DATE_ADD()와 ADDDATE()는 같은 동작이고, DATE_SUB()와 SUBDATE()는 같은 의미이다.   

-- 예) 각 사원의 근속 년수가 5년이 되는 날에 휴가를 보내준다면 각 사원들의 근속 휴가 날짜는?
select first_name, hire_date,
	date_add(hire_date,interval 5 year)
from employees;

-- cast
select
	'12345' + 10,
    cast('12345' as int) + 10
from dual;
select
	date_format('2023-08-30' ,'%Y년 %m월 %d일'),
    date_format(cast('2023-08-30' as date),'%Y년 %m월 %d일')
from dual;
select
	cast(1-2 as unsigned),
    cast(cast(1-2 as unsigned) as signed),
    cast(cast(1-2 as unsigned) as int),
    cast(cast(1-2 as unsigned) as integer)
from dual;

-- type
-- 문자 : varchar, char, text, CLOB
-- 정수 : tiny, medium, signed(int, integer), unsigned, big int
-- 실수 : float, double
-- 시간 : date, datetime
-- LOB : CLOB(Character Large Object), BLOB(Binary Large OBject)





