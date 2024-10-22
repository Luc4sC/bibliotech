package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

interface BookRepository extends JpaRepository<Book, Long> {
}
