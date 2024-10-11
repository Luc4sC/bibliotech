package br.com.bibliotech.domain.model;

import br.com.bibliotech.application.dto.GenreDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "genres")
@Entity(name = "Genre")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false)
    @Setter
    private boolean deleted;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Genre(GenreDTO genreDTO){
        this.name = genreDTO.name();
        this.deleted = false;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
