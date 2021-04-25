CREATE TABLE users
(
    id       int          NOT NULL AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    surname  varchar(255) NOT NULL,
    email    varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   int         NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);


-- Insert data
INSERT INTO users (name, surname, email, password)
VALUES ('Aleksandr', 'Ivanov', 'alex@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai'),
       ('Oleg', 'Ivanov', 'oleg@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai'),
       ('Aleksandr', 'Gorbunov', 'alex_gorbun@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai'),
       ('Nina', 'Sidorova', 'nina@mail.ru', '$2a$10$r9oj99L0CVzw7a0aT89bNehfXkv8KvxI7wDQ25tqaDnt0yVDrj2ai');

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_BUYER');

INSERT INTO user_roles
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 3);


CREATE TABLE cars
(
    id                  int NOT NULL AUTO_INCREMENT,
    vin                 varchar(20) UNIQUE,
    brand               varchar(50),
    model               varchar(50),
    year_of_manufacture int(4),
    price               int,
    mileage             int,
    selling_price       int,
    sold                tinyint(1),
    PRIMARY KEY (id)
);


INSERT INTO cars (vin, brand, model, year_of_manufacture, price, mileage,sold)
VALUES ('XWBJA69VJDA033200', 'Chevrolet', 'Cruse', '2014', 400000, 97000,0),
       ('WVWZZZ3AZSN123456', 'Volkswagen', 'Polo', '2015', 450000, 70000,0),
       ('XWBJA69VJDA001011', 'Chevrolet', 'Lacetti', '2012', 290000, 120000,1);

