package br.com.bibliotech.dtos;

import br.com.bibliotech.entities.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GenreDTO(@NotNull @NotBlank String name) {

    public GenreDTO(Genre genre){
        this(genre.getName());
    }
}
