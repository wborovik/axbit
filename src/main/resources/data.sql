INSERT INTO authors (name, surname, patronymic, date_of_birth, date_of_creation)
VALUES ('Ivan', 'Petrov', 'Vasilevich', '1995-12-22 +03', '2022-01-10 +03');

INSERT INTO books (isbn, genre, author_id)
VALUES ('978-5-01-834511-4', 'fantasy', 1);

COMMIT;