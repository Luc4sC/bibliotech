package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Request;
import br.com.bibliotech.domain.service.RequestService;
import br.com.bibliotech.presentation.converter.RequestConverter;
import br.com.bibliotech.presentation.dto.RequestDTO;
import br.com.bibliotech.presentation.responses.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/request")
public class RequestController {

    private final RequestService requestService;
    private final RequestConverter requestConverter;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
        this.requestConverter = new RequestConverter();
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(RequestDTO requestDTO) {
        Request request = requestConverter.fromDTO(requestDTO);
        requestService.save(request);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public RequestResponse findById(@PathVariable Long id) {
        Request request = requestService.findById(id);
        return requestConverter.fromModel(request);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestResponse> findAll() {
        List<Request> requests = requestService.findAll();
        return requestConverter.fromModelList(requests);
    }

}
