# --- First database schema

# --- !Ups

create table user (
    id int auto_increment primary key,
    name varchar(255) not null,
    text varchar(500) not null,
);

insert into user (name,text) values ('aaaaa', 'aaaaa');
insert into user (name,text) values ('aaaaa', 'aaaaa');

-- CREATE TABLE user (
--     id BIGINT AUTO_INCREMENT NOT NULL,
--     name VARCHAR(255) NOT NULL,
--     text VARCHAR(500) NOT NULL,
--     PRIMARY KEY(id)
-- )

-- INSERT INTO user (name, text) VALUES ('頼む', '動いてくれ');
-- INSERT INTO user (`id`,`name`,`text`) VALUES (1,`名無し`, `頼む動いてくれ`);

# --- !Downs

drop table user;
