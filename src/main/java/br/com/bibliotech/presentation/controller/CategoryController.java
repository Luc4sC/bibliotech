package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.domain.service.CategoryService;
import br.com.bibliotech.presentation.converter.CategoryConverter;
import br.com.bibliotech.presentation.dto.CategoryDTO;
import br.com.bibliotech.presentation.response.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @Autowired
    CategoryController(CategoryService categoryService, CategoryConverter categoryConverter) {
        this.categoryService = categoryService;
        this.categoryConverter = categoryConverter;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = categoryConverter.fromDTO(categoryDTO);
        categoryService.save(category);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid CategoryDTO categoryDTO, @PathVariable Long id) {
        categoryService.update(categoryConverter.fromDTO(id, categoryDTO));
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse findByName(@PathVariable Long id){
        Category category = categoryService.findById(id);
        return categoryConverter.fromModel(category);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> findAll(){
        List<Category> categories = categoryService.findAll();
        return categoryConverter.fromModelList(categories);
    }

    @GetMapping(path = "/{name}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse findByName(@PathVariable String name) {
        Category category = categoryService.findByName(name);
        return categoryConverter.fromModel(category);
    }

}
