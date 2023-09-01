-- 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select a.emp_no, b.first_name, a.salary
from salaries a, employees b
where a.emp_no = b.emp_no
order by a.salary desc;

-- 문제2. 확인 필요.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select a.emp_no, a.first_name, b.title
from employees a, titles b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
order by a.first_name;

-- 문제3.  패스
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select b.emp_no, b.first_name, a.dept_no
from dept_emp a, employees b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
order by b.first_name;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select a.emp_no, a.first_name, b.salary, c.title, d.dept_no
from employees a, salaries b, titles c, dept_emp d
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and a.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
order by a.first_name;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요.
-- 현재 ‘Technique Leader’의 직책으로 근무하는 사원은 고려하지 않습니다.
select a.emp_no as 사번, concat(a.first_name,' ',a.last_name) as 이름
from employees a, titles b
where a.emp_no = b.emp_no
and b.to_date !='9999-01-01'
and b.title = 'Technique Leader'
;


-- 문제6.
-- 직원 이름(last_name) 중에서 s로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select a.last_name, c.dept_name, d.title
from employees a, dept_emp b, departments c, titles d
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and a.emp_no = d.emp_no
and a.last_name like "s%";


-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가
-- 이름, 급여 출력
-- 급여가 큰 순서대로 출력하세요.
select concat(a.first_name,' ',a.last_name) as 이름 , b.salary 급여 
from employees a, salaries b, titles c
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and c.title = 'Engineer'
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and b.salary >= 40000
order by 급여 desc;


-- 문제8.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select a.dept_no as 부서, avg(salary) as 평균연봉 
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
group by a.dept_no
order by 평균연봉 desc;


-- 문제9.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select a.title as 직책 , avg(b.salary) as 평균연봉
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
order by 평균연봉 desc;
