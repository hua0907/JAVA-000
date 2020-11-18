drop table if exists user_info;

create table user_info
(
  id        int auto_increment primary key,
  user_name varchar(50) not null,
  age int(3) not null
);