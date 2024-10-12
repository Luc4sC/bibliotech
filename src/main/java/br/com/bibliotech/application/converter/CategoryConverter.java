package br.com.bibliotech.application.converter;

import br.com.bibliotech.application.dto.CategoryDTO;
import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.presentation.response.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryConverter implements GenericConverter<Category, CategoryDTO, CategoryResponse>{
    @Override
    public Category modelFromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.name());
    }

    @Override
    public CategoryResponse responseFromModel(Category category) {
        return new CategoryResponse(category.getName());
    }

    @Override
    public List<CategoryResponse> responseListFromModelList(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        categories.forEach(category -> {
            CategoryResponse categoryResponse = responseFromModel(category);
            categoryResponses.add(categoryResponse);
        });

        return categoryResponses;
    }
}
