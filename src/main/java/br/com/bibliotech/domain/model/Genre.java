package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;

@Table(name = "genres")
@Entity(name = "Genre")
@EqualsAndHashCode(of = "id")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean deleted;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Genre(String name){
        this.name = name;
        this.deleted = false;
    }

    public String getName() {
        return name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void update(Genre genre) {
        this.name = genre.name;
    }

    public void delete() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
