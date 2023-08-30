use employees;
-- 산술비교 연산자
-- 예제 : employees 테이블에서 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력
  SELECT concat( first_name, ' ', last_name ) AS 이름,gender AS 성별, hire_date AS 입사일
    FROM employees
   WHERE hire_date < '1991-01-01' 
order by hire_date;



-- 예제2 : 비교연산자: employees 테이블에서 1989년 이전에 입사한 여직원의 이름, 입사일을 출력
  SELECT concat( first_name, ' ', last_name ) AS 이름,hire_date AS 입사일
    FROM employees
   WHERE gender='f'
     AND hire_date < '1989-01-01'
order by hire_date desc;

-- IN
-- 예제3 : dept_emp 테이블에서 부서 번호가 d005나 d009에 속한 사원의
--       사번, 부서번호 출력
 SELECT emp_no, dept_no    
   FROM dept_emp
WHERE dept_no in( 'd005', 'd009' );

-- like
-- 예제4 : employee 테이블에서 1989년에 입사한 직원의 이름, 입사일 출력
select first_name, hire_date
from employees
where '1989-01-01' <= hire_date
and '1989-12-31' <= hire_date;

select *
from employees
where hire_date between '1989-01-01' and '1989-12-31';

select *
from employees
where hire_date like '1989%';
 
 -- 예제5 : salaries 테이블에서 2001년 월급을 가장 높은순으로 사번, 월급 출력.
select emp_no, salary, from_date, to_date
from salaries
where from_date like '2001%'
or to_date like '2001%'
order by salary desc;

-- 예제 6 : 남자 직원의 이름, 성별, 입사일을 선임순으로 출력.
select *
from employees
where gender = 'M'
order by hire_date asc;

-- 예제 7 : 직원들의 사번, 월급을 사번 순으로 출력.
select *
from employees
order by emp_no;

