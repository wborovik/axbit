package com.example.axbit.dto;

import com.example.axbit.model.Genre;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookDto {
    private Long id;
    private String isbn;
    private Long AuthorId;
    private Long GenreId;
}
