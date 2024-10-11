package br.com.bibliotech.domain.repository;

import java.util.List;

interface GenericX<T> {
    void save(T object);
    void delete(T object);
    T findById(Long id);
    List<T> findAll();
}
