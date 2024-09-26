package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
