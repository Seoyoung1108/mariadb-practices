-- 1) 집계쿼리: select 절에 집계함수(count, max, min, avg, variance, strdev, sum
select avg(salary), sum(salary) from salaries;

-- 2) select절에 집계함수(그룹함수)가 있는 경우, 어떤 컬럼도 select절에 올 수 없다!
--    emp_no는 아무 의미가 없다. => 오류!!
select avg(salary), emp_no from salaries;

-- 3) query 순서
-- 1. from: 접근 테이블 확인
-- 2. where: 조건에 맞는 row를 확인 선택(임시테이블)
-- 3. 집계(결과테이블)
-- 4. projection
select avg(salary) from salaries
	where emp_no=10060;

-- 4) group by
--    group by에 참여하고 있는 컬럼은 projection이 가능하다: select 절에 올 수 있다.
-- 예제: 사원별 평균 연봉은?
select emp_no, avg(salary) from salaries
	group by emp_no;

-- 5) having
-- 집계 결과(결과테이블)에서 row를 선택해야 하는 경우
-- 이미 where절은 실행이 되었기 때문에 having절에서 이 조건을 주어야 한다.
-- 예제: 평균월급이 60000 이상인 사원의 사번과 평균 연봉을 출력하세요.
select emp_no, avg(salary) as avg_salary from salaries
	group by emp_no
    having avg(salary)>=60000;

-- 6) order by
--    order by는 항상 맨 마지마 출력(projection) 전에 한다.
select emp_no, avg(salary) as avg_salary from salaries
	group by emp_no
    having avg(salary)>=60000
    order by avg(salary) asc;

-- 주의) 사번이 10060인 사원의 사번, 평균 급여, 급여 총합을 출력하세요.
-- select emp_no, avg(salary), sum(salary) from salaries
-- where emp_no=10060;
-- emp_no가 다 같아도 emp_no를 그냥 출력하면 안됨. (문법 오류)
    
select emp_no, avg(salary), sum(salary) from salaries
	group by emp_no
    having emp_no=10060;
-- 정답


