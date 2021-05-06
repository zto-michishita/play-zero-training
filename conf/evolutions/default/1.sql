# --- First database schema

# --- !Ups

create table users (
  id                        bigint not null,
  name                      varchar(255)
  text                      varchar(500),
  introduced                timestamp,
  discontinued              timestamp,
  constraint pk_users      primary key (id))
;

# --- !Downs
drop table users;
