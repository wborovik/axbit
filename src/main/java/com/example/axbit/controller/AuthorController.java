package com.example.axbit.controller;

import com.example.axbit.model.AbstractEntity;
import com.example.axbit.model.Author;
import com.example.axbit.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController extends AbstractControllerImpl<Author, AuthorService> {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        super(authorService);
        this.authorService = authorService;
    }

    @Override
    @GetMapping("/authors")
    public ResponseEntity<List<? extends AbstractEntity>> getAllEntity() {
        return super.getAllEntity();
    }

    @Override
    @GetMapping("author/{id}")
    public ResponseEntity<AbstractEntity> getEntityById(@PathVariable Long id) {
        return super.getEntityById(id);
    }

    @Override
    @DeleteMapping("/author/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id) {
        return super.deleteEntityById(id);
    }

    @PostMapping("/author/create")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        authorService.createAuthor(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PatchMapping("/author/update/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable Long id, @RequestBody Author author) {
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.authorService.updateAuthorById(id, author);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
