package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
