package br.com.bibliotech.domain.model;

import br.com.bibliotech.domain.exception.AllBooksInStockException;
import br.com.bibliotech.domain.exception.CannotBeBorrowedException;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Table(name = "books")
@Entity(name = "Book")
public class Book {

    @Deprecated
    Book(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private String subtitle;

    @Column(nullable = false)
    private String synopsis;

    @Column(nullable = false)
    private int pages;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishDate;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int availableQuantity;

    @Column(nullable = false)
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private List<BookLoanRequest> bookLoanRequests;

    public Book(String isbn, String title, String subtitle, String synopsis, int pages, LocalDate publishDate,
                int quantity, int availableQuantity, Author author, Category category, Genre genre, Publisher publisher) {

        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.synopsis = synopsis;
        this.pages = pages;
        this.publishDate = publishDate;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.author = author;
        this.category = category;
        this.genre = genre;
        this.publisher = publisher;
    }

    public Book(Long id, String isbn, String title, String subtitle, String synopsis, int pages, LocalDate publishDate,
                int quantity, int availableQuantity, Author author, Category category, Genre genre, Publisher publisher) {

        this(isbn, title, subtitle, synopsis, pages, publishDate, quantity, availableQuantity, author, category, genre,
                publisher);
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Optional<String> getSubtitle() {
        return Optional.of(subtitle);
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

    public int getAvailableQuantity() {
        return availableQuantity;
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

    public boolean isNotAvailable() {
        return availableQuantity > 0;
    }

    public void delete() {
        deleted = true;
        quantity = 0;
        availableQuantity = 0;
    }

    public void borrow() {
        if (!isNotAvailable())
            throw new CannotBeBorrowedException("The book: " + this + " is not available to be borrowed");

        this.availableQuantity--;
    }

    public void giveBack() {
        if (availableQuantity == quantity)
            throw new AllBooksInStockException("The book: " + this + " has no copy borrowed");

        this.availableQuantity++;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
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
