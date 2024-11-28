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

    @Column(nullable = false, unique = true)
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

    public Book(String isbn, String title, String subtitle, String synopsis, int pages, LocalDate publishDate,
                int quantity) {

        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.synopsis = synopsis;
        this.pages = pages;
        this.publishDate = publishDate;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getPages() {
        return pages;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public Genre getGenre() {
        return genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
