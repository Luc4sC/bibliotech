package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Table(name = "book_request")
@Entity(name = "BookRequest")
public class BookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "request_id", updatable = false)
    private Request request;

    public BookRequest(Book book, Request request) {
        this.book = book;
        this.request = request;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "id=" + id +
                ", book=" + book +
                ", request=" + request +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookRequest bookRequest = (BookRequest) object;
        return Objects.equals(id, bookRequest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
