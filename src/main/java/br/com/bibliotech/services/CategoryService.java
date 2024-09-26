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

    public List<CategoryDTO> findAll(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        categories.forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO(category);
            categoryDTOS.add(categoryDTO);
        });

        return categoryDTOS;
    }

    public CategoryDTO findById(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty())
            throw new RuntimeException();

        return new CategoryDTO(optionalCategory.get());
    }

    @Transactional
    public void update(CategoryDTO categoryDTO, Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new RuntimeException();

        Category category = optionalCategory.get();
        category.setName(categoryDTO.name());
        log.info("Category updated: " + category);
    }

    @Transactional
    public void delete(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new RuntimeException();

        categoryRepository.deleteById(id);
        log.info("Category deleted: " + optionalCategory.get());
    }
}
