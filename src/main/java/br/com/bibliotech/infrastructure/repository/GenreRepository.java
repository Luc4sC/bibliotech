package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);
}
