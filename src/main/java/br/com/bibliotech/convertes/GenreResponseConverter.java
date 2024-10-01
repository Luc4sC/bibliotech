package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Genre;
import br.com.bibliotech.responses.BookResponse;
import br.com.bibliotech.responses.GenreResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class GenreResponseConverter implements Converter<GenreResponse, Genre> {

    @Override
    public GenreResponse convert(Genre genre){
        return new GenreResponse(genre.getName());
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
