package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Genre;

import java.util.List;

public interface Genres {

    void save(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
    Genre findById(Long id);
    List<Genre> findAll();
    Genre findByName(String name);
}
