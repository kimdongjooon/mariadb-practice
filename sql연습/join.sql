-- inner join

-- ex1 ) 현재, 근무하고 있는 사원의 사번 과 이름과 직책 출력.
select a.emp_no, a.first_name, b.title
from employees a,titles b
where a.emp_no = b.emp_no  -- join 조건 (n-1)
and b.to_date ='9999-01-01' -- row 선택 조건
