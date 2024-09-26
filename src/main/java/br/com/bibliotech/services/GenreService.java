package br.com.bibliotech.services;

import br.com.bibliotech.dtos.GenreDTO;
import br.com.bibliotech.entities.Genre;
import br.com.bibliotech.repositories.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Transactional
    public void save(GenreDTO genreDTO) {
        Genre genre = new Genre(genreDTO);
        genreRepository.save(genre);
        log.info("Genre created: " + genre);
    }

    public GenreDTO getById(Long id){
        return new GenreDTO(findById(id));
    }

    public List<GenreDTO> getAll() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDTO> genreDTOS = new ArrayList<>();

        genres.forEach(genre -> {
            GenreDTO genreDTO = new GenreDTO(genre);
            genreDTOS.add(genreDTO);
        });

        return genreDTOS;
    }

    @Transactional
    public void update(GenreDTO genreDTO, Long id) {
        Genre genre = findById(id);
        genre.setName(genreDTO.name());
        log.info("Genre updated: " + genre);
    }

    @Transactional
    public void delete(Long id) {
        Genre genre = findById(id);
        genreRepository.delete(genre);
        log.info("Genre deleted: " + genre);
    }

    private Genre findById(Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isEmpty())
            throw new RuntimeException();

        return optionalGenre.get();
    }
}
