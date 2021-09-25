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

drop table IF EXISTS events;

create table IF NOT EXISTS events
(
    id          bigint  primary key,
    name        varchar(100),
    comm        varchar(300),
    status      int,
    start_date  timestamp,
    count_days  int,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

drop table IF EXISTS users_events;

create table IF NOT EXISTS users_events (
   user_id                 bigint not null references users (id),
   event_id                bigint not null references events (id),
   primary key (user_id, event_id)
);

drop table IF EXISTS images;

create table IF NOT EXISTS images (
    id      bigint  primary key,
    name    varchar(100),
    comm    text,
    image   varchar(300)
);

drop table IF EXISTS users_images;

create table IF NOT EXISTS users_images (
    user_id                 bigint not null references users (id),
    image_id                bigint not null references images (id),
    primary key (user_id, image_id)
);

drop table IF EXISTS profile;

create table IF NOT EXISTS profile (
   id                  bigint primary key,
   user_id             bigint not null references users (id),
   firstname           varchar(80) not null,
   lastname            varchar(80) not null,
   image               bigint references images (id),
   comm                text
);

drop table IF EXISTS dreams;

create table IF NOT EXISTS dreams (
    id              bigint primary key,
    description     varchar,
    annotation      varchar,
    user_id         bigint not null references users (id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

drop table IF EXISTS tags;

create table IF NOT EXISTS tags (
    id              bigint primary key,
    name            varchar(100)
)

drop table IF EXISTS dreams_tags;

create table IF NOT EXISTS dreams_tags (
    dream_id              bigint not null references dreams (id),
    tag_id                bigint not null references tags (id),
    primary key (dream_id, tag_id)
)

