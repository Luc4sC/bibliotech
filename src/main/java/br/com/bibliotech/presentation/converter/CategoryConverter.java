package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.presentation.dto.CategoryDTO;
import br.com.bibliotech.presentation.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.name());
    }

    public CategoryResponse fromModel(Category category) {
        return new CategoryResponse(category.getName(), category.isDeleted());
    }

    public List<CategoryResponse> fromModelList(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categories.forEach(category -> categoryResponses.add(fromModel(category)));

        return categoryResponses;
    }

    public Category fromDTO(Long id, CategoryDTO categoryDTO) {
        return new Category(id, categoryDTO.name());
    }

}
