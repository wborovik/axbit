package com.example.axbit.controller;

import com.example.axbit.exception.EntityNotFoundException;
import com.example.axbit.exception.NotDeleteException;
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
    private final Logger logger = LogManager.getLogger(AbstractService.class);

    public AbstractControllerImpl(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<T>> getAllEntity() {
        List<T> entities = new ArrayList<>(service.getAllEntity());
        if (entities.isEmpty()) {
            logger.debug("Entities exist db");
            throw new EntityNotFoundException("Entities not found");
        }
        logger.debug("Entities retrieved from db");
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T> getEntityById(@PathVariable Long id) {
        logger.debug("Input id: " + id);
        T entity = service.getEntityById(id);
        if (entity == null) {
            logger.debug("Entity with id " + id + " exist db");
            throw new EntityNotFoundException("Entity id not found: " + id);
        }
        logger.debug("Entity with id " + id + " retrieved from db");
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEntityById(@PathVariable Long id) {
        logger.debug("Input delete Entity id: " + id);
        AbstractEntity entity = service.getEntityById(id);
        logger.debug("Retrieved from db: " + entity);
        if (entity == null) {
            logger.debug("Entity not deleted");
            throw new NotDeleteException("Entity not found and not deleted");
        }
        service.deleteEntityById(id);
        logger.debug("Entity deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
