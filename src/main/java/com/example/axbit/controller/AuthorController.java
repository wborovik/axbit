package com.example.axbit.controller;

import com.example.axbit.dto.AuthorDto;
import com.example.axbit.exception.EntityNotCreateException;
import com.example.axbit.model.Author;
import com.example.axbit.service.AbstractService;
import com.example.axbit.service.AuthorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController extends AbstractControllerImpl<Author, AuthorService> {
    private final AuthorService authorService;
    private final ModelMapper modelMapper;
    private static final Logger LOGGER = LogManager.getLogger(AuthorService.class);


    @Autowired
    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        super(authorService);
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDto>> getAllAuthor() {
        try {
            List<AuthorDto> entities = authorService.getAllEntity().stream()
                    .map(post -> modelMapper.map(post, AuthorDto.class))
                    .collect(Collectors.toList());

            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("author/{id}")
    public ResponseEntity<Author> getEntityById(@PathVariable Long id) {
        return super.getEntityById(id);
    }

    @Override
    @DeleteMapping("/author/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id) {
        return super.deleteEntityById(id);
    }

    @PostMapping("/author/create")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try {
            authorService.createAuthor(author);
            LOGGER.debug("Author created");
            return new ResponseEntity<>(author, HttpStatus.CREATED);
        } catch (Exception ex) {
            LOGGER.debug("Author is not created");
            throw new EntityNotCreateException("Author was not created");
        }
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
