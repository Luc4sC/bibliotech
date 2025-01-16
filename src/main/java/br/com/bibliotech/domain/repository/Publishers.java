package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Publishers {

    void save(Publisher publisher);
    void update(Publisher publisher);
    void delete(Publisher publisher);
    Publisher findById(Long id);
    Page<Publisher> findAll(Pageable pageable);
    Publisher findByTradeName(String tradeName);

}
