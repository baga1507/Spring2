drop table users CASCADE;
drop table roles CASCADE;
drop table users_roles CASCADE;

create table users (
                       id         bigserial primary key,
                       username   varchar(36) not null,
                       password   varchar(80) not null,
                       email      varchar(50) unique,
                       created_at timestamp default current_timestamp,
                       updated_at timestamp default current_timestamp
);

create table roles (
                       id         bigserial primary key,
                       name       varchar(50) not null,
                       created_at timestamp default current_timestamp,
                       updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles (
                             user_id bigint not null references users (id),
                             role_id bigint not null references roles (id),
                             primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('mike', '$2y$10$hocTtdQXZF1Q6RSeqsSO6uq2MqzORZgZkOOOCzAhmkI6DtR6kJs.S', 'mike@gmail.com'),
       ('jimmy', '$2y$10$hocTtdQXZF1Q6RSeqsSO6uq2MqzORZgZkOOOCzAhmkI6DtR6kJs.S', 'jimmy@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);