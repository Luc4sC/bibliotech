package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Genres {

    void save(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
    Genre findById(Long id);
    Page<Genre> findAll(Pageable pageable);
    Genre findByName(String name);
}
