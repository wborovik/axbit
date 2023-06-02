package com.example.axbit.controller;

import com.example.axbit.model.AbstractEntity;
import com.example.axbit.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractControllerImpl<T extends AbstractEntity, S extends AbstractService<T>>
        implements AbstractController<T> {
    private final S service;

    public AbstractControllerImpl(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<? extends AbstractEntity>> getAllEntity() {
        try {
            List<? extends AbstractEntity> entities = new ArrayList<>(service.getAllEntity());

            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<AbstractEntity> getEntityById(@PathVariable Long id) {
        AbstractEntity author = service.getEntityById(id);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id) {
        service.deleteEntityById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
