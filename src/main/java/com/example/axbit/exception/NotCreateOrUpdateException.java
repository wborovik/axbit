package com.example.axbit.exception;

public class NotCreateOrUpdateException extends RuntimeException {
    public NotCreateOrUpdateException(String message) {
        super(message);
    }
}
