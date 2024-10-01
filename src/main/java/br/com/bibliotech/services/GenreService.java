package br.com.bibliotech.services;

import br.com.bibliotech.convertes.GenreResponseConverter;
import br.com.bibliotech.dtos.GenreDTO;
import br.com.bibliotech.entities.Genre;
import br.com.bibliotech.repositories.GenreRepository;
import br.com.bibliotech.responses.GenreResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    private final GenreResponseConverter genreResponseConverter = new GenreResponseConverter();

    @Transactional
    public GenreResponse save(GenreDTO genreDTO) {
        Genre genre = new Genre(genreDTO);
        genreRepository.save(genre);

        log.info("Genre created: " + genre);
        return genreResponseConverter.convert(genre);
    }

    public GenreResponse getById(Long id){
        Genre genre = genreRepository.findById(id).orElseThrow();
        return genreResponseConverter.convert(genre);
    }

    public List<GenreResponse> getAll() {
        List<Genre> genres = genreRepository.findAll();
        return genreResponseConverter.convertEach(genres);
    }

    @Transactional
    public GenreResponse update(GenreDTO genreDTO, Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        genre.setName(genreDTO.name());

        log.info("Genre updated: " + genre);
        return genreResponseConverter.convert(genre);
    }

    @Transactional
    public void delete(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        genre.setDeleted(true);
        log.info("Genre deleted: " + genre);
    }
}
