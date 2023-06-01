package com.example.axbit.controller;

import com.example.axbit.model.Book;
import com.example.axbit.service.AuthorService;
import com.example.axbit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = new ArrayList<>(bookService.getAllBooks());

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/author/books/{authorId}")
    public ResponseEntity<List<Book>> getAllBooksByAuthorId(@PathVariable Long authorId) {
        try {
            List<Book> books = new ArrayList<>(bookService.getAllBooksByAuthorId(authorId));

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/book/create/{authorId}")
    public ResponseEntity<Book> createBook(@RequestBody Book book, @PathVariable Long authorId) {
        if (book == null || authorService.getAuthorById(authorId) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.createBook(authorId, book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PatchMapping("/book/update/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book) {
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.bookService.updateBookById(id, book);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
