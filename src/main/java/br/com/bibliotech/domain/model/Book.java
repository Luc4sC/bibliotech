package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Table(name = "books")
@Entity(name = "Book")
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private String subtitle;

    private String synopsis;

    @Column(nullable = false)
    private int pages;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishDate;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean deleted;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Publisher publisher;

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void update(Book book) {
        this.isbn = book.isbn;
        this.title = book.title;
        this.subtitle = book.subtitle;
        this.synopsis = book.synopsis;
        this.pages = book.pages;
        this.publishDate = book.publishDate;
        this.quantity = book.quantity;
        this.author = book.author;
        this.category = book.category;
        this.genre = book.genre;
        this.publisher = book.publisher;
    }

    public void delete() {
        deleted = true;
        quantity = 0;
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
