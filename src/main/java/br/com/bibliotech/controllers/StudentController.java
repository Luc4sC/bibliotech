package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.StudentDTO;
import br.com.bibliotech.responses.StudentResponse;
import br.com.bibliotech.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<StudentResponse> save(@RequestBody @Valid StudentDTO authorDTO){
        StudentResponse studentResponse = studentService.save(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<StudentResponse>> findAll(){
        List<StudentResponse> studentResponses = studentService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(studentResponses);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id){
        StudentResponse studentResponse = studentService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<StudentResponse> update(@RequestBody @Valid StudentDTO authorDTO, @PathVariable Long id){
        StudentResponse studentResponse = studentService.update(authorDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<StudentResponse> delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(path = "/{id}/block", produces = "application/json; charset=utf-8")
    public  ResponseEntity<StudentResponse> block(@PathVariable Long id){
        StudentResponse studentResponse = studentService.block(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }
}
