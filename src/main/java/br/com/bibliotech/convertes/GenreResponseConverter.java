package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Genre;
import br.com.bibliotech.responses.BookResponse;
import br.com.bibliotech.responses.GenreResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GenreResponseConverter implements Converter<GenreResponse, Genre> {

    @Autowired
    private BookResponseConverter bookResponseConverter;

    @Override
    public GenreResponse convert(Genre genre){
        List<BookResponse> bookResponses = bookResponseConverter.convertEach(genre.getBooks());
        return new GenreResponse(genre.getName(), bookResponses);
    }

    @Override
    public List<GenreResponse> convertEach(List<Genre> genres){
        List<GenreResponse> genreResponses = new ArrayList<>();

        genres.forEach(category -> {
            GenreResponse categoryResponse = convert(category);
            genreResponses.add(categoryResponse);
        });

        return genreResponses;
    }

}
