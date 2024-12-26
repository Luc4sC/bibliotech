package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Authors {

    void save(Author author);
    void update(Author author);
    void delete(Author author);
    Author findById(Long id);
    Page<Author> findAll(Pageable pageable);
    Author findByStageName(String stageName);
}
