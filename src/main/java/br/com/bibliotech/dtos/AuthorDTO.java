package br.com.bibliotech.dtos;

import br.com.bibliotech.entities.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record AuthorDTO(@NotEmpty @Size(min = 5, max = 50) String fullName, @NotEmpty @Size(min = 5, max = 25) String stageName,
                        @NotNull LocalDate birthdate) {

    public AuthorDTO(Author author){
        this(author.getFullName(), author.getStageName(), author.getBirthdate());
    }
}
