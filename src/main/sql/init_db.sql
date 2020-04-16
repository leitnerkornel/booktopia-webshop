DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS recommender;
DROP TABLE IF EXISTS cart;

CREATE TABLE genre
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE recommender
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE author
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE book
(
    id             SERIAL PRIMARY KEY,
    author_id      INTEGER REFERENCES author (id),
    genre_id       INTEGER REFERENCES genre (id),
    recommender_id INTEGER REFERENCES recommender (id),
    title          VARCHAR(255) NOT NULL,
    description    VARCHAR(2000),
    price          FLOAT,
    stock          INTEGER DEFAULT 1
);

CREATE TABLE cart
(
    id SERIAL PRIMARY KEY,
    book_id INTEGER REFERENCES book (id),
    quantity INTEGER DEFAULT 1
);