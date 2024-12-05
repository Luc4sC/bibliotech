package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

interface RequestRepository extends JpaRepository<Request, Long> {
}
