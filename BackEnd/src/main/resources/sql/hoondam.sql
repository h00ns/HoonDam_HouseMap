drop table happyhouse.qna;
CREATE TABLE `happyhouse`.`qna` (
`id` INT NOT NULL AUTO_INCREMENT,
`title` VARCHAR(45) NOT NULL,
`content` VARCHAR(45) NOT NULL,
`writer` VARCHAR(45) NOT NULL,
`isprivate` VARCHAR(45) NOT NULL DEFAULT 'false',
`answer` VARCHAR(45) NULL,
`writetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`completed` VARCHAR(45) NULL DEFAULT 'false',
PRIMARY KEY (`id`));


insert into happyhouse.qna (title,content,writer)
values ('test','화이팅','tomato');



insert into happyhouse.qna (title,content,writer)
values ('뷰관통','화이팅','훈담');

insert into happyhouse.qna (title,content,writer,answer)
values ('뷰관통','화이팅','훈담','안녕하세요');
select * from qna;
select * from  happyhouse.qna ;



CREATE TABLE  happyhouse.board (
id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(45) NOT NULL,
content VARCHAR(45) NOT NULL,
writer VARCHAR(45) NOT NULL,
boarddate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id));

CREATE TABLE  happyhouse.board (
id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(45) NOT NULL,
content VARCHAR(45) NOT NULL,
writer VARCHAR(45) NOT NULL,
boarddate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id));

