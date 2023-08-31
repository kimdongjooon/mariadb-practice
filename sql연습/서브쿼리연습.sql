--
-- subquery
-- 
set sql_safe_updates=0;
update salaries set to_date = '9999-01-01' where to_date = '2023-08-30';
set sql_safe_updates=1;

-- 1) select 절의 컬럼 프로젝션,
--   insert into t values()


-- 2) select의 from절*

select a.n, a.s , a.r
from (select now() as n, sysdate() as s, 3+1 as r from dual) a;

--
-- 3) select의 where절(having절) 서브쿼리
--
-- 예제: 현재, Fai Bale 이 근무하는 부서에서 근무하는 다른 직워느이 사번 ,이름 출력.
select *
from employees a, dept_emp b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and concat (first_name,' ',last_name) = 'Fai Bale';

-- 단일행 연산자 : =, >, <, >=, <> !=
-- 3-1) 
-- 예제 : 현재, 전체 사원의 평균연봉보다 적은 급여를 받고있는 사원의 이름과 급여 출력.
select avg(salary) 
from salaries
where to_date='9999-01-01';

select *
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and b.salary < (select avg(salary) 
				from salaries
				where to_date='9999-01-01')
order by b.salary desc;

-- 예제 2: 현재, 직책별 가정 적은 평균 급여의 직책과 급여를 출력

select min(평균급여) as 가장적은평균급여
from(
select a.title, avg(b.salary) as 평균급여
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
) a;

select a.title, avg(b.salary) as 평균급여
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
having 평균급여 = (
select min(평균급여) as 가장적은평균급여
from(
select c.title, avg(d.salary) as 평균급여
from titles c, salaries d
where c.emp_no = d.emp_no
and c.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
group by c.title
) c
);

-- 3-2) 복수행 연산자 : in, not in, 비교연산자 any, 비교연산자all

-- any 사용법.
-- 1. =any : in
-- 2. >any, >=any : 최소값
-- 3. <ant, <=any : 최대값
-- 4. !any, <>any : not in

-- all 사용법.
-- 1. =all: (x)
-- 2. >all, >=all : 최대값
-- 3. <all, <=all : 최소값
-- 4. !=all, <>all :

-- 예제3 : 현재, 급여가 50000이상인 직원의 이름과 급여를 출력하세요.(급여가 큰순)

select a.emp_no,b.salary
from employees a, salaries b
where a.emp_no = b.emp_no 
and salary >= 50000
and to_date ='9999-01-01'
;

select * 
from employees a, salaries b
where a.emp_no = b.emp_no;

-- 문제 4 : 현재 각 부서별로 최고 급여를 받고 있는/ 작원의 이름과 연봉을 출력하세요.
select a.dept_no,max(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.dept_no;
-- sol1) where 절 서브쿼리 in, 복수행/ 다중컬럼.
select a.first_name, b.emp_no, c.dept_name, d.salary
from employees a, dept_emp b, departments c, salaries d
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and b.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and (b.dept_no,d.salary) in (
    select a.dept_no, max(b.salary) as salary
	from dept_emp a, salaries b
	where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
	group by a.dept_no
)
-- and a.emp_no = 10017
-- and d.salary = 99651

;

select a.dept_no,b.emp_no, max(salary)
from dept_emp a, employees b, salaries c
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and a.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
group by a.dept_no;


-- sol2) from 절 서브쿼리, join