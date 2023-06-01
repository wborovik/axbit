package com.example.axbit.service;

import com.example.axbit.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthor();
    Author getAuthorById(Long id);
    void createAuthor(Author author);
    void updateAuthorById(Long id, Author author);
    void deleteAuthorById(Long id);
}
