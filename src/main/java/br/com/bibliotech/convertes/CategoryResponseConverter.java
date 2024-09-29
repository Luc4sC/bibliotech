package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Category;
import br.com.bibliotech.responses.CategoryResponse;
import br.com.bibliotech.responses.BookResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponseConverter {

    public static CategoryResponse convert(Category category){
        List<BookResponse> bookResponses = BookResponseConverter.convertList(category.getBooks());
        return new CategoryResponse(category.getName(), bookResponses);
    }

    public static List<CategoryResponse> convertList(List<Category> categories){
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        categories.forEach(category -> {
            CategoryResponse categoryResponse = convert(category);
            categoryResponses.add(categoryResponse);
        });

        return categoryResponses;
    }

}
