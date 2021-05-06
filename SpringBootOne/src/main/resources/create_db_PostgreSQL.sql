CREATE TABLE users
(
    id       serial       NOT NULL,
    name     varchar(255) NOT NULL,
    surname  varchar(255),
    email    varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   serial      NOT NULL,
    name varchar(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id serial NOT NULL,
    role_id serial NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);


-- Insert data
INSERT INTO users (name, surname, email, password)
VALUES ('Aleksandr', 'Ivanov', 'alex@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai'),
       ('Oleg', 'Ivanov', 'oleg@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai'),
       ('Aleksandr', 'Gorbunov', 'alex_gorbun@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai'),
       ('Nina', 'Sidorova', 'nina@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai'),
       ('Boris', 'Makarov', 'dark@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai');

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_BUYER');

INSERT INTO user_roles
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 3),
       (5, 1);

CREATE TABLE cars
(
    id                  serial NOT NULL,
    vin                 varchar(20) UNIQUE,
    brand               varchar(50),
    model               varchar(50),
    year_of_manufacture smallint,
    price               int,
    mileage             int,
    selling_price       int,
    sold                boolean,
    PRIMARY KEY (id)
);


INSERT INTO cars (vin, brand, model, year_of_manufacture, price, mileage, sold)
VALUES ('XWBJA69VJDA033200', 'Chevrolet', 'Cruse', '2014', 400000, 97000, false),
       ('XWBJA69VJDA001011', 'Chevrolet', 'Lacetti', '2012', 290000, 120000, true),
       ('XWBJA69VJDA101022', 'Kia', 'Optima', '2018', 1290000, 120000, true),
       ('ZXCVBN3AZSN123459', 'Lada', 'Vesta', '2020', 700000, 1000, false),
       ('WVWZZZ3AZSN123456', 'Volkswagen', 'Polo', '2015', 450000, 70000, false),
       ('WVWZZZ3AZSN123457', 'Volkswagen', 'Polo', '2010', 430000, 66000, false),
       ('WVWZZZ3AZSN123458', 'Volkswagen', 'Tiguan', '2020', 1430000, 50000, false),
       ('WVWZZZ3AZSN123459', 'Volkswagen', 'Tiguan', '2021', 2100000, 0, false),
       ('WVWZZZ3AZSN123460', 'Volkswagen', 'Jetta', '2021', 1300000, 0, false);

