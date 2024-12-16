package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Book;

import java.util.List;

public interface Books {

    void save(Book book);
    void update(Book book);
    void delete(Book book);
    Book findById(Long id);
    List<Book> findAll();
    Book findByIsbn(String isbn);

}
