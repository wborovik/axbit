package com.example.axbit.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@Entity(name = "authors")
public class Author extends AbstractEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String patronymic;
    @Column(nullable = false)
    private LocalDate DateOfBirth;
    @OneToMany (mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books;
}
