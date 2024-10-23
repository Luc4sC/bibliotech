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

    private final CategoryRepository categoryRepository;

    @Autowired
    CategoriesImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category model) {
        categoryRepository.save(model);
    }

    @Override
    public void update(Category model, Category update) {
        model.setName(update.getName());
    }

    @Override
    public void delete(Category model) {
        model.setDeleted(true);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new NotFoundException("Category with id: " + id + " not found!");

        return optionalCategory.get();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByName(String name) {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if (optionalCategory.isEmpty())
            throw new NotFoundException("Category with name: " + name + " not found!");

        return optionalCategory.get();
    }

}
