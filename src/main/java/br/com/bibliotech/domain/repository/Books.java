package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Books {

    void save(Book book);
    void update(Book book);
    void delete(Book book);
    Book findById(Long id);
    Page<Book> findAll(Pageable pageable);
    Book findByIsbn(String isbn);
    Page<Book> findByLoanRequestId(Long loanRequestId, Pageable pageable);

}
