package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.BookDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "books")
@Entity(name = "Book")
@NoArgsConstructor
@EqualsAndHashCode(of = "isbn")
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String title;

    @Setter
    private String subtitle;

    @Setter
    private String synopsis;

    @Column(nullable = false)
    @Setter
    private LocalDate publishDate;

    @Column(nullable = false)
    @Setter
    private String isbn;

    @Column(nullable = false)
    @Setter
    private boolean deleted;

    @Column(nullable = false)
    @Setter
    @ManyToOne
    private Author author;

    @Column(nullable = false)
    @Setter
    @ManyToOne
    private Category category;

    @Column(nullable = false)
    @Setter
    @ManyToOne
    private Genre genre;

    @Column(nullable = false)
    @Setter
    @ManyToOne
    private Publisher publisher;

    public Book(BookDTO bookDTO, Author author, Category category, Genre genre, Publisher publisher){
        this.title = bookDTO.title();
        this.subtitle = bookDTO.subtitle();
        this.synopsis = bookDTO.synopsis();
        this.publishDate = bookDTO.publishDate();
        this.isbn = bookDTO.isbn();
        this.deleted = false;
        this.author = author;
        this.category = category;
        this.genre = genre;
        this.publisher = publisher;
    }
}
