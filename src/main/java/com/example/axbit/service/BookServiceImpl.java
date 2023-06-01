package com.example.axbit.service;

import com.example.axbit.model.Author;
import com.example.axbit.model.Book;
import com.example.axbit.repository.BookRepository;
import com.example.axbit.setting.IsbnGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getAllBooksByAuthorId(Long authorId) {
        List<Book> list = getAllBooks();
        return list.stream().filter(u -> u.getAuthor().getId().equals(authorId)).toList();
    }

    @Override
    public void createBook(Long authorId, Book book) {
        Author author = authorService.getAuthorById(authorId);
        book.setDateOfCreation(LocalDate.now());
        book.setModificationDate(LocalDate.now());
        book.setISBN(IsbnGenerator.isbnGenerator());
        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Override
    public void updateBookById(Long id, Book book) {
        Book bookUpdate = getBookById(id);
        bookUpdate.setBookTitle(book.getBookTitle());
        bookUpdate.setGenre(book.getGenre());
        bookUpdate.setModificationDate(LocalDate.now());
        bookRepository.save(bookUpdate);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
