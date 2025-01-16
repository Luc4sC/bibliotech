package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.repository.Genres;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void update(Genre genre) {
        genres.update(genre);
        log.info("Genre updated: " + genre);
    }

    public void delete(Long id) {
        Genre genre = genres.findById(id);
        genres.delete(genre);

        log.info("Genre deleted: " + genre);
    }

    public Genre findById(Long id) {
        return genres.findById(id);
    }

    public Page<Genre> findAll(Pageable pageable) {
        return genres.findAll(pageable);
    }

    public Genre findByName(String name) {
        return genres.findByName(name);
    }

}
