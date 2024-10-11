package br.com.bibliotech.domain.service;

import br.com.bibliotech.application.converter.CopyResponseConverter;
import br.com.bibliotech.application.dto.CopyDTO;
import br.com.bibliotech.domain.model.Copy;
import br.com.bibliotech.infrastructure.repository.CopyRepository;
import br.com.bibliotech.presentation.response.CopyResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CopyService {

    @Autowired
    private CopyRepository copyRepository;

    private final CopyResponseConverter copyResponseConverter = new CopyResponseConverter();

    @Transactional
    public CopyResponse save(CopyDTO copyDTO){
        Copy copy = new Copy(copyDTO);
        copyRepository.save(copy);

        log.info("Copy created: " + copy);
        return copyResponseConverter.convert(copy);
    }

    public CopyResponse getById(Long id){
        Copy copy = copyRepository.findById(id).orElseThrow();
        return copyResponseConverter.convert(copy);
    }

    public List<CopyResponse> getAll(){
        List<Copy> copies = copyRepository.findAll();
        return copyResponseConverter.convertEach(copies);
    }

    @Transactional
    public CopyResponse update(CopyDTO copyDTO, Long id){
        Copy copy = copyRepository.findById(id).orElseThrow();

        copy.setNumeration(copyDTO.numeration());
        copy.setIsbn(copyDTO.isbn());

        log.info("Copy updated: " + copy);
        return copyResponseConverter.convert(copy);
    }

    @Transactional
    public void delete(Long id){
        Copy copy = copyRepository.findById(id).orElseThrow();
        copy.setDeleted(true);
        copy.setAvailable(false);
        log.info("Copy deleted: " + copy);
    }

}
