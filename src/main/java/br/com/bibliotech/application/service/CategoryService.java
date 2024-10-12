package br.com.bibliotech.application.service;

import br.com.bibliotech.application.converter.CategoryConverter;
import br.com.bibliotech.application.dto.CategoryDTO;
import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.domain.service.CategoryDomainService;
import br.com.bibliotech.presentation.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDomainService categoryDomainService;

    private final CategoryConverter categoryConverter = new CategoryConverter();

    public CategoryResponse save(CategoryDTO categoryDTO){
        Category category = categoryConverter.modelFromDTO(categoryDTO);
        categoryDomainService.save(category);

        return categoryConverter.responseFromModel(category);
    }

    public CategoryResponse update(CategoryDTO categoryDTO, Long id){
        Category category = categoryDomainService.findBydId(id);
        Category categoryUpdate = categoryConverter.modelFromDTO(categoryDTO);

        categoryDomainService.update(category, categoryUpdate);

        return categoryConverter.responseFromModel(category);
    }

    public void delete(Long id){
        Category category = categoryDomainService.findBydId(id);
        categoryDomainService.delete(category);
    }

    public CategoryResponse findById(Long id){
        Category category = categoryDomainService.findBydId(id);
        return categoryConverter.responseFromModel(category);
    }

    public List<CategoryResponse> findAll(){
        List<Category> categories = categoryDomainService.findAll();
        return categoryConverter.responseListFromModelList(categories);
    }

    public CategoryResponse findByName(String name){
        Category category = categoryDomainService.findByName(name);
        return categoryConverter.responseFromModel(category);
    }
}
