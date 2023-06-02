package com.example.axbit.repository;

import com.example.axbit.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {
}