package com.example.axbit.controller;

import com.example.axbit.model.Author;
import com.example.axbit.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        try {
            List<Author> authors = new ArrayList<>(authorService.getAllAuthor());

            if (authors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/author/create")
    public ResponseEntity<Author> createUser(@RequestBody Author author) {
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        authorService.createAuthor(author);

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PutMapping("/author/update/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable Long id, @RequestBody Author author) {
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.authorService.updateAuthorById(id, author);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("/author/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
