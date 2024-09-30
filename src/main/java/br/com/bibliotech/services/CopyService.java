package br.com.bibliotech.services;

import br.com.bibliotech.convertes.CopyResponseConverter;
import br.com.bibliotech.dtos.CopyDTO;
import br.com.bibliotech.entities.Copy;
import br.com.bibliotech.repositories.CopyRepository;
import br.com.bibliotech.responses.CopyResponse;
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

    @Autowired
    private CopyResponseConverter copyResponseConverter;

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
