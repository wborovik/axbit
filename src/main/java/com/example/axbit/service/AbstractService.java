package com.example.axbit.service;

import com.example.axbit.model.AbstractEntity;

import java.util.List;

public interface AbstractService<T extends AbstractEntity> {
    List<T> getAllEntity();

    T getEntityById(Long id);

    void deleteEntityById(Long id);
}
