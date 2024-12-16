package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Table(name = "books_requests")
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
    private LoanRequest loanRequest;

    public BookRequest(Book book, LoanRequest loanRequest) {
        this.book = book;
        this.loanRequest = loanRequest;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "id=" + id +
                ", book=" + book +
                ", request=" + loanRequest +
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
