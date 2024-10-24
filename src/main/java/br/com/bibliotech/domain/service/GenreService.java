package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.repository.Genres;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GenreService {

    private final Genres genres;

    @Autowired
    GenreService(Genres genres) {
        this.genres = genres;
    }

    public void save(Genre genre) {
        genres.save(genre);
        log.info("Genre created: " + genre);
    }

    public void update(Genre genre, Genre update) {
        genres.update(genre, update);
        log.info("Genre updated: " + genre);
    }

    public void delete(Genre genre) {
        genres.delete(genre);
        log.info("Genre deleted: " + genre);
    }

    public Genre findById(Long id) {
        return genres.findById(id);
    }

    public List<Genre> findAll() {
        return genres.findAll();
    }

    public Genre findByName(String name) {
        return genres.findByName(name);
    }

}
