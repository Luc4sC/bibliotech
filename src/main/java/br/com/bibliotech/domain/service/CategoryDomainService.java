package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.exception.ConflictException;
import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.domain.repository.Categories;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryDomainService {

    @Autowired
    private Categories categories;

    public void save(Category category){
        if (isNameUnavailable(category.getName(), category))
            throw new ConflictException("Category with name: " + category.getName() + " already exist!");

        categories.save(category);
        log.info("Category saved: " + category);
    }

    public void update(Category category, Category categoryUpdate){
        if (isNameUnavailable(categoryUpdate.getName(), category))
            throw new ConflictException("Category with name: " + categoryUpdate.getName() + " already exist!");

        categories.update(category, categoryUpdate);
        log.info("Category updated: " + category);
    }

    public void delete(Category category){
        categories.delete(category);
        log.info("Category deleted: " + category);
    }

    public Category findBydId(Long id) {
        return categories.findById(id);
    }

    public List<Category> findAll(){
        return categories.findAll();
    }

    public Category findByName(String name){
        return categories.findByName(name);
    }

    private boolean isNameUnavailable(String name, Category category){
        try {
            Category byName = categories.findByName(name);
            return !category.equals(byName);
        } catch (NotFoundException e){
            return false;
        }
    }

}
