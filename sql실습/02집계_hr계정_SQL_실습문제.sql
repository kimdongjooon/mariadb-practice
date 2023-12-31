-- 집계(통계) SQL 문제입니다.

-- 문제 1.  
-- 최고임금(salary)과  최저임금을 “최고임금, “최저임금”프로젝션 타이틀로 함께 출력해 보세요. 
-- 두 임금의 차이는 얼마인가요? 함께 “최고임금 – 최저임금”이란 타이틀로 출력해 보세요.
select 
	max(salary) as 최고임금, 
	min(salary) as 최저임금,
    max(salary) - min(salary) as '최고임금 - 최저임금'
from salaries;

-- 문제2.
-- 마지막으로 신입사원이 들어온 날은 언제 입니까? 다음 형식으로 출력해주세요.
-- 예) 2014년 07월 10일
select * from employees;
select date_format(min(hire_date),'%Y년 %m월 %d일')
from employees;

select * from salaries ;

-- 문제3.
-- 가장 오래 근속한 직원의 입사일은 언제인가요? 다음 형식으로 출력해주세요.
-- 예) 2014년 07월 10일
-- 오늘날짜로 업데이트
set sql_safe_updates=0;
update salaries set to_date = curdate() where to_date = '9999-01-01';
set sql_safe_updates=1;

-- 근속기간 계산.
select emp_no,
sum(period_diff(date_format(to_date,'%y%m'),date_format(from_date,'%y%m'))) as 근속기간,
date_format(to_date,'%Y년 %m월 %d일') as '오래 근속한 직원의 입사일'
from salaries 
group by emp_no 
order by 근속기간 desc, to_date asc
limit 1;

update salaries set from_date = curdate() where from_date = '9999-01-01';


-- 문제4.
-- 현재 이 회사의 평균 연봉은 얼마입니까?
select * from salaries;
select avg(salary) from salaries;

-- 문제5.
-- 현재 이 회사의 최고/최저 연봉은 얼마입니까?
select max(salary) as '최고 연봉', min(salary) as '최저 연봉'
from salaries;

-- 문제6.
-- 최고 어린 사원의 나이와 최 연장자의 나이는?
select * from employees;
select curdate();
select
-- date_format(curdate(),'%Y%m'), date_format(birth_date,'%Y%m'),
-- emp_no, 
-- ceil(period_diff(date_format(curdate(),'%Y%m'),date_format(birth_date,'%Y%m')) / 12) as 직원나이
min(ceil(period_diff(date_format(curdate(),'%Y%m'),date_format(birth_date,'%Y%m')) / 12)) as '직원 최연소 나이',
max(ceil(period_diff(date_format(curdate(),'%Y%m'),date_format(birth_date,'%Y%m')) / 12)) as '직원 최연장자 나이'
from employees
-- order by ceil(period_diff(date_format(curdate(),'%Y%m'),date_format(birth_date,'%Y%m')) / 12) asc;
-- order by 직원나이;








