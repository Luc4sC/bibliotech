package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Author;

import java.util.List;

public interface Authors {

    void save(Author author);
    void update(Author author);
    void delete(Author author);
    Author findById(Long id);
    List<Author> findAll();
    Author findByStageName(String stageName);
}
