package br.com.bibliotech.dtos;

import br.com.bibliotech.entities.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GenreDTO(@NotEmpty @Size(min = 3, max = 50) String name) {

    public GenreDTO(Genre genre){
        this(genre.getName());
    }
}
