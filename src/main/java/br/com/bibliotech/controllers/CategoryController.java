package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.CategoryDTO;
import br.com.bibliotech.services.CategoryService;
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
    public ResponseEntity<CategoryDTO> save(@RequestBody @Valid CategoryDTO categoryDTO){
        categoryService.save(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categoryDTOS = categoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOS);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        CategoryDTO categoryDTO = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryDTO> update(@RequestBody @Valid CategoryDTO categoryDTO, @PathVariable Long id){
        categoryService.update(categoryDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
