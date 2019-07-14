create table if not exists public.users
(
id bigserial constraint user_id PRIMARY KEY ,
    name varchar (255) not null ,
    age integer default 0
);