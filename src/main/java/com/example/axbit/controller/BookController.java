package com.example.axbit.controller;

import com.example.axbit.dto.BookDto;
import com.example.axbit.model.Book;
import com.example.axbit.repository.AuthorRepository;
import com.example.axbit.repository.GenreRepository;
import com.example.axbit.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController extends AbstractControllerImpl<Book, BookService> {
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService, AuthorRepository authorRepository, GenreRepository genreRepository,
                          ModelMapper modelMapper) {
        super(bookService);
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        try {
            List<BookDto> entities = bookService.getAllEntity().stream().map(post -> modelMapper.map(post, BookDto.class))
                    .collect(Collectors.toList());

            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("book/{id}")
    public ResponseEntity<Book> getEntityById(@PathVariable Long id) {
        return super.getEntityById(id);
    }

    @Override
    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id) {
        return super.deleteEntityById(id);
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

    @PostMapping("/book/create/{authorId}/{genreId}")
    public ResponseEntity<Book> createBookByAuthorAndGenre(@RequestBody Book book, @PathVariable Long authorId, @PathVariable Long genreId) {
        if (book == null || authorRepository.findById(authorId).isEmpty() || genreRepository.findById(genreId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.createBookByAuthorAndGenre(authorId, genreId, book);

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
}
