package com.example.axbit.service;

import com.example.axbit.model.Genre;

public interface GenreService extends AbstractService<Genre> {
    void createGenre(Genre genre);
}
