package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
