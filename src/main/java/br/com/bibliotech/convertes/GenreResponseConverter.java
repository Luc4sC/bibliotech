package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Genre;
import br.com.bibliotech.responses.BookResponse;
import br.com.bibliotech.responses.GenreResponse;

import java.util.ArrayList;
import java.util.List;

public class GenreResponseConverter {

    public static GenreResponse convert(Genre genre){
        List<BookResponse> bookResponses = BookResponseConverter.convertList(genre.getBooks());
        return new GenreResponse(genre.getName(), bookResponses);
    }

    public static List<GenreResponse> convertList(List<Genre> genres){
        List<GenreResponse> genreResponses = new ArrayList<>();

        genres.forEach(category -> {
            GenreResponse categoryResponse = convert(category);
            genreResponses.add(categoryResponse);
        });

        return genreResponses;
    }

}
