# --- First database schema

# --- !Ups

create table user (
    id int auto_increment primary key,
    name varchar(255) not null,
    text varchar(500) not null,
);

insert into user (name,text) values ('aaaaa', 'aaaaa');
insert into user (name,text) values ('aaaaa', 'aaaaa');

# --- !Downs

drop table user;
