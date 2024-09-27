package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.AuthorDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Author")
@Table(name = "authors")
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Setter
    private boolean deleted;

    @OneToMany(mappedBy = "book")
    private List<Book> books;

    public Author(AuthorDTO authorDTO){
        this.fullName = authorDTO.fullName();
        this.stageName = authorDTO.stageName();
        this.birthdate = authorDTO.birthdate();
        this.deleted = false;
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
