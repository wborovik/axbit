package com.example.axbit.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.ISBN;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookDto {
    private Long id;
    @NotBlank
    @ISBN
    private String isbn;
    @NotBlank
    private Long AuthorId;
    @NotBlank
    private Long GenreId;
}
