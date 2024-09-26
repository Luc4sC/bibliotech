package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.GenreDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "genres")
@Entity(name = "Genre")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Genre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    public Genre(GenreDTO genreDTO){
        this.name = genreDTO.name();
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
