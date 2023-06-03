package com.example.axbit.service;

import com.example.axbit.model.Genre;
import com.example.axbit.repository.GenreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class GenreServiceImpl extends AbstractServiceImpl<Genre, GenreRepository> implements GenreService {

    private final GenreRepository genreRepository;

    GenreServiceImpl(GenreRepository repository, GenreRepository genreRepository) {
        super(repository);
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAllEntity() {
        return super.getAllEntity();
    }

    @Override
    public Genre getEntityById(Long id) {
        return super.getEntityById(id);
    }

    @Override
    public void deleteEntityById(Long id) {
        super.deleteEntityById(id);
    }

    @Override
    public void createGenre(Genre genre) {
        genre.setCreationDate(LocalDate.now());
        genre.setModificationDate(LocalDate.now());
        genreRepository.save(genre);
    }

    @Override
    public void updateGenreById(Long id, Genre genre) {
        Genre genreUpdate = getEntityById(id);
        genreUpdate.setDescription(genre.getDescription());
        genreUpdate.setModificationDate(LocalDate.now());
        genreRepository.save(genreUpdate);
    }
}
