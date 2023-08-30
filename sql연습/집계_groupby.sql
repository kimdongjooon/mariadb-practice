select * from employees;
-- 
-- group  
-- 

-- 1) 집계쿼리 : selcet절에 통계함수(avg, max, min, count, sum, stddev, ...)
select
	avg(salary),
    sum(salary)
from salaries;

-- 2) select에 그룹 함수(통계함수)가 있는 경우, 어떤 컬럼도 select절에 올 수 없다.
-- 여기서 emp_no는 아무런 의미가 없다. 즉 오류를 내야하지만 mysql에서는 그냥 1행을 출력한다.
select emp_no, avg(salary)
from salaries;

-- 3) 쿼리 순서
-- 1. from: 테이블에 접근
-- 2. where: 조건에 맞는 row 선택.
-- 3. projection: 집계(임시 테이블, 메모리 캐시)
-- 4. 결과를 반환: 출력.

-- 예제1) 사번이 10060인 사원이 받은 평균 연봉은?
select avg(salary)
from salaries
where emp_no = 10060;

-- 예제2) groupby, Having
-- 집계 결과(결과 테이블)에서 row를 선택해야하는 경우.

select emp_no, avg(salary) as avg_salary
from salaries
where emp_no > 12000
group by emp_no
having avg(salary) > 80000
order by avg_salary desc
limit 100; 


-- 주의)
-- 예제: 사번이 10060인 사원의 사번, 평균 급여, 급여 총합을 출력
-- 문법적으로 오류이고 의미적으로는 맞음.
select emp_no,avg(salary), sum(salary)
from salaries
group by emp_no
having emp_no=10060;