package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

}
