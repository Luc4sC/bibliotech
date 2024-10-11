package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.application.dto.CategoryDTO;
import br.com.bibliotech.presentation.response.CategoryResponse;
import br.com.bibliotech.domain.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> save(@RequestBody @Valid CategoryDTO categoryDTO){
        CategoryResponse categoryResponse = categoryService.save(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<CategoryResponse>> findAll(){
        List<CategoryResponse> categoryResponses = categoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponses);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        CategoryResponse categoryResponse = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> update(@RequestBody @Valid CategoryDTO categoryDTO, @PathVariable Long id){
        CategoryResponse categoryResponse = categoryService.update(categoryDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryResponse> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
