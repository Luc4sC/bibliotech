package br.com.bibliotech.dtos;

import br.com.bibliotech.entities.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record AuthorDTO(@NotNull @NotBlank String fullName, @NotNull @NotBlank String stageName,
                        @NotNull @NotBlank @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate birthdate) {

    public AuthorDTO(Author author){
        this(author.getFullName(), author.getStageName(), author.getBirthdate());
    }
}
