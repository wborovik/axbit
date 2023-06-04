package com.example.axbit.controller;

import com.example.axbit.dto.AuthorDto;
import com.example.axbit.exception.EntityNotFoundException;
import com.example.axbit.exception.NotCreateOrUpdateException;
import com.example.axbit.model.Author;
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
        List<AuthorDto> entities = authorService.getAllEntity().stream()
                .map(post -> modelMapper.map(post, AuthorDto.class))
                .collect(Collectors.toList());
        if (entities.isEmpty()) {
            LOGGER.debug("Author exist db");
            throw new EntityNotFoundException("Author not found");
        }
        LOGGER.debug("Author retrieved from db");
        return new ResponseEntity<>(entities, HttpStatus.OK);
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
            LOGGER.debug("Input Author name: " + author.getName() + " surname: " + author.getSurname() +
                    " patronymic: " + author.getPatronymic() + " birth date: " + author.getDateOfBirth());
            authorService.createAuthor(author);
            LOGGER.debug("Author created id: " + author.getId());
            return new ResponseEntity<>(author, HttpStatus.CREATED);
        } catch (Exception ex) {
            LOGGER.debug("Author is not created");
            throw new NotCreateOrUpdateException("Author not created");
        }
    }

    @PatchMapping("/author/update/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable Long id, @RequestBody Author author) {
        try {
            LOGGER.debug("Update author id: " + id + " new name: " + author.getName() + " new surname: " + author.getName()
                    + " new patronymic: " + author.getPatronymic() + " new birth date: " + author.getDateOfBirth());

            this.authorService.updateAuthorById(id, author);
            LOGGER.debug("Author update id: " + author.getId());
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.debug("Author not update");
            throw new NotCreateOrUpdateException("Author not update");
        }
    }
}
