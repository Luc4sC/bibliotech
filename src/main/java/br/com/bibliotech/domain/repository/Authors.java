package br.com.bibliotech.domain.repository;

import br.com.bibliotech.application.dto.AuthorDTO;
import br.com.bibliotech.domain.model.Author;

public interface Authors extends GenericX<Author> {

    void update(Author author, AuthorDTO authorDTO);
    Author findByStageName(String stageName);
}
