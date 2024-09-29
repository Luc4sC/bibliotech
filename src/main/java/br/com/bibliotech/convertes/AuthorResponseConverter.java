package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Author;
import br.com.bibliotech.entities.Book;
import br.com.bibliotech.responses.AuthorResponse;
import br.com.bibliotech.responses.BookResponse;

import java.util.ArrayList;
import java.util.List;

public class AuthorResponseConverter {

    public static AuthorResponse convert(Author author){
        List<BookResponse> bookResponses = BookResponseConverter.convertList(author.getBooks());
        return new AuthorResponse(author.getFullName(), author.getStageName(), author.getBirthdate(), bookResponses);
    }

    public static List<AuthorResponse> convertList(List<Author> authors){
        List<AuthorResponse> authorResponses = new ArrayList<>();

        authors.forEach(author -> {
            AuthorResponse authorResponse = convert(author);
            authorResponses.add(authorResponse);
        });

        return authorResponses;
    }

}
