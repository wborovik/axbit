package com.example.axbit.controller;

import com.example.axbit.exception.EntityNotFoundException;
import com.example.axbit.model.AbstractEntity;
import com.example.axbit.service.AbstractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractControllerImpl<T extends AbstractEntity, S extends AbstractService<T>>
        implements AbstractController<T> {
    private final S service;
    private static final Logger LOGGER = LogManager.getLogger(AbstractService.class);

    public AbstractControllerImpl(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<T>> getAllEntity() {
            List<T> entities = new ArrayList<>(service.getAllEntity());

            if (entities.isEmpty()) {
                LOGGER.debug("Entities exist db");
                throw new EntityNotFoundException("Entities not found");
            }
            LOGGER.debug("Entities retrieved from db");
            return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T> getEntityById(@PathVariable Long id) {
        T entity = service.getEntityById(id);
        if (entity == null) {
            LOGGER.debug("Entity with id " + id + " exist db");
            throw new EntityNotFoundException("Entity id not found: " + id);
        }
        LOGGER.debug("Entity with id " + id + " retrieved from db");
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id) {
        service.deleteEntityById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
