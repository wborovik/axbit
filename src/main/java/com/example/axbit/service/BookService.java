package com.example.axbit.service;

import com.example.axbit.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService extends AbstractService<Book> {
    List<Book> getAllBooksByAuthorId(Long authorId);

    void createBook(Long authorId, Book book);

    void updateBookById(Long id, Book book);
}
