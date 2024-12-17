package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Publisher;

import java.util.List;

public interface Publishers {

    void save(Publisher publisher);
    void update(Publisher publisher);
    void delete(Publisher publisher);
    Publisher findById(Long id);
    List<Publisher> findAll();
    Publisher findByTradeName(String tradeName);

}
