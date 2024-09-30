package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.CopyDTO;
import br.com.bibliotech.responses.CopyResponse;
import br.com.bibliotech.services.CopyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/copy")
public class CopyController {

    @Autowired
    private CopyService copyService;

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<CopyResponse> save(@RequestBody @Valid CopyDTO authorDTO){
        CopyResponse copyResponse = copyService.save(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(copyResponse);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<CopyResponse>> findAll(){
        List<CopyResponse> copyResponses = copyService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(copyResponses);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CopyResponse> findById(@PathVariable Long id){
        CopyResponse copyResponse = copyService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(copyResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CopyResponse> update(@RequestBody @Valid CopyDTO authorDTO, @PathVariable Long id){
        CopyResponse copyResponse = copyService.update(authorDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(copyResponse);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<CopyResponse> delete(@PathVariable Long id){
        copyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
