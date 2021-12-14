CREATE TABLE IF NOT EXISTS users
(
    id       serial       NOT NULL,
    name     varchar(255) NOT NULL,
    surname  varchar(255),
    email    varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles
(
    id   serial      NOT NULL,
    name varchar(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id serial NOT NULL,
    role_id serial NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS cars
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

