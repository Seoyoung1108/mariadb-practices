-- join

-- 예제: 이름이 'parto hitomi'인 직원의 직책을 구하세요.
select emp_no from employees
	where concat(first_name, ' ', last_name) = 'parto hitomi';
    
-- 11052
select title from titles
	where emp_no=11052;
    
select a.emp_no, a.first_name, a.last_name, b.title
from employees a, titles b -- join condition
where a.emp_no=b.emp_no and concat(a.first_name, ' ', a.last_name) = 'parto hitomi'; -- row select condition




