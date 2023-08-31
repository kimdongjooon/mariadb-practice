-- 혼합 SQL 문제입니다.

-- 문제1.
-- 현재, 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select avg(salary)
from salaries
where to_date = '9999-01-01';

select count(*)
from salaries
where to_date = '9999-01-01'
and salary > (
	select avg(salary)
	from salaries
	where to_date = '9999-01-01'
);

-- 문제2. (x) 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서, 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select a.dept_no, max(salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and b.to_date ='9999-01-01'
group by a.dept_no;

select 
	a.emp_no as 사번, 
	concat(a.first_name,' ',a.last_name) as 이름,
    b.dept_no as 부서,
    c.salary as 연봉
from employees a, dept_emp b, salaries c
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and c.to_date = '9999-01-01'
and (b.dept_no,c.salary) in (
	select a.dept_no, max(salary)
	from dept_emp a, salaries b
	where a.emp_no = b.emp_no
	and b.to_date ='9999-01-01'
	group by a.dept_no
)
order by 연봉 desc;


-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select a.dept_no, avg(salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
group by a.dept_no;

select 
	a.emp_no as 사번,
	concat(a.first_name,' ',a.last_name) as 이름,
    b.salary
from employees a, salaries b, dept_emp c, (	
	select a.dept_no, avg(salary) as 평균연봉
	from dept_emp a, salaries b
	where a.emp_no = b.emp_no
	and b.to_date = '9999-01-01'
	group by a.dept_no) d
where a.emp_no = b.emp_no
	and a.emp_no = c.emp_no
	and c.dept_no = d.dept_no
	and b.to_date = '9999-01-01'
	and b.salary > d.평균연봉;

select * from dept_manager;
-- 문제4. 재확인.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select 
	a.emp_no as 사번, 
	concat(a.first_name,' ',a.last_name) as 사원_이름, 
    concat(e.first_name,' ',e.last_name) as 부서_매니저_이름,
	d.dept_name as 부서_이름
from (((employees a left join dept_emp b on a.emp_no = b.emp_no) 
	left join dept_manager c on b.dept_no = c.dept_no) 
	left join departments d on b.dept_no = d.dept_no)
    left join employees e on c.emp_no = e.emp_no
where c.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
;

-- 문제5. 확인 필요.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select a.dept_no, avg(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.dept_no
limit 1;

select 
	a.emp_no, 
	a.first_name,
    b.title,
    c.salary
from employees a, titles b, salaries c,dept_emp d,(
	select a.dept_no, avg(b.salary)
	from dept_emp a, salaries b
	where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
	group by a.dept_no
	limit 1
	)e
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and a.emp_no = d.emp_no
and d.dept_no = e.dept_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
order by c.salary desc;

-- 문제6.
-- 평균 연봉이 가장 높은 부서는?
-- 총무 20000 
select 
	a.dept_name as 부서, 
    avg(salary) as 평균연봉 
from departments a, dept_emp b,salaries c
where a.dept_no = b.dept_no
and b.emp_no = c.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
group by a.dept_name
order by 평균연봉 desc
limit 1;


-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- Engineer 40000
select 
	a.title as 직책,
    avg(salary) as 평균연봉
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
order by 평균연봉 desc
limit 1;

-- 문제8. (순수 join 문제)
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select 
	b.dept_no,
    concat(a.first_name,' ',a.last_name) as 매니저이름,
    avg(salary) as 매니저연봉
from employees a, dept_manager b, salaries c
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
group by b.dept_no;

-- 1번 서브쿼리
select 
	d.dept_name as 부서이름,
    concat(a.first_name,' ',a.last_name) as 사원이름,
    b.salary as 연봉,
    e.매니저이름,
    e.매니저연봉 
from employees a, salaries b, dept_emp c, departments d,(
	select 
		bb.dept_no,
		concat(aa.first_name,' ',aa.last_name) as 매니저이름,
		avg(salary) as 매니저연봉
	from employees aa, dept_manager bb, salaries cc
	where aa.emp_no = bb.emp_no
		and aa.emp_no = cc.emp_no
        and bb.to_date = '9999-01-01'
        and cc.to_date = '9999-01-01'
	group by bb.dept_no ) e
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and c.dept_no = d.dept_no
and d.dept_no = e.dept_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
group by 연봉 desc;

-- left join
select 
	c.dept_name as 부서이름,
    concat(a.first_name,' ',a.last_name) as 사원이름,
    f.salary as 연봉,
    concat(e.first_name,' ',e.last_name) as 매니저이름,
    g.salary as 매니저연봉
from (((((employees a left join dept_emp b on a.emp_no = b.emp_no)
	left join departments c on b.dept_no = c.dept_no)
    left join dept_manager d on c.dept_no = d.dept_no)
    left join employees e on d.emp_no = e.emp_no)
    left join salaries f on a.emp_no = f.emp_no)
    left join salaries g on e.emp_no = g.emp_no
where b.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and f.to_date = '9999-01-01'
and g.to_date = '9999-01-01'
and f.salary > g.salary
order by 연봉 desc;



