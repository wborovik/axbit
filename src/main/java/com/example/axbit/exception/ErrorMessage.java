package com.example.axbit.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private LocalDateTime timestamp;
    private int status;
    private String error;
}
