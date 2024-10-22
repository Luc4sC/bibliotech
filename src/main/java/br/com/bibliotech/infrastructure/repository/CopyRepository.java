package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

interface CopyRepository extends JpaRepository<Category, Long> {
}
