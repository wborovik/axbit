package com.example.axbit.service;

import com.example.axbit.model.Author;

public interface AuthorService extends AbstractService<Author> {
    void createAuthor(Author author);
    void updateAuthorById(Long id, Author author);
}
