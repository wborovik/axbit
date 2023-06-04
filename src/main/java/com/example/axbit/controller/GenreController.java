package com.example.axbit.controller;

import com.example.axbit.exception.NotCreateOrUpdateException;
import com.example.axbit.model.Genre;
import com.example.axbit.service.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController extends AbstractControllerImpl<Genre, GenreService> {
    private final GenreService genreService;
    private final Logger logger = LogManager.getLogger(GenreService.class);

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
        try {
            logger.debug("Input Genre description: " + genre.getDescription());
            genreService.createGenre(genre);
            logger.debug("Genre created id: " + genre.getId() + " description: " + genre.getDescription());
            return new ResponseEntity<>(genre, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.debug("Genre not created", e);
            throw new NotCreateOrUpdateException("Genre not created");
        }
    }

    @PutMapping("/genre/update/{id}")
    public ResponseEntity<Genre> updateGenreById(@PathVariable Long id, @RequestBody Genre genre) {
        try {
            logger.debug("Update genre id: " + id + " new description: " + genre.getDescription());
            this.genreService.updateGenreById(id, genre);
            logger.debug("Genre update id: " + genre.getId() + " new description: " + genre.getDescription());
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("Genre not update", e);
            throw new NotCreateOrUpdateException("Genre not update");
        }
    }
}
