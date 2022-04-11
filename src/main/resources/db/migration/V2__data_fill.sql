insert into cities (name, lat, lng)
values
    ('Алейск', '51.9581028', '85.9603235'),
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

insert into shelters (name, number, city_id)
values ('Детский дом № 15', '15', '1'),
       ('Детский дом № 14', '14', '2'),
       ('Детский дом № 16', '16', '2'),
       ('Детский дом № 17', '17', '3'),
       ('Детский дом № 18', '18', '4'),
       ('Детский дом № 19', '19', '4'),
       ('Детский дом № 20', '20', '7');

insert into users (username, password, email, shelter_id)
values ('admin', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'admin@mail.ru', 1),
       ('sponsor', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'sponsor@mail.ru', 2),
       ('volunteer', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'volunteer@mail.ru', 2),
       ('orphanage', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'orphanage@mail.ru', 1),
       ('pupil', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'pupil@mail.ru', 2),
       ('user4', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'user4@mail.ru', 2); -- pass: tttttt

insert into profile (user_id, firstname, lastname, comment)
values (1, 'Иван', 'admin', 'Вот такой вот admin'),
       (2, 'Иван', 'sponsor', 'Вот такой вот sponsor'),
       (3, 'Иван', 'volunteer', 'Вот такой вот volunteer'),
       (4, 'Иван', 'orphanage', 'Вот такой вот orphanage'),
       (5, 'Иван', 'pupil', 'Вот такой вот pupil'),
       (6, 'Петров', 'pupil', 'Всем привет pupil');

INSERT INTO roles (code, name)
VALUES ('admin', 'Администратор'),
       ('sponsor', 'Спонсор'),
       ('volunteer', 'Волонтёр'),
       ('orphanage', 'Официальный представитель детского дома'),
       ('pupil', 'Воспитанник');

insert into events (name, comm, status, start_date, count_days, user_id)
values ('Интересное событие', 'Очень интересное событие которое будет проводиться.', 1, '2021-09-26 10:00:00.000000', 1,
        1),
       ('Невероятно интересное событие', 'Проводится невероятно интересное мероприятие.', 2,
        '2021-09-26 11:00:00.000000', 2, 2);

insert into tags (name)
values ('Спорт'),
       ('Музыка'),
       ('Кино'),
       ('Театр');

insert into dreams (description, annotation, user_id)
values ('Хочу попасть на матч', 'Хочу попасть на матч', 5),
       ('Хочу попасть на концерт', 'Хочу попасть на концерт Metallica', 6);

insert into dreams_tags (dream_id, tag_id)
values (1, 1),
       (2, 2);

insert into users_dreams (dream_id, user_id)
values (1, 5),
       (2, 6);

insert into users_tags (user_id, tag_id)
values (1, 1),
       (2, 2);

insert into users_events (user_id, event_id)
values (1, 1),
       (2, 2);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 5);

insert into permissions
    (role_id, permission)
values (1, 'role_absolute'),
       (2, 'role_edit_profile'),
       (2, 'role_get_pupil_profile'),
       (3, 'role_edit_profile'),
       (3, 'role_get_pupil_profile'),
       (4, 'role_edit_profile'),
       (4, 'role_add_pupil_profile'),
       (4, 'role_get_pupil_profile'),
       (4, 'role_edit_pupil_profile');
