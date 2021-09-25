
create table IF NOT EXISTS users (
    id                      bigserial primary key,
    username                varchar(80) not null,
    password                varchar(80) not null,
    email                   varchar(80) unique,
    shelter_id              bigint null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table IF NOT EXISTS roles (
    id                      bigserial primary key,
    name                    varchar(80) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table IF NOT EXISTS users_roles (
    user_id                 bigint not null references users (id),
    role_id                 bigint not null references roles (id),
    primary key (user_id, role_id)
);

create table IF NOT EXISTS cities (
    id                     bigserial primary key,
    name                   varchar(80) null,
    lat                    varchar(80) null,
    lng                    varchar(80) null
);

create table IF NOT EXISTS shelters (
    id                     bigserial primary key,
    name                   varchar(80) null,
    number                 bigint,
    city_id                bigint,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);


