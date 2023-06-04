package com.example.axbit.dto;

import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String patronymic;
    @Past
    @NotBlank
    private LocalDate DateOfBirth;
}
