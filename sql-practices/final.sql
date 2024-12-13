desc user;

insert into user values (null, 'aaa','fd','ff','000');

select * from user;
select no, name, email, password, phonenumber from user order by no desc;

delete from user;

insert into category values (null, 'dk');
select * from category;

select no, name from category order by no desc;
delete from category;

desc book;
insert into book values (null, 'dd', 12, 5);

select * from book;
select no, title, price, category_no from category order by no desc;

delete from book;