package com.example.axbit.service;

import com.example.axbit.model.Author;
import com.example.axbit.model.Book;
import com.example.axbit.model.Genre;
import com.example.axbit.repository.BookRepository;
import com.example.axbit.util.IsbnGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl extends AbstractServiceImpl<Book, BookRepository> implements BookService {
    private final BookRepository bookRepository;
    private final AuthorServiceImpl authorService;
    private final GenreService genreService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorServiceImpl authorService, GenreService genreService) {
        super(bookRepository);
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public List<Book> getAllEntity() {
        return super.getAllEntity();
    }

    @Override
    public Book getEntityById(Long id) {
        return super.getEntityById(id);
    }

    @Override
    public void deleteEntityById(Long id) {
        super.deleteEntityById(id);
    }

    @Override
    public List<Book> getAllBooksByAuthorId(Long authorId) {
        List<Book> list = getAllEntity();
        return list.stream().filter(u -> u.getAuthor().getId().equals(authorId)).toList();
    }

    @Override
    public void createBookByAuthorAndGenre(Long authorId, Long genreId, Book book) {
        book.setCreationDate(LocalDate.now());
        book.setISBN(IsbnGenerator.isbnGenerator());
        book.setAuthor(authorService.getEntityById(authorId));
        book.setGenre(genreService.getEntityById(genreId));
        bookRepository.save(book);
    }

    @Override
    public void updateBookById(Long id, Book book) {
        Book bookUpdate = getEntityById(id);
        bookUpdate.setBookTitle(book.getBookTitle());
        bookUpdate.setGenre(bookUpdate.getGenre());
        bookUpdate.setModificationDate(LocalDate.now());
        bookRepository.save(bookUpdate);
    }
}
