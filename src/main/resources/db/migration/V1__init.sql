DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id         bigint primary key auto_increment,
    username   varchar(80) not null,
    password   varchar(80) not null,
    email      varchar(80) unique,
    shelter_id bigint    default 0,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

// users roles
DROP TABLE IF EXISTS roles;
CREATE TABLE IF NOT EXISTS roles
(
    id         bigint primary key auto_increment,
    code       varchar(80) unique,
    name       varchar(80) unique,
    status     int       default 1,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
INSERT INTO roles (code, name)
VALUES ('admin', 'Администратор'),
       ('sponsor', 'Спонсор'),
       ('volunteer', 'Волонтёр'),
       ('orphanage', 'Официальный представитель детского дома'),
       ('pupil', 'Воспитанник');

DROP TABLE IF EXISTS users_roles;

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

DROP TABLE IF EXISTS cities;

CREATE TABLE IF NOT EXISTS cities
(
    id   bigint primary key auto_increment,
    name varchar(80) null,
    lat  varchar(80) null,
    lng  varchar(80) null
);

DROP TABLE IF EXISTS shelters;

CREATE TABLE IF NOT EXISTS shelters
(
    id         bigint primary key auto_increment,
    name       varchar(80) null,
    number     bigint,
    city_id    bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS events;

CREATE TABLE IF NOT EXISTS events
(
    id         bigint primary key auto_increment,
    name       varchar(100),
    comm       varchar(300),
    status     int,
    start_date timestamp,
    count_days int,
    user_id    bigint not null references users (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS users_events;

CREATE TABLE IF NOT EXISTS users_events
(
    user_id  bigint not null references users (id),
    event_id bigint not null references events (id),
    primary key (user_id, event_id)
);

DROP TABLE IF EXISTS images;

CREATE TABLE IF NOT EXISTS images
(
    id    bigint primary key auto_increment,
    name  varchar(100),
    comm  text,
    image varchar(300)
);

DROP TABLE IF EXISTS users_images;

CREATE TABLE IF NOT EXISTS users_images
(
    user_id  bigint not null references users (id),
    image_id bigint not null references images (id),
    primary key (user_id, image_id)
);

DROP TABLE IF EXISTS profile;

CREATE TABLE IF NOT EXISTS profile
(
    id        bigint primary key auto_increment,
    user_id   bigint      not null references users (id),
    firstname varchar(80) not null,
    lastname  varchar(80) not null,
    image     bigint references images (id),
    comment   text
);

DROP TABLE IF EXISTS dreams;

CREATE TABLE IF NOT EXISTS dreams
(
    id          bigint primary key auto_increment,
    description varchar,
    annotation  varchar,
    user_id     bigint not null references users (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

DROP TABLE IF EXISTS tags;

CREATE TABLE IF NOT EXISTS tags
(
    id   bigint primary key auto_increment,
    name varchar(100)
);

DROP TABLE IF EXISTS dreams_tags;

CREATE TABLE IF NOT EXISTS dreams_tags
(
    dream_id bigint not null references dreams (id),
    tag_id   bigint not null references tags (id),
    primary key (dream_id, tag_id)
);

DROP TABLE IF EXISTS users_tags;

CREATE TABLE IF NOT EXISTS users_tags
(
    user_id bigint not null references users (id),
    tag_id  bigint not null references tags (id),
    primary key (user_id, tag_id)
)

