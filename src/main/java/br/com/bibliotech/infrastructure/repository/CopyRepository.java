package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}
