package br.com.bibliotech.responses;

import br.com.bibliotech.entities.Author;

import java.time.LocalDate;

public record AuthorResponse(String fullName, String stageName, LocalDate birthdate) {

    public static AuthorResponse converter(Author author){
        return new AuthorResponse(author.getFullName(), author.getStageName(), author.getBirthdate());
    }

}
