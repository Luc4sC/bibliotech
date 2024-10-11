package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
