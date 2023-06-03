package com.example.axbit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AbstractController<T> {
    ResponseEntity<List<T>> getAllEntity();

    ResponseEntity<T> getEntityById(@PathVariable Long id);

    ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id);
}
