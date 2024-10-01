package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Category;
import br.com.bibliotech.responses.CategoryResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CategoryResponseConverter implements Converter<CategoryResponse, Category> {

    public CategoryResponse convert(Category category){
        return new CategoryResponse(category.getName());
    }

    public List<CategoryResponse> convertEach(List<Category> categories){
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        categories.forEach(category -> {
            CategoryResponse categoryResponse = convert(category);
            categoryResponses.add(categoryResponse);
        });

        return categoryResponses;
    }

}
