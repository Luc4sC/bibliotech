package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.BookRequest;

public interface BooksRequests {

    void save(BookRequest bookRequest);
}
