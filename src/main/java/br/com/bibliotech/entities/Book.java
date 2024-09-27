package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.BookDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Table(name = "books")
@Entity(name = "Book")
@NoArgsConstructor
@EqualsAndHashCode(of = "isbn")
@Getter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String subtitle;

    @Setter
    private String synopsis;

    @Setter
    private LocalDate publishDate;

    @Setter
    private String isbn;

    @Setter
    private boolean deleted;

    @Setter
    @ManyToOne
    private Author author;

    @Setter
    @ManyToOne
    private Category category;

    @Setter
    @ManyToOne
    private Genre genre;

    @Setter
    @ManyToOne
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private PublishCompany publishCompany;

    public Book(BookDTO bookDTO, Author author, Category category, Genre genre, PublishCompany publishCompany){
        this.title = bookDTO.title();
        this.subtitle = bookDTO.subtitle();
        this.synopsis = bookDTO.synopsis();
        this.publishDate = bookDTO.publishDate();
        this.isbn = bookDTO.isbn();
        this.deleted = false;
        this.author = author;
        this.category = category;
        this.genre = genre;
        this.publishCompany = publishCompany;
    }
}
