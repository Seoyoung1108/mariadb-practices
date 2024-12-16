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

select * from cart;

insert into cart values (2, 3, 6);
delete from cart where user_no=3 and book_no=6;
select quantity, user_no, book_no from cart 
where user_no=3
order by no desc;

select c.quantity, c.user_no, c.book_no, b.title 
from cart c, book b
where c.book_no = b.no
and c.user_no = 37 
order by c.book_no asc;

delete from cart;


desc orders;
select * from orders;
insert into orders values (null, "fff", 12, "ff", "입금확인중", ?, ?);
delete from orders;

select no, number, payment, shipping, status, user_no from orders where no = 6 and user_no = 15;

select * from orders_book;
delete from orders_book;

select ob.quantity, ob.price, ob.book_no, ob.orders_no, o.user_no, b.title from orders_book ob, orders o, book b
where ob.book_no = b.no and ob.orders_no = o.no and ob.orders_no = ? and o.user_no = ?
order by ob.book_no asc;