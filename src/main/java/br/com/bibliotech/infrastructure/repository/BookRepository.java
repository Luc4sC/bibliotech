package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);
    @Query("SELECT b FROM Book b JOIN BookLoanRequest blr ON blr.book.id = b.id WHERE blr.loanRequest.id = :loanRequestId")
    Page<Book> findByLoanRequestId(@Param("loanRequestId") Long loanRequestId, Pageable pageable);
}
