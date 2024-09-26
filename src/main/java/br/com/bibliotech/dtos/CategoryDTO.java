package br.com.bibliotech.dtos;

import br.com.bibliotech.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDTO(@NotNull @NotBlank String name) {

    public CategoryDTO(Category category){
        this(category.getName());
    }
}
