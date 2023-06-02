package com.example.axbit.service;

import com.example.axbit.model.AbstractEntity;
import com.example.axbit.repository.AbstractRepository;
import org.springframework.stereotype.Service;


public abstract class AbstractServiceImpl<REP extends AbstractRepository<? extends AbstractEntity>> {
    private final REP repository;

    public AbstractServiceImpl(REP repository) {
        this.repository = repository;
    }

}
