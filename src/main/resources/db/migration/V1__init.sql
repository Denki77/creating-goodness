drop table IF EXISTS users;

create table IF NOT EXISTS users
(
    id         bigint primary key,
    username   varchar(80) not null,
    password   varchar(80) not null,
    email      varchar(80) unique,
    shelter_id bigint      null,
    profile_id  bigint  references profile (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
drop table IF EXISTS roles;

create table IF NOT EXISTS roles
(
    id         bigint primary key,
    name       varchar(80) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
drop table IF EXISTS users_roles;
create table IF NOT EXISTS users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);
drop table IF EXISTS cities;

create table IF NOT EXISTS cities
(
    id   bigint primary key,
    name varchar(80) null,
    lat  varchar(80) null,
    lng  varchar(80) null
);
drop table IF EXISTS shelters;

create table IF NOT EXISTS shelters
(
    id         bigint primary key,
    name       varchar(80) null,
    number     bigint,
    city_id    bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table IF NOT EXISTS events
(
    id          bigserial  primary key,
    name        varchar(100),
    comm        varchar(300),
    status      int,
    start_date  timestamp,
    count_days  int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table IF NOT EXISTS users_events (
   user_id                 bigint not null references users (id),
   event_id                bigint not null references events (id),
   primary key (user_id, event_id)
);

create table IF NOT EXISTS images (
    id      bigint  primary key,
    name    varchar(100),
    comm    text,
    image   varchar(300)
);

create table IF NOT EXISTS users_images (
    user_id                 bigint not null references users (id),
    image_id                bigint not null references images (id),
    primary key (user_id, image_id)
);

create table IF NOT EXISTS profile (
   id                  bigint primary key,
   user_id             bigint not null references users (id),
   firstname           varchar(80) not null,
   lastname            varchar(80) not null,
   image               bigint references images (id),
   comm                text
);

