package br.com.bibliotech.domain.repository;

import java.util.List;

interface Generics<T> {

    void save(T model);
    void update(T model, T update);
    void delete(T model);
    T findById(T model);
    List<T> findAll();

}
