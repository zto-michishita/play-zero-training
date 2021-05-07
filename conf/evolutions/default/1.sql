# --- First database schema

# --- !Ups

create table users (
    id int auto_increment primary key,
    name varchar(255) not null,
    text varchar(500) not null,
);

insert into users (id,name,text) values (default, '名無し', 'サンプルテキストです。');
insert into users (id,name,text) values (default, '名無し', 'サンプルテキスト２です。');

# --- !Downs

drop table users;
