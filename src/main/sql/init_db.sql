DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS recommender;

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
    description    VARCHAR(2000)
);


INSERT INTO author (name)
VALUES ('Jean-Dominique Bauby'),
       ('Nick Cutter'),
       ('Kurt Vonnegut'),
       ('R. A. Salvatore'),
       ('Philip K. Dick'),
       ('Jo Nesbo'),
       ('J. K. Rowling'),
       ('Dan Brown'),
       ('Agatha Christie'),
       ('Jonas Jonasson'),
       ('Robert Merle'),
       ('Ilf-Petrov'),
       ('George Orwell'),
       ('Tom Sharpe'),
       ('Richard Scarry')
;

INSERT INTO book (author_id, title)
VALUES ((SELECT id FROM author WHERE name = 'Jean-Dominique Bauby'), 'The diving bell and the butterfly'),
       ((SELECT id FROM author WHERE name = 'Nick Cutter'), 'The troop')
;