desc employee;
desc department;

select version(), current_date(), current_time, now() FROM dual;

-- 수학함수, 사칙연산도 된다.
select sin(pi()/4),1+2*3-4/5 from dual;

-- 대소문자 구분이 없다.
select version(), CURRENT_DATE, NOW() FROM DUAL;

-- table 생성
create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- table 삭제
drop table pet;

-- schema 확인
describe pet;
desc pet;

-- insert (C)
insert into pet values('aaa','bbb','dog','m','2020-12-14',null);

-- select (R)
select * from pet;

-- update (U)
update pet set name ='ccc' where name='aaa';
update pet set death=null where death='0000-00-00';

-- delete (D)
delete from pet where name = 'ccc';

-- load data: mysql(CLI) Local 전용
-- 리눅스에서 load data local infile '/home/shinee/pet.txt' into table pet;

-- select 연습
select name, species
	from pet
	where name = 'bowser';
    
select name, species, birth
	from pet
	where birth >= '1998-01-01';
    
select name, species, gender
	from pet
    where species='dog' and gender='f';
    
select name, species
	from pet
    where species='snake' or species='bird';
    
select name, birth, death
	from pet
    where death is null;
    
select name
	from pet
    where name like 'b%';
    
select name
	from pet
	where name like '%fy';
    
 select name
	from pet
	where name like '%w%';
    
select name
	from pet
    where name like 'b___';
    
select count(*), max(birth) from pet; -- 특정 칼럼으로 작성 시 null 안 셈.


