package br.com.bibliotech.domain.repository;

import jakarta.transaction.Transactional;

import java.util.List;

interface Generics<T> {
    @Transactional
    void save(T object);

    @Transactional
    void delete(T object);

    @Transactional
    void update(T object, T update);

    T findById(Long id);

    List<T> findAll();
}
