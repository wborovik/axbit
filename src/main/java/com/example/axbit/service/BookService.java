package com.example.axbit.service;

import com.example.axbit.model.Author;
import com.example.axbit.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    List<Book> getAllBooksByAuthorId(Long authorId);

    void createBook(Long authorId, Book book);

    void updateBookById(Long id, Book book);

    void deleteBookById(Long id);
}
