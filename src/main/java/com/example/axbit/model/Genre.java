package com.example.axbit.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@Entity(name = "genres")
public class Genre extends AbstractEntity {
    @Column
    private String description;
}
