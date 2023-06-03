package com.example.axbit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
//@ToString
@NoArgsConstructor
@Entity(name = "books")
public class Book extends AbstractEntity {
    private String bookTitle;

    @NotNull
    @ISBN
    private String ISBN;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonIgnore
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    Author author;
}
