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
VALUES ('John', 'Smith', 'demo@mail.ru', '$2a$10$QF0K7IGyIPwWktMYzebP7utSOAUd1BW4n2yGStnYIXpiBaTs2ofaW'),
       ('Alexei', 'Volkoff', 'alex@mail.ru', '$2a$10$NbAhhj9QL2rvHGcgdIvU3O4PIp20s6EXsRYYV.DJtUZYx6gHeFhpW'),
       ('Boris', 'Makarov', 'dark@mail.ru', '$2a$10$QslYjpOrodZWbUjXVgu1Yu6mM2CYBl3Z.A6zccmPaWPD100dWjJke'),
       ('Teimur', 'Dzhafarov', 'dz@gmail.com', '$2a$10$gCqXO5923/y9lGZYZ/Sk7OKh588l1q31QQRo.z7ZxfjnDm/Vnj5Mi'),
       ('Vlad', 'Svetlakov', 'vlad@mail.ru', '$2a$10$ntl0hCGZ/XI24gDqWm6IUecBb5P7a.6lBwBmtAXG37lkxmCxyaTg2'),
       ('Nina', 'Sidorova', 'nina@mail.ru', '$2a$10$tgv3J8sta2TuIRVYdBLdLu7VgJmSTWtAuSgEXJ1uu7aZ2zVRmI13S'),
       ('Denis', 'Abrashin', 'condor@mail.ru', '$2a$10$gCqXO5923/y9lGZYZ/Sk7OKh588l1q31QQRo.z7ZxfjnDm/Vnj5Mi');


INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_BUYER'),
       ('ROLE_DEMO');

INSERT INTO user_roles
VALUES (1, 4),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 2),
       (6, 3),
       (7, 1);

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
VALUES ('WVWZZZ3AZSN223456', 'Volkswagen', 'Polo', '1999', 150000, 70000, false),
       ('WVWZZZ3AZSN223457', 'Volkswagen', 'Jetta', '2001', 130000, 66000, false),
       ('WVWZZZ3AZSN223458', 'Volkswagen', 'Tiguan', '2019', 1430000, 30000, false),
       ('WVWZZZ3AZSN223459', 'Volkswagen', 'Tiguan', '2021', 2100000, 0, false),
       ('XWBJA69VJDA033200', 'Chevrolet', 'Cruse', '2014', 400000, 97000, false),
       ('XWBJA69VJDA001011', 'Chevrolet', 'Lacetti', '2012', 290000, 120000, true),
       ('XWBJA69VJDA101022', 'Kia', 'Optima', '2018', 1290000, 120000, true),
       ('ZXCVBN3AZSN123459', 'Lada', 'Vesta', '2020', 700000, 1000, false),
       ('WVWZZZ3AZSN123456', 'Volkswagen', 'Polo', '2015', 450000, 70000, false),
       ('WVWZZZ3AZSN123457', 'Volkswagen', 'Polo', '2010', 430000, 66000, false),
       ('WVWZZZ3AZSN123458', 'Volkswagen', 'Tiguan', '2020', 1430000, 50000, false),
       ('WVWZZZ3AZSN123459', 'Volkswagen', 'Tiguan', '2021', 2100000, 0, false),
       ('WVWZZZ3AZSN123460', 'Volkswagen', 'Jetta', '2021', 1300000, 0, false);

