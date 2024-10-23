package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Author;

public interface Authors extends Generics<Author> {

    Author findByStageName(String stageName);
}
