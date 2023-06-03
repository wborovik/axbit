package com.example.axbit.controller;

import com.example.axbit.model.Genre;
import com.example.axbit.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController extends AbstractControllerImpl<Genre, GenreService> {
    private final GenreService genreService;
    public GenreController(GenreService service, GenreService genreService) {
        super(service);
        this.genreService = genreService;
    }

    @Override
    @GetMapping("/genre/{id}")
    public ResponseEntity<Genre> getEntityById(@PathVariable Long id) {
        return super.getEntityById(id);
    }

    @Override
    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getAllEntity() {
        return super.getAllEntity();
    }

    @Override
    @DeleteMapping("/genre/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id) {
        return super.deleteEntityById(id);
    }

    @PostMapping("/genre/create")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        genreService.createGenre(genre);
        return new ResponseEntity<>(genre, HttpStatus.CREATED);
    }

    @PutMapping("/genre/update/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.genreService.updateGenreById(id, genre);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }
}
