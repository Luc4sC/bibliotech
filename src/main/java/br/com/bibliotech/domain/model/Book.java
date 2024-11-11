package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Table(name = "books")
@Entity(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
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


    public String getIsbn() {
        return isbn;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void update(Book book) {
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

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
