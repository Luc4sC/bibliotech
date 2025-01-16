package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Categories {

    void save(Category category);
    void update(Category category);
    void delete(Category category);
    Category findById(Long id);
    Page<Category> findAll(Pageable pageable);
    Category findByName(String name);

}
