package br.com.bibliotech.responses;

import br.com.bibliotech.entities.Genre;

public record GenreResponse(String name) {

    public static GenreResponse converter(Genre genre){
        return new GenreResponse(genre.getName());
    }

}
