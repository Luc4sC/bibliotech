package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.presentation.dto.CategoryDTO;
import br.com.bibliotech.presentation.responses.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryConverter implements GenericConverter<Category, CategoryDTO, CategoryResponse> {


    @Override
    public Category fromDto(CategoryDTO dto) {
        return new Category(dto.name());
    }

    @Override
    public CategoryResponse fromModel(Category model) {
        return new CategoryResponse(model.getName());
    }

    @Override
    public List<CategoryResponse> fromModelList(List<Category> models) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        models.forEach(model -> {
            CategoryResponse categoryResponse = fromModel(model);
            categoryResponses.add(categoryResponse);
        });

        return categoryResponses;
    }

}
