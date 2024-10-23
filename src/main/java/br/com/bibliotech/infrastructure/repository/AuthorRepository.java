package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByStageName(String stageName);
}
