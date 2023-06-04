package com.example.axbit.exception;

import lombok.NoArgsConstructor;

public class EntityNotCreateException extends RuntimeException {
    public EntityNotCreateException(String message) {
        super(message);
    }
}
