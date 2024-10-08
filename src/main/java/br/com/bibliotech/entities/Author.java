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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String fullName;

    @Column(nullable = false)
    @Setter
    private String stageName;

    @Column(nullable = false)
    @Setter
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Column(nullable = false)
    @Setter
    private boolean deleted;

    @OneToMany(mappedBy = "author")
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
                ", stageName='" + stageName + '\'' +
                '}';
    }
}
