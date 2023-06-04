package com.example.axbit.controller;

import com.example.axbit.dto.BookDto;
import com.example.axbit.exception.NotCreateOrUpdateException;
import com.example.axbit.exception.EntityNotFoundException;
import com.example.axbit.model.Book;
import com.example.axbit.service.AuthorService;
import com.example.axbit.service.BookService;
import com.example.axbit.service.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final AuthorService authorService;
    private final GenreService genreService;
    private final ModelMapper modelMapper;
    private final Logger logger = LogManager.getLogger(BookService.class);

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService, ModelMapper modelMapper) {
        super(bookService);
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> entities = bookService.getAllEntity().stream()
                .map(post -> modelMapper.map(post, BookDto.class)).collect(Collectors.toList());
        if (entities.isEmpty()) {
            logger.debug("Books exist db");
            throw new EntityNotFoundException("Books not found");
        }
        logger.debug("Books retrieved from db");
        return new ResponseEntity<>(entities, HttpStatus.OK);
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
        logger.debug("Input Author id: " + authorId);
        List<Book> books = new ArrayList<>(bookService.getAllBooksByAuthorId(authorId));
        if (books.isEmpty()) {
            logger.debug("Books for Author with id: " + authorId + " exist db");
            throw new EntityNotFoundException("Books not found");
        }
        logger.debug("Books for Author with id " + authorId + " retrieved from db");
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/book/create/{authorId}/{genreId}")
    public ResponseEntity<Book> createBookByAuthorAndGenre(@RequestBody Book book, @PathVariable Long authorId,
                                                           @PathVariable Long genreId) {
        try {
            logger.debug("Input Book title: " + book.getBookTitle() + ", authorId: " + authorId + ", genreId: " + genreId);
            if (authorService.getEntityById(authorId) == null || genreService.getEntityById(genreId) == null) {
                throw new Exception();
            }
            bookService.createBookByAuthorAndGenre(authorId, genreId, book);
            logger.debug("Book created id: " + book.getId() + "title: " + book.getBookTitle() + " ISBN: " + book.getISBN());
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.debug("Book not created", e);
            throw new NotCreateOrUpdateException("Book not created");
        }
    }

    @PatchMapping("/book/update/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book) {
        try {
            logger.debug("Update book id: " + id + " new title: " + book.getBookTitle());
            this.bookService.updateBookById(id, book);
            logger.debug("Book update id: " + book.getId() + "new title: " + book.getBookTitle());
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("Book not update", e);
            throw new NotCreateOrUpdateException("Book not update");
        }
    }
}
