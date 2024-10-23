package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Category;
import br.com.bibliotech.domain.service.CategoryService;
import br.com.bibliotech.presentation.converter.CategoryConverter;
import br.com.bibliotech.presentation.dto.CategoryDTO;
import br.com.bibliotech.presentation.responses.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryConverter categoryConverter;

    @Autowired
    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.categoryConverter = new CategoryConverter();
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> save(@RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = categoryConverter.fromDto(categoryDTO);
        categoryService.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryConverter.fromModel(category));
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> update(@RequestBody @Valid CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = categoryService.findById(id);
        categoryService.update(category, categoryConverter.fromDto(categoryDTO));

        return ResponseEntity.status(HttpStatus.OK).body(categoryConverter.fromModel(category));
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> delete(@PathVariable Long id){
        Category category = categoryService.findById(id);
        categoryService.delete(category);

        return ResponseEntity.status(HttpStatus.OK).body(categoryConverter.fromModel(category));
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        Category category = categoryService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(categoryConverter.fromModel(category));
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<CategoryResponse>> findAll(){
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(categoryConverter.fromModelList(categories));
    }

    @GetMapping(path = "/source", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> findById(@RequestParam String name) {
        Category category = categoryService.findByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(categoryConverter.fromModel(category));
    }

}
