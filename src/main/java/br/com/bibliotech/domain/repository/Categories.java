package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Category;

public interface Categories extends Generics<Category>{

    Category findByName(String name);
}
