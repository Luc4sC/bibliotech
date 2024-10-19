package br.com.bibliotech.application.service;

import br.com.bibliotech.application.converter.GenreConverter;
import br.com.bibliotech.application.dto.GenreDTO;
import br.com.bibliotech.domain.exception.ConflictException;
import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.service.GenreDomainService;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import br.com.bibliotech.presentation.response.GenreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreDomainService genreDomainService;

    private final GenreConverter genreConverter = new GenreConverter();

    public GenreResponse save(GenreDTO genreDTO){
        Genre genre = genreConverter.modelFromDTO(genreDTO);

        validName(genre.getName(), genre);
        genreDomainService.save(genre);

        return genreConverter.responseFromModel(genre);
    }

    public GenreResponse update(GenreDTO genreDTO, Long id){
        Genre genre = genreDomainService.findById(id);
        Genre genreUpdate = genreConverter.modelFromDTO(genreDTO);

        validName(genreUpdate.getName(), genre);
        genreDomainService.update(genre, genreUpdate);

        return genreConverter.responseFromModel(genre);
    }

    public void delete(Long id){
        Genre genre = genreDomainService.findById(id);
        genreDomainService.delete(genre);
    }

    public GenreResponse findById(Long id){
        Genre genre = genreDomainService.findById(id);
        return genreConverter.responseFromModel(genre);
    }

    public List<GenreResponse> findAll(){
        List<Genre> genres = genreDomainService.findAll();
        return genreConverter.responseListFromModelList(genres);
    }

    public GenreResponse findByName(String name){
        Genre genre = genreDomainService.findByName(name);
        return genreConverter.responseFromModel(genre);
    }

    private void validName(String name, Genre genre){
        try{
            Genre byName = genreDomainService.findByName(name);
            if (!genre.equals(byName))
                throw new ConflictException("Genre with name: " + name + " already exist!");
        }
        catch (NotFoundException ignored){}
    }
}
