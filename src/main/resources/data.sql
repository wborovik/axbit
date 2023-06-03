INSERT INTO authors (name, surname, patronymic, date_of_birth, creation_date, modification_date)
VALUES ('Ivan', 'Petrov', 'Vasilevich', '1995-12-22 +03', CURDATE(), CURDATE());

INSERT INTO genres (description, creation_date, modification_date)
    VALUE ('fantasy', CURDATE(), CURDATE());

INSERT INTO books (book_title, isbn, genre_id, author_id, creation_date, modification_date)
VALUES ('Ostrov Sokrovisch', '978-5-01-834511-4', 1, 1, CURDATE(), CURDATE());

COMMIT;