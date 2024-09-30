package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Author;
import br.com.bibliotech.responses.AuthorResponse;
import br.com.bibliotech.responses.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AuthorResponseConverter implements Converter<AuthorResponse, Author>{

    @Autowired
    private BookResponseConverter bookResponseConverter;

    public AuthorResponse convert(Author author){
        List<BookResponse> bookResponses = bookResponseConverter.convertEach(author.getBooks());
        return new AuthorResponse(author.getFullName(), author.getStageName(), author.getBirthdate(), bookResponses);
    }

    public List<AuthorResponse> convertEach(List<Author> authors){
        List<AuthorResponse> authorResponses = new ArrayList<>();

        authors.forEach(author -> {
            AuthorResponse authorResponse = convert(author);
            authorResponses.add(authorResponse);
        });

        return authorResponses;
    }

}
