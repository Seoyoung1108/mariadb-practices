-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 단 조회결과는 급여의 내림차순으로 정렬되어 나타나야 합니다. 

-- 문제1.
-- 현재 전체 사원의 평균 급여보다 많은 급여를 받는 사원은 몇 명이나 있습니까?
-- 1) ~~ 평균 급여
select avg(salary)
from salaries
where to_date='9999-01-01';

-- 2) 답
select count(emp_no)
from salaries
where to_date='9999-01-01'
and salary > (select avg(salary)
				from salaries
				where to_date='9999-01-01');

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 급여을 조회하세요. 단 조회결과는 급여의 내림차순으로 정렬합니다.
-- 이미 품.

-- 문제3.
-- 현재, 사원 자신들의 부서의 평균급여보다 급여가 많은 사원들의 사번, 이름 그리고 급여를 조회하세요.
-- 1) 부서별 평균 급여
select s.emp_no, de.dept_no, avg(s.salary) as avg_salary
from dept_emp de, salaries s
where de.emp_no=s.emp_no
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
group by de.dept_no;

-- 2) 답
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '급여'
from employees e, salaries s, (select s.emp_no, de.dept_no, avg(s.salary) as avg_salary
								from dept_emp de, salaries s
								where de.emp_no=s.emp_no
								and de.to_date='9999-01-01'
								and s.to_date='9999-01-01'
								group by de.dept_no) k
where k.emp_no=e.emp_no
and e.emp_no=s.emp_no
and s.to_date='9999-01-01'
and s.salary>=k.avg_salary;
        
-- 문제4.
-- 현재, 사원들의 사번, 이름, 그리고 매니저 이름과 부서 이름을 출력해 보세요.
-- employee를 두개? 올리래

-- 1) employees - dept_manager
select de.dept_no, e.first_name
from employees e, dept_manager de
where e.emp_no=de.emp_no
and de.to_date='9999-01-01';

-- 2) 답
select e.first_name as '이름', e.emp_no as '사번', k.first_name as '매니저', d.dept_name as '부서'
from employees e, dept_emp de, departments d, (select de.dept_no, e.first_name
	from employees e, dept_manager de
	where e.emp_no=de.emp_no
    and de.to_date='9999-01-01') k
where e.emp_no=de.emp_no
and de.dept_no=d.dept_no
and k.dept_no=d.dept_no
and de.to_date='9999-01-01';

-- 문제5.
-- 현재, 평균급여가 가장 높은 부서의 사원들의 사번, 이름, 직책 그리고 급여를 조회하고 급여 순으로 출력하세요.
-- 1) 평균급여가 가장 높은 부서
select de.dept_no
from dept_emp de, salaries s
where de.emp_no=s.emp_no
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
group by de.dept_no
order by avg(s.salary) desc limit 1;

-- 2) 답
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', t.title as '직책', s.salary as '급여'
from employees e, titles t, salaries s, dept_emp de
where e.emp_no=t.emp_no
and e.emp_no=s.emp_no
and de.emp_no=e.emp_no
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
and t.to_date='9999-01-01'
and de.dept_no=(select de.dept_no
				from dept_emp de, salaries s
				where de.emp_no=s.emp_no
				and de.to_date='9999-01-01'
				and s.to_date='9999-01-01'
				group by de.dept_no
				order by avg(s.salary) desc limit 1)
order by s.salary asc;

-- 문제6.
-- 현재, 평균 급여가 가장 높은 부서의 이름 그리고 평균급여를 출력하세요.
select d.dept_name, avg(s.salary) as avg_salary
from dept_emp de, salaries s, departments d
where de.emp_no=s.emp_no
and de.dept_no=d.dept_no
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
group by de.dept_no
order by avg_salary desc limit 1;

-- 문제7.
-- 현재, 평균 급여가 가장 높은 직책의 타이틀 그리고 평균급여를 출력하세요.
select t.title, avg(s.salary) as avg_salary
from titles t, salaries s
where t.emp_no=s.emp_no
and t.to_date='9999-01-01'
and s.to_date='9999-01-01'
group by t.title
order by avg_salary desc limit 1;
