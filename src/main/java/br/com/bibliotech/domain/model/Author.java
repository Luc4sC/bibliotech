package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public Author(String fullName, String stageName, LocalDate birthdate){
        this.fullName = fullName;
        this.stageName = stageName;
        this.birthdate = birthdate;
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
