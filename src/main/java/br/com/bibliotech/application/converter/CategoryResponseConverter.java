package br.com.bibliotech.application.converter;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.presentation.response.CategoryResponse;
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
