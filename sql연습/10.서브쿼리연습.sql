-- Subquery

-- 1) select 절, (insert 절)의 서브쿼리
select (select 1+1 from dual) from dual;
-- insert into t1 values(null,(select max(no) from t1));

-- 2) from 절의 서브쿼리
select now() as n, sysdate() as s, 3+1 as r from dual;
select a.n, a.r
	from (select now() as n, sysdate() as s, 3+1 as r from dual) a;

-- 3) where 절의 서브쿼리
-- 서브쿼리 값이 딱 하나
-- 예제) 현재, Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력해보세요. 
select b.dept_no
	from employees a, dept_emp b
	where a.emp_no=b.emp_no
    and b.to_date='9999-01-01'
    and concat(a.first_name, ' ', a.last_name)='Fai Bale';

-- d004
select a.emp_no, a.first_name
	from employees a, dept_emp b
    where a.emp_no=b.emp_no
    and b.to_date='9999-01-01'
    and b.dept_no='d004';

-- -> 서브쿼리로 하기
select a.emp_no, a.first_name
	from employees a, dept_emp b
    where a.emp_no=b.emp_no
    and b.to_date='9999-01-01'
    and b.dept_no=(select b.dept_no
					from employees a, dept_emp b
					where a.emp_no=b.emp_no
					and b.to_date='9999-01-01'
					and concat(a.first_name, ' ', a.last_name)='Fai Bale');

-- 3-1) 단일행 연산자: =, >, <, >=, <=, <>, !=
-- 실습문제1
-- 현재, 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력하세요.
select avg(salary)
from salaries
where to_date='9999-01-01';

select concat(a.first_name, ' ', a.last_name) as '이름', b.salary
	from employees a, salaries b
    where a.emp_no=b.emp_no
    and b.to_date='9999-01-01'
    and b.salary < (select avg(salary)
					from salaries
					where to_date='9999-01-01');

-- 실습문제2
-- 현재, 직책별 평균급여 중에 가장 적은 평균급여의 직책이름과 그 평균급여를 출력하세요.
-- 1) 직책별 평균 급여
select avg(a.salary) as avg_salary, b.title
	from salaries a, titles b
    where a.emp_no=b.emp_no
    and a.to_date='9999-01-01'
    and b.to_date='9999-01-01'
    group by b.title;
    
 -- 2) 직책별 가장 적은 평균 급여
 select min(avg_salary)
	from(select avg(a.salary) as avg_salary, b.title
			from salaries a, titles b
			where a.emp_no=b.emp_no
			and a.to_date='9999-01-01'
			and b.to_date='9999-01-01'
			group by b.title) a;
            
-- 3) sol1: where절 subquery(=)
select b.title, avg(a.salary)
	from salaries a, title b
	where a.emp_no=b.emp_no
	and a.to_date='9999-01-01'
	and b.to_date='9999-01-01'
    group by b.title
    having avg(a.salary)=( select min(avg_salary)
							from(select avg(a.salary) as avg_salary, b.title
									from salaries a, titles b
									where a.emp_no=b.emp_no
									and a.to_date='9999-01-01'
									and b.to_date='9999-01-01'
									group by b.title) a);

-- 4) sol2: top-k
select avg(a.salary) as avg_salary, b.title
	from salaries a, titles b
    where a.emp_no=b.emp_no
    and a.to_date='9999-01-01'
    and b.to_date='9999-01-01'
    group by b.title
    order by avg_salary asc
    limit 1;
    
-- 3-1) 복수행 연산자 in, not in, 비교연산자 any, 비교연산자 all
-- any 사용법
-- 1. = any: in
-- 2. > any, >= any: 최소값
-- 3. < any, <= any: 최대값
-- 4. <> any, != any: not in

-- all 사용법
-- 1. = all (X)
-- 2. > all, >= all: 최대값
-- 3. < all, <= all: 최소값
-- 4. <> all, != all

-- 실습문제3
-- 현재 급여 50000 이상인 직원의 이름과 급여를 출력하세요

-- sol01
select concat(a.first_name, ' ', a.last_name) as '이름', b.salary
from employees a, salaries b
where a.emp_no=b.emp_no
and b.to_date='9999-01-01'
and b.salary>=50000;

-- sol02
select concat(a.first_name, ' ', a.last_name) as '이름', b.salary
from employees a, salaries b
where a.emp_no=b.emp_no
and b.to_date='9999-01-01'
and (a.emp_no, b.salary) in (select emp_no, salary
								from salaries
                                where to_date='9999-01-01'
                                and salary>=50000);

-- 실습문제4
-- 현재, 각 부서별 최고급여를 받고 있는 직원의 이름, 부서이름, 급여를 출력해 보세요.

-- sol1: where in
-- 1) 각 부서별 최고 급여 -> 간단하게~
select de.dept_no, max(s.salary)
from dept_emp de, salaries s
where de.emp_no=s.emp_no
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
group by de.dept_no;

-- 2) 답
select e.first_name, d.dept_name, s.salary
from employees e, departments d, dept_emp de, salaries s
where e.emp_no=s.emp_no
and de.emp_no=e.emp_no
and d.dept_no=de.dept_no
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
and (de.dept_no, s.salary) in (select de.dept_no, max(s.salary)
								from dept_emp de, salaries s
								where de.emp_no=s.emp_no
								and de.to_date='9999-01-01'
								and s.to_date='9999-01-01'
								group by de.dept_no);


-- sol2: from 절, join
select e.first_name, d.dept_name, s.salary
from employees e, departments d, dept_emp de, salaries s,
	(select de.dept_no, max(s.salary) as max_salary
		from dept_emp de, salaries s
		where de.emp_no=s.emp_no
		and de.to_date='9999-01-01'
		and s.to_date='9999-01-01'
		group by de.dept_no) k
where e.emp_no=s.emp_no
and de.emp_no=e.emp_no
and d.dept_no=de.dept_no
and k.dept_no=d.dept_no
and de.to_date='9999-01-01'
and s.to_date='9999-01-01'
and s.salary = k.max_salary;

