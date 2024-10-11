package br.com.bibliotech.domain.service;

import br.com.bibliotech.application.converter.GenreResponseConverter;
import br.com.bibliotech.application.dto.GenreDTO;
import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.infrastructure.repository.GenreRepository;
import br.com.bibliotech.presentation.response.GenreResponse;
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
