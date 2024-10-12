package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.domain.repository.Categories;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class CategoriesImpl implements Categories {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findByName(String name) {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        if (categoryOptional.isEmpty())
            throw new NotFoundException("Category with name: " + name + " not found!");

        return categoryOptional.get();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        category.setDeleted(true);
    }

    @Override
    public void update(Category category, Category categoryUpdate) {
        category.setName(categoryUpdate.getName());
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty())
            throw new NotFoundException("Category with id: " + id + " not found!");

        return categoryOptional.get();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
