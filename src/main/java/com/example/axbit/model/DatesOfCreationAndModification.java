package com.example.axbit.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@MappedSuperclass
public class DatesOfCreationAndModification {
    private LocalDate dateOfCreation;
    private LocalDate modificationDate;
}
