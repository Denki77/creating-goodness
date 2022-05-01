INSERT INTO users (username, password, email)
VALUES ('admin', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'admin@mail.ru'),
       ('sponsor', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'sponsor@mail.ru'),
       ('volunteer', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'volunteer@mail.ru'),
       ('orphanage', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'orphanage@mail.ru'),
       ('pupil', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'pupil@mail.ru'),
       ('user4', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'user4@mail.ru'); -- pass: tttttt

INSERT INTO roles (code, names)
VALUES ('admin', 'Администратор'),
       ('sponsor', 'Спонсор'),
       ('volunteer', 'Волонтёр'),
       ('orphanage', 'Официальный представитель детского дома'),
       ('pupil', 'Воспитанник');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 5);

INSERT INTO cities (names, lat, lng)
VALUES ('Алейск', '51.9581028', '85.9603235'),
       ('Белокуриха', '51.9581028', '85.9603235'),
       ('Барнаул', '51.9581028', '85.9603235'),
       ('Змеиногорск', '51.9581028', '85.9603235'),
       ('Славгород', '51.9581028', '85.9603235'),
       ('Новоалтайск', '51.9581028', '85.9603235'),
       ('Завитинск', '51.9581028', '85.9603235'),
       ('Зея', '51.9581028', '85.9603235'),
       ('Онега', '51.9581028', '85.9603235'),
       ('Шенкурск', '51.9581028', '85.9603235'),
       ('Ахтубинск', '51.9581028', '85.9603235');

INSERT INTO shelters (names, number, city_id)
VALUES ('Детский дом № 15', '15', '1'),
       ('Детский дом № 14', '14', '2'),
       ('Детский дом № 16', '16', '2'),
       ('Детский дом № 17', '17', '3'),
       ('Детский дом № 18', '18', '4'),
       ('Детский дом № 19', '19', '4'),
       ('Детский дом № 20', '20', '7');

INSERT INTO profile (user_id, firstname, lastname, description, shelter_id)
VALUES (1, 'Иван', 'admin', 'Вот такой вот admin', 1),
       (2, 'Иван', 'sponsor', 'Вот такой вот sponsor', 1),
       (3, 'Иван', 'volunteer', 'Вот такой вот volunteer', 4),
       (4, 'Иван', 'orphanage', 'Вот такой вот orphanage', 4),
       (5, 'Иван', 'pupil', 'Вот такой вот pupil', 2),
       (6, 'Петров', 'pupil', 'Всем привет pupil', 2);

INSERT INTO events (names, description, status, start_date, count_days, maintainer_profile_id)
VALUES ('Интересное событие', 'Очень интересное событие которое будет проводиться.', 1, '2021-09-26 10:00:00.000000', 1,
        1),
       ('Невероятно интересное событие', 'Проводится невероятно интересное мероприятие.', 2,
        '2021-09-26 11:00:00.000000', 2, 2);

INSERT INTO tags (tags)
VALUES ('Спорт'),
       ('Музыка'),
       ('Кино'),
       ('Театр');

INSERT INTO dreams (description, annotation, profile_id)
VALUES ('Хочу попасть на матч', 'Хочу попасть на матч', 5),
       ('Хочу попасть на концерт', 'Хочу попасть на концерт Metallica', 6);

INSERT INTO dreams_tags (dream_id, tag_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO profile_tags (profile_id, tag_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO profile_events (profile_id, event_id)
VALUES (1, 1),
       (2, 2);