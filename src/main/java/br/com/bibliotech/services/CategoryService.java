package br.com.bibliotech.services;

import br.com.bibliotech.convertes.CategoryResponseConverter;
import br.com.bibliotech.dtos.CategoryDTO;
import br.com.bibliotech.entities.Category;
import br.com.bibliotech.repositories.CategoryRepository;
import br.com.bibliotech.responses.CategoryResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional
    public CategoryResponse save(CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO);
        categoryRepository.save(category);
        log.info("Category saved: " + category);

        return CategoryResponseConverter.convert(category);
    }


    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return CategoryResponseConverter.convert(category);
    }

    public List<CategoryResponse> getAll(){
        List<Category> categories = categoryRepository.findAll();

        return CategoryResponseConverter.convertEach(categories);
    }


    @Transactional
    public CategoryResponse update(CategoryDTO categoryDTO, Long id){
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(categoryDTO.name());

        log.info("Category updated: " + category);
        return CategoryResponseConverter.convert(category);
    }

    @Transactional
    public void delete(Long id){
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setDeleted(true);

        log.info("Category deleted: " + category);
    }

}
