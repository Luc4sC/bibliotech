package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.domain.repository.Categories;
import br.com.bibliotech.infrastructure.exception.ConflictException;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class CategoriesImpl implements Categories {

    private final CategoryRepository categoryRepository;

    @Autowired
    CategoriesImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void save(Category category) {
        try {
            categoryRepository.save(category);
        }
        catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Category named: " + category.getName() + " already exist!");
        }
    }

    @Override
    @Transactional
    public void update(Category category) {
        try {
            categoryRepository.flush();
        }
        catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Category named: " + category.getName() + " already exist!");
        }
    }

    @Override
    @Transactional
    public void delete(Category category) {
        category.delete();
        categoryRepository.flush();
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new NotFoundException("Category with id: " + id + " not found!");

        return optionalCategory.get();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findByName(String name) {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if (optionalCategory.isEmpty())
            throw new NotFoundException("Category with name: " + name + " not found!");

        return optionalCategory.get();
    }

}
