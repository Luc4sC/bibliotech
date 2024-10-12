package br.com.bibliotech.domain.repository;

import java.util.List;

interface Generics<T> {
    void save(T object);
    void delete(T object);
    void update(T object, T update);
    T findById(Long id);
    List<T> findAll();
}
