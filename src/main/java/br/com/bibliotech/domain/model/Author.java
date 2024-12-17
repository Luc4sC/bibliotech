package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Entity(name = "Author")
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
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

    public Author(Long id, String fullName, String stageName, LocalDate birthdate) {
        this(fullName, stageName, birthdate);
        this.id = id;
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

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Author author = (Author) object;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
