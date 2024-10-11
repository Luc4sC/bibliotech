package br.com.bibliotech.domain.model;

import br.com.bibliotech.application.dto.BookDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Table(name = "books")
@Entity(name = "Book")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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
    private int pages;

    @Column(nullable = false)
    @Setter
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishDate;

    @Column(nullable = false)
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
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private List<Copy> copies;

    public Book(BookDTO bookDTO, Author author, Category category, Genre genre, Publisher publisher){
        this.title = bookDTO.title();
        this.subtitle = bookDTO.subtitle();
        this.pages = bookDTO.pages();
        this.synopsis = bookDTO.synopsis();
        this.publishDate = bookDTO.publishDate();
        this.deleted = false;
        this.author = author;
        this.category = category;
        this.genre = genre;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
