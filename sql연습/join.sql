use employees;
-- inner join

-- ex1 ) 현재, 근무하고 있는 사원의 사번 과 이름과 직책 출력.
select a.emp_no, a.first_name, b.title
from employees a,titles b
where a.emp_no = b.emp_no  -- join 조건 (n-1)
and b.to_date ='9999-01-01'; -- row 선택 조건

-- join ~ on *
-- ex) 현재, 직책별 평균 연봉을 큰순서대로 출력
select a.title, max(b.salary),avg(b.salary)
from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date ='9999-01-01'
and b.to_date ='2023-08-30'
group by a.title
order by avg(b.salary) desc;

-- natural join
-- 조인 대상이 되는 테이블들에 이름이 같은 공통 컬럼이 있는 경우
-- 조인 조건을 명시하지 않고 암묵적으로 조인이됨.
-- 예제 : 현재 근무하고 있는 직원의 이름과 직책 출력.
select *
from employees a, titles b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
order by a.first_name asc;

select a.first_name, b.title
from employees a natural join titles b
where b.to_date = '9999-01-01'
order by a.first_name asc;

-- join - using
-- natural join문제점
-- 예제 : 현재 근무하고 있는 직원의 이름과 직책과 연봉출력
select a.emp_no, a.title, b.salary
from titles a join salaries b using(emp_no)
where a.to_date ='9999-01-01'
and b.to_date ='2023-08-30'
group by a.emp_no;

-- 실습문제 1
-- 현재, 직원별 근무 부서를 출력
-- 사번, 직원이름(first_name), 부서명 순으로 출력
select * from dept_emp;
select * from departments;
select * from employees;

select a.emp_no as 사번, a.first_name as '직원이름', c.dept_name as 부서명
from employees a, dept_emp b, departments c
where a.emp_no = b.emp_no 
and b.dept_no = c.dept_no
and b.to_date = '9999-01-01';
    

-- 실습문제 2
-- 현재, 직책별 평균연봉과 직원수를 구하되 직책별 직원수가 100명 이상인 직책만 출력하시오.
-- titles, salaries, employees;
select * from titles;

select a.title, avg(b.salary) as '평균연봉', count(*) as '직원수'
from titles a, salaries b, employees c
where a.emp_no = c.emp_no
and b.emp_no = c.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '2023-08-30'
group by a.title
having 직원수 >=100;


-- 실습문제3
-- 현재, 부서별로 직책이 Engineer인 직원들에 대해서만 평균연봉을 구하시오.
-- 부서이름, 평균급여 순으로 출력하고 평균 연봉이 높은 순으로 정렬.
select * from titles;
select * from dept_emp;
select * from departments;
select a.dept_name as 부서이름, avg(c.salary) as 평균연봉
from departments a, dept_emp b, salaries c, titles d
where a.dept_no = b.dept_no
and b.emp_no = c.emp_no
and c.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '2023-08-30'
and d.to_date = '9999-01-01'
and d.title = 'Engineer'
group by 부서이름
order by 평균연봉 desc;


