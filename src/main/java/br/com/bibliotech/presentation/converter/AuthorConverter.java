package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.presentation.dto.AuthorDTO;
import br.com.bibliotech.presentation.response.AuthorResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorConverter {

    public Author fromDTO(AuthorDTO authorDTO) {
        return new Author(authorDTO.fullName(), authorDTO.stageName(), authorDTO.birthdate());
    }


    public AuthorResponse fromModel(Author author) {
        return new AuthorResponse(author.getFullName(), author.getStageName(), author.getBirthdate(), author.isDeleted());
    }

    public List<AuthorResponse> fromPage(Page<Author> authors) {
        List<AuthorResponse> authorResponses = new ArrayList<>();
        authors.forEach(author -> authorResponses.add(fromModel(author)));

        return authorResponses;
    }

    public Author fromDTO(Long id, AuthorDTO authorDTO) {
        return new Author(id, authorDTO.fullName(), authorDTO.stageName(), authorDTO.birthdate());
    }
}
