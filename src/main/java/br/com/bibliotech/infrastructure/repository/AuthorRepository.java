package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

interface AuthorRepository extends JpaRepository<Author, Long> {
}
