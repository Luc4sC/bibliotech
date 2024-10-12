package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.domain.repository.Categories;
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
        categories.save(category);
        log.info("Category saved: " + category);
    }

    public void update(Category category, Category categoryUpdate){
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
}
