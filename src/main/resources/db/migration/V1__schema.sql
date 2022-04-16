DROP TABLE IF EXISTS profile_events CASCADE;
DROP TABLE IF EXISTS profile_tags CASCADE;
DROP TABLE IF EXISTS users_roles CASCADE;
DROP TABLE IF EXISTS dreams_tags CASCADE;
DROP TABLE IF EXISTS profile_images CASCADE;
DROP TABLE IF EXISTS profile_interest CASCADE;
DROP TABLE IF EXISTS interests CASCADE;
DROP TABLE IF EXISTS profile CASCADE;
DROP TABLE IF EXISTS images CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS dreams CASCADE;
DROP TABLE IF EXISTS tags CASCADE;
DROP TABLE IF EXISTS cities CASCADE;
DROP TABLE IF EXISTS shelters CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS permissions CASCADE;
DROP TABLE IF EXISTS interest CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(80) NOT NULL,
    password   VARCHAR(80) NOT NULL,
    email      VARCHAR(80) UNIQUE,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS roles
(
    id         SERIAL PRIMARY KEY,
    code       VARCHAR(80) UNIQUE,
    names      VARCHAR(80) UNIQUE,
    status     INT       DEFAULT 1,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL REFERENCES users (id),
    role_id BIGINT NOT NULL REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS permissions
(
    role_id    BIGINT       NOT NULL REFERENCES roles (id),
    permission VARCHAR(100) NOT NULL,
    PRIMARY KEY (role_id, permission)
);

CREATE TABLE IF NOT EXISTS cities
(
    id    SERIAL PRIMARY KEY,
    names VARCHAR(255) NOT NULL,
    lat   VARCHAR(80)  NULL,
    lng   VARCHAR(80)  NULL
);

CREATE TABLE IF NOT EXISTS shelters
(
    id          SERIAL PRIMARY KEY,
    names       VARCHAR(80) NULL,
    number      BIGINT,
    annotation  VARCHAR,
    description TEXT,
    city_id     BIGINT REFERENCES cities (id),
    created_at  TIMESTAMP DEFAULT current_timestamp,
    updated_at  TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS profile
(
    id          SERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES users (id),
    shelter_id  BIGINT DEFAULT NULL REFERENCES shelters (id),
    firstname   VARCHAR(80),
    lastname    VARCHAR(80),
    annotation  VARCHAR,
    description TEXT
);

CREATE TABLE IF NOT EXISTS events
(
    id                    SERIAL PRIMARY KEY,
    names                 VARCHAR(100),
    annotation            VARCHAR,
    description           VARCHAR(300),
    status                INT,
    start_date            TIMESTAMP,
    count_days            INT,
    maintainer_profile_id BIGINT NOT NULL REFERENCES profile (id),
    created_at            TIMESTAMP DEFAULT current_timestamp,
    updated_at            TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS interest
(
    id       SERIAL PRIMARY KEY,
    interest VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS profile_interest
(
    profile_id  BIGINT NOT NULL REFERENCES profile (id),
    interest_id BIGINT NOT NULL REFERENCES interest (id),
    PRIMARY KEY (profile_id, interest_id)
);

CREATE TABLE IF NOT EXISTS profile_events
(
    profile_id BIGINT NOT NULL REFERENCES profile (id),
    event_id   BIGINT NOT NULL REFERENCES events (id),
    PRIMARY KEY (profile_id, event_id)
);

CREATE TABLE IF NOT EXISTS images
(
    id                    SERIAL PRIMARY KEY,
    maintainer_profile_id BIGINT NOT NULL REFERENCES profile (id),
    title                 VARCHAR(100),
    description           TEXT,
    image                 VARCHAR(300)
);

CREATE TABLE IF NOT EXISTS profile_images
(
    profile_id BIGINT NOT NULL REFERENCES profile (id),
    image_id   BIGINT NOT NULL REFERENCES images (id),
    PRIMARY KEY (profile_id, image_id)
);

CREATE TABLE IF NOT EXISTS dreams
(
    id          SERIAL PRIMARY KEY,
    annotation  VARCHAR(255),
    description TEXT,
    profile_id  BIGINT NOT NULL REFERENCES profile (id),
    created_at  TIMESTAMP DEFAULT current_timestamp,
    updated_at  TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS tags
(
    id   SERIAL PRIMARY KEY,
    tags VARCHAR(100) UNIQUE
);

CREATE TABLE IF NOT EXISTS dreams_tags
(
    dream_id BIGINT NOT NULL REFERENCES dreams (id),
    tag_id   BIGINT NOT NULL REFERENCES tags (id),
    PRIMARY KEY (dream_id, tag_id)
);

CREATE TABLE IF NOT EXISTS profile_tags
(
    profile_id BIGINT NOT NULL REFERENCES profile (id),
    tag_id     BIGINT NOT NULL REFERENCES tags (id),
    PRIMARY KEY (profile_id, tag_id)
);