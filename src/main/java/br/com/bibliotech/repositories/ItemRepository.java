package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
