package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Author")
@Table(name = "authors")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String stageName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Column(nullable = false)
    private boolean deleted;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(String fullName, String stageName, LocalDate birthdate){
        this.fullName = fullName;
        this.stageName = stageName;
        this.birthdate = birthdate;
        this.deleted = false;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStageName() {
        return stageName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void update(Author author) {
        this.fullName = author.fullName;
        this.stageName = author.stageName;
        this.birthdate = author.birthdate;
    }

    public void delete() {
        deleted = true;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", stageName='" + stageName + '\'' +
                '}';
    }

}
