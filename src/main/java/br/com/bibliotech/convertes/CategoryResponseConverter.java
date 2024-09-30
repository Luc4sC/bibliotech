package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Category;
import br.com.bibliotech.responses.CategoryResponse;
import br.com.bibliotech.responses.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponseConverter implements Converter<CategoryResponse, Category> {

    @Autowired
    BookResponseConverter bookResponseConverter;

    public CategoryResponse convert(Category category){
        List<BookResponse> bookResponses = bookResponseConverter.convertEach(category.getBooks());
        return new CategoryResponse(category.getName(), bookResponses);
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
