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
       ('Детский дом № 14', '14', '1'),
       ('Детский дом № 16', '16', '1'),
       ('Детский дом № 17', '17', '1'),
       ('Детский дом № 18', '18', '1'),
       ('Детский дом № 19', '19', '1'),
       ('Детский дом № 20', '20', '1');

insert into users (username, password, email, shelter_id)
values ('user3', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'user3@mail.ru', 1),
       ('user4', '$2a$10$EaI1HzFHEFxBT8p555sYFeFgnBFVrSQ07C0cnZkXzd8Txaw0b3JDK', 'user4@mail.ru', 1); -- pass: tttttt

insert into profile (user_id, firstname, lastname, comment)
values (1, 'Иван', 'Иванов', 'Вот такой вот персонаж'),
       (2, 'Петров', 'Петр', 'Всем привет');

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
        '2021-09-26 11:00:00.000000', 2, 1);

insert into tags (name)
values ('Спорт'),
       ('Музыка'),
       ('Кино'),
       ('Театр');

insert into dreams (description, annotation, user_id)
values ('Хочу попасть на матч', 'Хочу попасть на матч', 1),
       ('Хочу попасть на концерт', 'Хочу попасть на концерт Metallica', 2);

insert into dreams_tags (dream_id, tag_id)
values (1, 1),
       (2, 2);

insert into users_tags (user_id, tag_id)
values (1, 1),
       (2, 2);

insert into users_events (user_id, event_id)
values (1, 1),
       (2, 2);