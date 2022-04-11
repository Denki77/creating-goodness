DROP TABLE IF EXISTS users_events cascade;
DROP TABLE IF EXISTS users_tags cascade;
DROP TABLE IF EXISTS users_roles cascade;
DROP TABLE IF EXISTS dreams_tags cascade;
DROP TABLE IF EXISTS users_images cascade;
DROP TABLE IF EXISTS profile cascade;
DROP TABLE IF EXISTS images cascade;
DROP TABLE IF EXISTS events cascade;
DROP TABLE IF EXISTS roles cascade;
DROP TABLE IF EXISTS dreams cascade;
DROP TABLE IF EXISTS users_dreams cascade;
DROP TABLE IF EXISTS tags cascade;
DROP TABLE IF EXISTS cities cascade;
DROP TABLE IF EXISTS shelters cascade;
DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS permissions cascade;

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   varchar(80) not null,
    password   varchar(80) not null,
    email      varchar(80) unique,
    shelter_id bigint    default 0,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE IF NOT EXISTS roles
(
    id         SERIAL PRIMARY KEY,
    code       varchar(80) unique,
    name       varchar(80) unique,
    status     int       default 1,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS cities
(
    id   SERIAL PRIMARY KEY,
    name varchar(80) null,
    lat  varchar(80) null,
    lng  varchar(80) null
);

CREATE TABLE IF NOT EXISTS shelters
(
    id         SERIAL PRIMARY KEY,
    name       varchar(80) null,
    number     bigint,
    city_id    bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE IF NOT EXISTS events
(
    id         SERIAL PRIMARY KEY,
    name       varchar(100),
    comm       varchar(300),
    status     int,
    start_date timestamp,
    count_days int,
    user_id    bigint not null references users (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE IF NOT EXISTS users_events
(
    user_id  bigint not null references users (id),
    event_id bigint not null references events (id),
    primary key (user_id, event_id)
);

CREATE TABLE IF NOT EXISTS images
(
    id    SERIAL PRIMARY KEY,
    name  varchar(100),
    comm  text,
    image varchar(300)
);

CREATE TABLE IF NOT EXISTS users_images
(
    user_id  bigint not null references users (id),
    image_id bigint not null references images (id),
    primary key (user_id, image_id)
);

CREATE TABLE IF NOT EXISTS profile
(
    id        SERIAL PRIMARY KEY,
    user_id   bigint not null references users (id),
    firstname varchar(80),
    lastname  varchar(80),
    image     bigint references images (id),
    comment   text
);

CREATE TABLE IF NOT EXISTS dreams
(
    id          SERIAL PRIMARY KEY,
    description varchar,
    annotation  varchar,
    user_id     bigint not null references users (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

CREATE TABLE IF NOT EXISTS tags
(
    id   SERIAL PRIMARY KEY,
    name varchar(100)
);

CREATE TABLE IF NOT EXISTS dreams_tags
(
    dream_id bigint not null references dreams (id),
    tag_id   bigint not null references tags (id),
    primary key (dream_id, tag_id)
);

CREATE TABLE IF NOT EXISTS users_dreams
(
    dream_id bigint not null references dreams (id),
    user_id  bigint not null references users (id),
    primary key (dream_id, user_id)
);

CREATE TABLE IF NOT EXISTS users_tags
(
    user_id bigint not null references users (id),
    tag_id  bigint not null references tags (id),
    primary key (user_id, tag_id)
);

CREATE TABLE IF NOT EXISTS permissions
(
    role_id    bigint       not null references roles (id),
    permission varchar(100) not null,
    primary key (role_id, permission)
)
