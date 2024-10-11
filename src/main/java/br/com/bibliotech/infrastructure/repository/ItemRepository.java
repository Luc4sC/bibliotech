package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
