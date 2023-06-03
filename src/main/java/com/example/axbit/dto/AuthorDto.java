package com.example.axbit.dto;

import com.example.axbit.model.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorDto {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate DateOfBirth;
}
