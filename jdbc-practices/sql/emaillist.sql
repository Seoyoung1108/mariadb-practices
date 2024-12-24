-- Forward engineering 복사
CREATE TABLE IF NOT EXISTS `webdb`.`emaillist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

desc emaillist;

-- insert
insert into emaillist
values (null,'둘','리','dooly@gmail.com');


-- delete
delete from emaillist
where id=1;


-- list
select id, first_name, last_name, email from emaillist
order by id desc;

select * from emaillist;


desc book;
desc author;

CREATE TABLE IF NOT EXISTS `webdb`.`guestbook` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `contents` TEXT NOT NULL,
  `reg_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

desc guestbook;
select * from guestbook;

insert into guestbook
values (null,'둘리','1234','hihihihihi',now());

delete from guestbook;
delete from guestbook where id=4 and password='3333';

CREATE TABLE IF NOT EXISTS `webdb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `gender` ENUM('male', 'female') NOT NULL,
  `join_date` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

desc user;
insert into user values (null,"aa","aa@gmial","1234","male",curdate());
select * from user;
select id, name from user where email=? and password=?;
select name, email, gender from user where id=4;

update user set name = '둘리2', password = 'ddd', gender = 'male' where id = 4;

CREATE TABLE IF NOT EXISTS `webdb`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `contents` TEXT NOT NULL,
  `hit` INT NOT NULL,
  `reg_date` DATETIME NOT NULL,
  `g_no` INT NOT NULL,
  `o_no` INT NOT NULL,
  `depth` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_board_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `webdb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

desc board;
select * from board;
select id, title, contents, hit, date_format(reg_date, '%Y-%m-%d %h:%i:%s'), g_no, o_no, depth, user_id from board where id=4;

select * from board;
select b.id, b.title, b.contents, b.hit, date_format(b.reg_date, '%Y-%m-%d %h:%i:%s'), b.g_no, b.o_no, b.depth, b.user_id, u.name 
from board as b, user as u
where b.user_id = u.id
order by g_no desc, o_no asc;

delete from board;

insert into board values (null, "모먹지?", "ㅁㅁ", 0, now(), 1,1,0,4);
insert into board values (null, "ㅎㅇ", "ㅇㅇ", 0, now(), 2,1,0,5);
insert into board values (null, "짬뽕", "ㅁㅁ", 0, now(), 1,4,1,4);
insert into board values (null, "국수?", "ㅁㅁ", 0, now(), 1,2,1,4);
insert into board values (null, "ㄴㄴ", "ㅁㅁ", 0, now(), 1,3,2,5);
insert into board values (null, "test2", "ㅁㅁ", 0, now(), (select max(g_no)+1 from board as g_no),3,2,5);
select max(g_no) from board;
update board set title="z", contents="ss" where id=1;

insert into board values (null, "test5", "ㅁㅁ", 0, now(), 5,(select max(o_no)+1 from board as o_no where g_no=5),2,5);

update board set o_no=o_no+1 where g_no=1 and o_no>=2;

delete from board where g_no=1 and o_no>=2;