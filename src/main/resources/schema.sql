create schema if not exists netology;

CREATE TABLE IF NOT EXISTS netology.users
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(120),
    password VARCHAR(120),
    role VARCHAR(40)
);
