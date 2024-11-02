package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.presentation.dto.CategoryDTO;
import br.com.bibliotech.presentation.responses.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryConverter {


    public Category fromDto(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.name());
    }

    public CategoryResponse fromModel(Category category) {
        return new CategoryResponse(category.getName(), category.isDeleted());
    }

    public List<CategoryResponse> fromModelList(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        categories.forEach(model -> {
            CategoryResponse categoryResponse = fromModel(model);
            categoryResponses.add(categoryResponse);
        });

        return categoryResponses;
    }

}
