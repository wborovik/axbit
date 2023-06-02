package com.example.axbit.controller;

import com.example.axbit.model.AbstractEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AbstractController<T extends AbstractEntity> {
    ResponseEntity<List<? extends AbstractEntity>> getAllEntity();
    ResponseEntity<AbstractEntity> getEntityById(@PathVariable Long id);
    ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id);
}
