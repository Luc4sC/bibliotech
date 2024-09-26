package br.com.bibliotech.services;

import br.com.bibliotech.dtos.CategoryDTO;
import br.com.bibliotech.entities.Category;
import br.com.bibliotech.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional
    public void save(CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO);
        categoryRepository.save(category);
        log.info("Category saved: " + category);
    }


    public CategoryDTO getById(Long id) {
        return new CategoryDTO(findById(id));
    }

    public List<CategoryDTO> getAll(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        categories.forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO(category);
            categoryDTOS.add(categoryDTO);
        });

        return categoryDTOS;
    }


    @Transactional
    public void update(CategoryDTO categoryDTO, Long id){
        Category category = findById(id);
        category.setName(categoryDTO.name());
        log.info("Category updated: " + category);
    }

    @Transactional
    public void delete(Long id){
        Category category = findById(id);
        categoryRepository.delete(category);
        log.info("Category deleted: " + findById(id));
    }

    private Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new RuntimeException();

        return optionalCategory.get();
    }
}
