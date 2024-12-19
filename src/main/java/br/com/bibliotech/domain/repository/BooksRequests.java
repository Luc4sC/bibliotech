package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.BookLoanRequest;

public interface BooksRequests {

    void save(BookLoanRequest bookLoanRequest);
}
