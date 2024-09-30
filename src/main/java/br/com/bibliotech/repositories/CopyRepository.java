package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}
