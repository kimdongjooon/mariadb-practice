-- 
-- 문자형 함수
--

-- UCASE, UPPER
SELECT UPPER('SEoul'), UCASE('seOUL'); 

-- LCASE, LOWER
SELECT LOWER('SEoul'), LCASE('seOUL'); 

-- substring(문자열, index, length)
SELECT SUBSTRING('Happy Day',3,2);
-- 예제: employees 테이블에서 1989년에 입사한 직원의 이름, 입사일을 출력
select *
from employees
where substring (hire_date,1,4) = '1989';

-- lpad, rpad : 정렬함수
select lpad('1234',10,'-');
select rpad('1234',10,'-');
-- 예제 : 직원들의 월급을 오른쪽 정렬
select lpad(salary,10,'.');

-- trim, Ltrim,rtrim
select 
concat('---', ltrim('    hello.  '), '---'),
concat('---', rtrim('    hello.  '), '---'),
concat('---', trim('    hello.  '), '---'),
concat('---', trim(leading 'x' from 'xxxhelloxxx'), '---'),
concat('---', trim(trailing 'x' from 'xxxhelloxxx'), '---'),
concat('---', trim(both 'x' from 'xxxhelloxxx'), '---')
from dual;   

-- length
select length('Hello World') from dual;

