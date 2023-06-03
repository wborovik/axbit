package com.example.axbit.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
@Entity(name = "authors")
public class Author extends AbstractEntity {
    @NonNull
    private String name;

    @NonNull
    private String surname;

    private String patronymic;

    @Past
    private LocalDate DateOfBirth;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books;
}
