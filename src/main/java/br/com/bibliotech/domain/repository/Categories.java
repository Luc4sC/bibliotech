package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Category;

import java.util.List;

public interface Categories {

    void save(Category category);
    void update(Category category);
    void delete(Category category);
    Category findById(Long id);
    List<Category> findAll();
    Category findByName(String name);

}
