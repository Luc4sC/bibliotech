package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.AuthorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Author")
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String fullName;

    @Setter
    private String stageName;

    @Setter
    private LocalDate birthdate;

    public Author(AuthorDTO authorDTO){
        this.fullName = authorDTO.fullName();
        this.stageName = authorDTO.stageName();
        this.birthdate = authorDTO.birthdate();
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", stageName='" + stageName + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
