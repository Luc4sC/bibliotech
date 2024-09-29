package br.com.bibliotech.responses;

import br.com.bibliotech.entities.Category;

public record CategoryResponse(String name) {

    public static CategoryResponse converter(Category category){
        return new CategoryResponse(category.getName());
    }

}
