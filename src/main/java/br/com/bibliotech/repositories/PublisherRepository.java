package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
