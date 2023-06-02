package com.example.axbit.service;

import com.example.axbit.model.AbstractEntity;
import com.example.axbit.repository.AbstractRepository;

import java.util.List;

public abstract class AbstractServiceImpl<T extends AbstractEntity, REP extends AbstractRepository<T>>
        implements AbstractService<T> {
    private final REP repository;

    AbstractServiceImpl(REP repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAllEntity() {
        return repository.findAll();
    }

    @Override
    public T getEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteEntityById(Long id) {
        repository.deleteById(id);
    }
}
