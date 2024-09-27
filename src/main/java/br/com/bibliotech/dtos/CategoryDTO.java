package br.com.bibliotech.dtos;

import br.com.bibliotech.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryDTO(@NotEmpty @Size(min = 3, max = 50) String name) {

    public CategoryDTO(Category category){
        this(category.getName());
    }
}
