package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
