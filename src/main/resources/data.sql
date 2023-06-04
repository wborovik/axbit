INSERT IGNORE INTO authors (id, name, surname, patronymic, date_of_birth, creation_date, modification_date)
VALUES (1, 'Ivan', 'Petrov', 'Vasilevich', '1995-12-22 +03', CURDATE(), NULL);

INSERT IGNORE INTO genres (id, description, creation_date, modification_date)
    VALUE (1, 'fantastic', CURDATE(), NULL);

INSERT IGNORE INTO books (id, book_title, isbn, genre_id, author_id, creation_date, modification_date)
VALUES (1, 'Treasure Island', '978-5-01-834511-4', 1, 1, CURDATE(), NULL);

COMMIT;