package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByTradeName(String tradeName);

}
