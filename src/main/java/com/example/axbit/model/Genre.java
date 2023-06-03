package com.example.axbit.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
//@ToString
@NoArgsConstructor
@Entity(name = "genres")
public class Genre extends AbstractEntity {
    @Column
    private String description;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<Book> books;
}
